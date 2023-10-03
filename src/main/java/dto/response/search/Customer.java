package dto.response.search;

public class Customer {
    String name;
    String lastname;

    public Customer() {
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.lastname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
