package dto.criteria;

public class BadCustomers extends Criteriya {
    Integer badCustomers;

    public BadCustomers() {
    }

    public BadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    public Integer getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Integer badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public String toString() {
        return "BadCustomers{" +
                "badCustomers=" + badCustomers +
                '}';
    }
}
