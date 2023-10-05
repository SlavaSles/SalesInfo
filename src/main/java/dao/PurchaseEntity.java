package dao;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchase", schema = "public", catalog = "postgres")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private LocalDate date;

    public PurchaseEntity() {
    }

    public PurchaseEntity(CustomerEntity customer, ProductEntity product, LocalDate date) {
        this.customer = customer;
        this.product = product;
        this.date = date;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customerEntity) {
        this.customer = customerEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity productEntity) {
        this.product = productEntity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PurchaseEntity{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", date=" + date +
                '}';
    }
}
