package dto.response.search;

public class Customer {
    private String name;
    private String lastname;

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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
