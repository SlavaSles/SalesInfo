package dto.criteria;

public class Product extends Criteria {
    String productName;
    Integer minTimes;

    public Product() {
    }

    public Product(String productName, Integer minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(Integer minTimes) {
        this.minTimes = minTimes;
    }
}
