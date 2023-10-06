package dao;

import javax.persistence.*;

@Entity
@Table(name = "customer")
//Default значения для полей schema и catalog указаны в файле конфигурации Hibernate
//@Table(name = "customer", schema = "public", catalog = "postgres")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastname;
    private String name;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String surname) {
        this.name = name;
        this.lastname = surname;
    }

    public CustomerEntity(Integer id, String lastname, String name) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
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
        return "CustomerEntity{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
