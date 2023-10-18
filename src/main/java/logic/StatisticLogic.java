package logic;

import dao.CustomerEntity;
import dto.request.StatRequest;
import dto.response.statistic.Purchase;
import dto.response.statistic.StatCustomerResults;
import dto.response.statistic.StatResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализован алгоритм сбора статистики из БД за период времени, указанный в запросе
 */
public class StatisticLogic {

    /**
     * Поле - запрос статистики из БД за период времени, указанный в запросе
     */
    StatRequest statRequest;

    /**
     * Конструктор класса с параметром
     * @param statRequest запрос статистики из БД за период времени, указанный в запросе
     */
    public StatisticLogic(StatRequest statRequest) {
        this.statRequest = statRequest;
    }

    /**
     * Функция сбора статистики из БД за период времени, указанный в запросе
     * @return возвращает ответ {@link StatResponse} в соответствии со спецификацией API
     */
    public StatResponse getStatistic() {
        StatResponse statResponse = new StatResponse();
        laborDaysCount(statResponse);
        List<CustomerEntity> customersList = findAllCustomersAndTheirExpensesForACertainPeriod(statResponse);
        findAllProductExpensesForEachCustomer(statResponse, customersList);
        findTotalAndAvgExpenses(statResponse);
        return statResponse;
    }

    private void laborDaysCount(StatResponse statResponse) {
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createNativeQuery(
                        "SELECT COUNT(*) labor_dates_count\n" +
                                "FROM GENERATE_SERIES (CAST (:startDate AS DATE), CAST (:endDate AS DATE), " +
                                    "INTERVAL '1 DAY' ) d\n" +
                                "WHERE EXTRACT(DOW FROM d) NOT IN (0,6);")
                .setParameter("startDate", statRequest.getStartDate())
                .setParameter("endDate", statRequest.getEndDate())
                .uniqueResult();
        statResponse.setTotalDays(Integer.parseInt(object.toString()));
        transaction.commit();
        session.close();
    }

    private List<CustomerEntity> findAllCustomersAndTheirExpensesForACertainPeriod(StatResponse statResponse) {
        List<CustomerEntity> customersList = new ArrayList<>();
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> tuples = session.createNativeQuery(
                        "SELECT c.id, c.lastname, c.name, SUM(pro.price) total_customer_expenses " +
                                "FROM {h-schema}customer c\n" +
                                "JOIN {h-schema}purchase pur ON pur.customer_id = c.id\n" +
                                "JOIN {h-schema}product pro ON pro.id = pur.product_id\n" +
                                "WHERE pur.date BETWEEN :startDate AND :endDate " +
                                    "AND ((SELECT EXTRACT(DOW FROM pur.date)) NOT IN (0, 6)) \n" +
                                "GROUP BY c.id\n" +
                                "ORDER BY total_customer_expenses DESC;")
                .setParameter("startDate", statRequest.getStartDate())
                .setParameter("endDate", statRequest.getEndDate())
                .list();
        for(Object[] tuple : tuples) {
            CustomerEntity customer = new CustomerEntity((Integer) tuple[0],
                    (String) tuple[1], (String) tuple[2]) ;
            BigDecimal totalProductExpenses = (BigDecimal) tuple[3];
            customersList.add(customer);
            StatCustomerResults statCustomerResults = new StatCustomerResults();
            statCustomerResults.setName(customer.getLastname().concat(" ").concat(customer.getName()));
            statCustomerResults.setTotalExpenses(totalProductExpenses);
            List<StatCustomerResults> customersResp = statResponse.getCustomers();
            customersResp.add(statCustomerResults);
        }
        transaction.commit();
        session.close();
        return customersList;
    }

    private void findAllProductExpensesForEachCustomer(StatResponse statResponse, List<CustomerEntity> customersList) {
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        int index = 0;
        for (CustomerEntity customer : customersList) {
            List<Object[]> tuples = session.createNativeQuery(
                            "SELECT pro.product_name, SUM(pro.price) totalProductExpenses " +
                                    "FROM {h-schema}customer c\n" +
                                    "JOIN {h-schema}purchase pur ON pur.customer_id = c.id\n" +
                                    "JOIN {h-schema}product pro ON pro.id = pur.product_id\n" +
                                    "WHERE c.name = :name AND c.lastname = :lastname " +
                                        "AND pur.date BETWEEN :startDate AND :endDate " +
                                        "AND ((SELECT EXTRACT(DOW FROM pur.date)) NOT IN (0, 6)) \n" +
                                    "GROUP BY c.id, pro.product_name\n" +
                                    "ORDER BY totalProductExpenses DESC;")
                    .setParameter("name", customer.getName())
                    .setParameter("lastname", customer.getLastname())
                    .setParameter("startDate", statRequest.getStartDate())
                    .setParameter("endDate", statRequest.getEndDate())
                    .list();
            List<Purchase> purchases = new ArrayList<>();
            for(Object[] tuple : tuples) {
                Purchase purchase = new Purchase((String) tuple[0], (BigDecimal) tuple[1]) ;
                purchases.add(purchase);
            }
            List<StatCustomerResults> customersResp = statResponse.getCustomers();
            customersResp.get(index++).setPurchases(purchases);
        }
        transaction.commit();
        session.close();
    }

    private void findTotalAndAvgExpenses(StatResponse statResponse) {
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        List<Object[]> tuples = session.createNativeQuery(
                        "SELECT SUM(pro.price) totalExpenses, SUM(pro.price)/(\n" +
                                "SELECT COUNT(DISTINCT c.id) totalCustomers FROM {h-schema}customer c\n" +
                                "JOIN {h-schema}purchase pur ON pur.customer_id = c.id\n" +
                                "JOIN {h-schema}product pro ON pro.id = pur.product_id\n" +
                                "WHERE pur.date BETWEEN :startDate AND :endDate " +
                                "AND ((SELECT EXTRACT(DOW FROM pur.date)) NOT IN (0, 6)) ) avgExpenses\n" +
                                "FROM {h-schema}customer c\n" +
                                "JOIN {h-schema}purchase pur ON pur.customer_id = c.id\n" +
                                "JOIN {h-schema}product pro ON pro.id = pur.product_id\n" +
                                "WHERE pur.date BETWEEN :startDate AND :endDate " +
                                "AND ((SELECT EXTRACT(DOW FROM pur.date)) NOT IN (0, 6));")
                .setParameter("startDate", statRequest.getStartDate())
                .setParameter("endDate", statRequest.getEndDate())
                .list();
        Object[] tuple = tuples.get(0);
        if (tuple[0] != null && tuple[1] != null) {
            statResponse.setTotalExpenses((BigDecimal) tuple[0]);
            statResponse.setAvgExpenses(((BigDecimal) tuple[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        transaction.commit();
        session.close();
    }
}
