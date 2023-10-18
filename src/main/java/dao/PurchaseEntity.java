package dao;

import javax.persistence.*;

import java.time.LocalDate;

/**
 * Класс, который является сущностью "Покупка" для БД
 */
@Entity
@Table(name = "purchase")
//Default значения для полей schema и catalog указаны в файле конфигурации Hibernate
//@Table(name = "purchase", schema = "public", catalog = "postgres")
public class PurchaseEntity {

    /**
     * Поле - идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Поле - покупатель {@link CustomerEntity}
     */
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    /**
     * Поле - товар {@link ProductEntity}
     */
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    /**
     * Поле - дата совершения покупки
     */
    private LocalDate date;

    /**
     * Пустой конструктор класса
     */
    public PurchaseEntity() {
    }

    /**
     * Конструктор класса с параметрами
     * @param customer покупатель
     * @param product товар
     * @param date дата совершения покупки
     */
    public PurchaseEntity(CustomerEntity customer, ProductEntity product, LocalDate date) {
        this.customer = customer;
        this.product = product;
        this.date = date;
    }

    /**
     * Функция получения покупателя
     * @return возвращает покупателя
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Процедура присвоения покупателя
     * @param customerEntity покупатель
     */
    public void setCustomer(CustomerEntity customerEntity) {
        this.customer = customerEntity;
    }

    /**
     * Функция получения купленного товара
     * @return возвращает товар
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Процедура присвоения товара
     * @param productEntity товар
     */
    public void setProduct(ProductEntity productEntity) {
        this.product = productEntity;
    }

    /**
     * Функция получения даты совершения покупки
     * @return возвращает дату совершения покупки
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Процедура присвоения даты совершения покупки
     * @param date дата совершения покупки
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
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
