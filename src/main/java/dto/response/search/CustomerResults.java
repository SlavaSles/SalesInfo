package dto.response.search;

import java.util.List;

public class CustomerResults {
    private List<Customer> customers;

    public CustomerResults() {
    }

    public CustomerResults(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "CustomerResults{" +
                "customers=" + customers +
                '}';
    }
}
