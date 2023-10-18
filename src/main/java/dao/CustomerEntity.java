package dao;

import javax.persistence.*;

/**
 * Класс, который является сущностью "Покупатель" для БД
 */
@Entity
@Table(name = "customer")
//Default значения для полей schema и catalog указаны в файле конфигурации Hibernate
//@Table(name = "customer", schema = "public", catalog = "postgres")
public class CustomerEntity {
    /**
     * Поле - идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Поле - фамилия покупателя
     */
    private String lastname;

    /**
     * Поле - имя покупателя
     */
    private String name;

    /**
     * Пустой конструктор класса
     */
    public CustomerEntity() {
    }

    /**
     * Конструктор класса с параметрами
     * @param name имя покупателя
     * @param lastname фамилия покупателя
     */
    public CustomerEntity(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    /**
     * Конструктор класса с параметрами
     * @param id идентификатор
     * @param lastname фамилия покупателя
     * @param name имя покупателя
     */
    public CustomerEntity(Integer id, String lastname, String name) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
    }

    /**
     * Функция получения имени покупателя
     * @return возвращает имя покупателя
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура присвоения имени покупателя
     * @param name имя покупателя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения фамилии покупателя
     * @return возвращает фамилию покупателя
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Процедура присвоения фамилии покупателя
     * @param lastname фамилия покупателя
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
