package logic;

import dao.CustomerEntity;
import dao.ProductEntity;
import dao.PurchaseEntity;
import dto.criteria.*;
import dto.request.SearchRequest;
import dto.response.ResponseType;
import dto.response.search.CriteriyaResult;
import dto.response.search.Customer;
import dto.response.search.SearchResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SearchLogic {
    private SearchRequest searchRequest;

    public SearchLogic(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }

    public SearchResponse search() {
        SearchResponse searchResponse = new SearchResponse(ResponseType.SEARCH);
        List<CriteriyaResult> criteriyaResults = searchResponse.getResults();
        List<Criteriya> criteriyas = searchRequest.getCriteriyas();
        for (Criteriya criteriya : criteriyas) {
//            System.out.println("****");
            ArrayList<CustomerEntity> customers = new ArrayList<>();
            if (criteriya instanceof Lastname) {
                Lastname lastname = (Lastname) criteriya;
                customers = lastnameSearch(lastname);
            } else if (criteriya instanceof Product) {
//                System.out.println("Criteriya is Product");
                Product product = (Product) criteriya;
                customers = productSearch(product);
            } else if (criteriya instanceof ExpensesRange) {
                System.out.println("Criteriya is ExpensesRange");
                ExpensesRange expensesRange = (ExpensesRange) criteriya;
                customers = expensesRangeSearch(expensesRange);
            } else if (criteriya instanceof BadCustomers) {
                System.out.println("Criteriya is BadCustomer");
                BadCustomers badCustomers = (BadCustomers) criteriya;
                customers = badCustomersSearch(badCustomers);
            }

            criteriyaResults.add(createCriteriyaResult(customers, criteriya));
        }
        System.out.println("****");
        System.out.println(searchResponse);
        return searchResponse;
    }

    private CriteriyaResult createCriteriyaResult(ArrayList<CustomerEntity> customers, Criteriya criteriya) {
        List<Customer> respCustomers = new ArrayList<>();
        for(CustomerEntity customer : customers) {
            Customer respCustomer = new Customer(customer.getName(), customer.getLastname());
            respCustomers.add(respCustomer);
        }
//        CustomerResults customerResults = new CustomerResults(respCustomers);
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
        customers.forEach(System.out::println);
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
            System.out.println(customer);
            customers.add(customer);
        }
        transaction.commit();
        session.close();
        return customers;
    }

    private ArrayList<CustomerEntity> expensesRangeSearch(ExpensesRange expensesRange) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();

        return customers;
    }

    private ArrayList<CustomerEntity> badCustomersSearch(BadCustomers badCustomers) {
        ArrayList<CustomerEntity> customers = new ArrayList<>();

        return customers;
    }
}
