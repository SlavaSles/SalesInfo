package dao;

import java.util.Date;

public class PurchaseEntity {
    Integer id;
    CustomerEntity customer;
    ProductEntity product;
    Date date;

    public PurchaseEntity() {
    }

    public PurchaseEntity(CustomerEntity customer, ProductEntity product, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
