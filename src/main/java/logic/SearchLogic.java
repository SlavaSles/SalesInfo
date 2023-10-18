package logic;

import dao.CustomerEntity;
import dao.ProductEntity;
import dao.PurchaseEntity;
import dto.criteria.*;
import dto.request.SearchRequest;
import dto.response.search.CriteriyaResult;
import dto.response.search.Customer;
import dto.response.search.SearchResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализован алгоритм поиска покупателей в БД по данным из запроса
 */
public class SearchLogic {

    /**
     * Поле - запрос поиска покупателей по критериям
     */
    private SearchRequest searchRequest;

    /**
     * Конструктор класса с параметром
     * @param searchRequest запрос поиска покупателей по критериям
     */
    public SearchLogic(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }

    /**
     * Функция поиска покупателей в БД по заданным критериям
     * @return возвращает ответ {@link SearchResponse} в соответствии со спецификацией API
     */
    public SearchResponse search() {
        SearchResponse searchResponse = new SearchResponse();
        List<CriteriyaResult> criteriyaResults = searchResponse.getResults();
        List<Criteriya> criteriyas = searchRequest.getCriteriyas();
        for (Criteriya criteriya : criteriyas) {
            ArrayList<CustomerEntity> customers = new ArrayList<>();
            if (criteriya instanceof Lastname) {
                Lastname lastname = (Lastname) criteriya;
                customers = lastnameSearch(lastname);
            } else if (criteriya instanceof Product) {
                Product product = (Product) criteriya;
                customers = productSearch(product);
            } else if (criteriya instanceof ExpensesRange) {
                ExpensesRange expensesRange = (ExpensesRange) criteriya;
                customers = expensesRangeSearch(expensesRange);
            } else if (criteriya instanceof BadCustomers) {
                BadCustomers badCustomers = (BadCustomers) criteriya;
                customers = badCustomersSearch(badCustomers);
            }
            criteriyaResults.add(createCriteriyaResult(customers, criteriya));
        }
        return searchResponse;
    }

    private CriteriyaResult createCriteriyaResult(ArrayList<CustomerEntity> customers, Criteriya criteriya) {
        List<Customer> respCustomers = new ArrayList<>();
        for(CustomerEntity customer : customers) {
            Customer respCustomer = new Customer(customer.getName(), customer.getLastname());
            respCustomers.add(respCustomer);
        }
        CriteriyaResult criteriyaResult = new CriteriyaResult(criteriya, respCustomers);
        return criteriyaResult;
    }

    private ArrayList<CustomerEntity> lastnameSearch(Lastname lastname) {
        ArrayList<CustomerEntity> customers;
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> customerQuery = builder.createQuery(CustomerEntity.class);
        Root<CustomerEntity> customerRoot = customerQuery.from(CustomerEntity.class);
        customerQuery.select(customerRoot)
                .where(builder.equal(customerRoot.get("lastname"), lastname.getLastName()));
        customers = (ArrayList<CustomerEntity>) session.createQuery(customerQuery).getResultList();
        transaction.commit();
        session.close();
        return customers;
    }

    private ArrayList<CustomerEntity> productSearch(Product product) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> customerQuery = builder.createQuery(Tuple.class);
        Root<PurchaseEntity> purchaseRoot = customerQuery.from(PurchaseEntity.class);
        Join<PurchaseEntity,CustomerEntity> customerJoin = purchaseRoot.join("customer");
        Join<ProductEntity,PurchaseEntity> productJoin = purchaseRoot.join("product");
        customerQuery.multiselect(customerJoin, builder.count(customerJoin.get("id")))
                .where(builder
                        .equal(productJoin.get("productName"), product.getProductName()))
                .groupBy(customerJoin.get("id"))
                .having(builder.ge(builder
                        .count(customerJoin.get("id")), product.getMinTimes()));
        ArrayList<Tuple> customerCountTuples = (ArrayList<Tuple>) session.createQuery(customerQuery).getResultList();
        for(Tuple tuple : customerCountTuples) {
            CustomerEntity customer = tuple.get(0, CustomerEntity.class);
            customers.add(customer);
        }
        transaction.commit();
        session.close();
        return customers;
    }

    private ArrayList<CustomerEntity> expensesRangeSearch(ExpensesRange expensesRange) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> customerQuery = builder.createQuery(Tuple.class);
        Root<PurchaseEntity> purchaseRoot = customerQuery.from(PurchaseEntity.class);
        Join<PurchaseEntity,CustomerEntity> customerJoin = purchaseRoot.join("customer");
        Join<ProductEntity,PurchaseEntity> productJoin = purchaseRoot.join("product");
        customerQuery.multiselect(customerJoin, builder.sum(productJoin.get("price")))
                .groupBy(customerJoin.get("id"))
                .having(builder
                                .and(builder.ge(builder
                                        .sum(productJoin.get("price")), expensesRange.getMinExpenses())),
                        builder.le(builder
                                .sum(productJoin.get("price")), expensesRange.getMaxExpenses()))
                .orderBy(builder.desc((Expression<?>) builder.sum(productJoin.get("price"))));
        ArrayList<Tuple> customerCountTuples = (ArrayList<Tuple>) session.createQuery(customerQuery).getResultList();
        for(Tuple tuple : customerCountTuples) {
            CustomerEntity customer = tuple.get(0, CustomerEntity.class);
            customers.add(customer);
        }
        transaction.commit();
        session.close();
        return customers;
    }

    private ArrayList<CustomerEntity> badCustomersSearch(BadCustomers badCustomers) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();
        Session session = HibernateSF.openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> customerQuery = builder.createQuery(Tuple.class);
        Root<PurchaseEntity> purchaseRoot = customerQuery.from(PurchaseEntity.class);
        Join<PurchaseEntity,CustomerEntity> customerJoin = purchaseRoot.join("customer");
        Join<ProductEntity,PurchaseEntity> productJoin = purchaseRoot.join("product");
        customerQuery.multiselect(customerJoin, builder.sum(productJoin.get("price")))
                .groupBy(customerJoin.get("id"))
                .orderBy(builder.asc((Expression<?>) builder.sum(productJoin.get("price"))));
        ArrayList<Tuple> customerCountTuples = (ArrayList<Tuple>) session.createQuery(customerQuery)
                .setMaxResults(badCustomers.getBadCustomers()).getResultList();
        for(Tuple tuple : customerCountTuples) {
            CustomerEntity customer = tuple.get(0, CustomerEntity.class);
            customers.add(customer);
        }
        transaction.commit();
        session.close();
        return customers;
    }
}
