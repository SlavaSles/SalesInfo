package dao;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Класс, который является сущностью "Товар" для БД
 */
@Entity
@Table(name = "product")
//Default значения для полей schema и catalog указаны в файле конфигурации Hibernate
//@Table(name = "product", schema = "public", catalog = "postgres")
public class ProductEntity {

    /**
     * Поле - идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Поле - название товара
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * Поле - цена товара
     */
    private BigDecimal price;

    /**
     * Пустой конструктор класса
     */
    public ProductEntity() {
    }

    /**
     * Конструктор класса с параметрами
     * @param productName название товара
     * @param price цена товара
     */
    public ProductEntity(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    /**
     * Функция получения названия товара
     * @return возвращает название товара
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Процедура присвоения названия товара
     * @param productName название товара
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Функция получения цены товара
     * @return возвращает цену товара
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Процедура присвоения цены товара
     * @param price цена товара
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
