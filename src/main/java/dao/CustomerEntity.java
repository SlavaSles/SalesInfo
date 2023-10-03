package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue()
    Integer id;
    String name;
    String lastname;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String surname) {
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
