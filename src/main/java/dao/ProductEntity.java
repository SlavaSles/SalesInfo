package dao;

import java.math.BigDecimal;

public class ProductEntity {
    Integer id;
    String productName;
    BigDecimal price;

    public ProductEntity() {
    }

    public ProductEntity(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
