package dao;

import javax.persistence.*;

@Entity
//@Table(name = "customer")
@Table(name = "customer", schema = "public", catalog = "postgres")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;

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
