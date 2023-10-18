package dto.response.statistic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором приводится информация по покупателю и список всех уникальных товаров, купленных им за указанный
 * период времени, упорядоченный по убыванию суммарной стоимости
 */
public class StatCustomerResults {

    /**
     * Поле - фамилия и имя покупателя в едином поле
     */
    private String name;

    /**
     * Поле - список купленных товаров в порядке убывания суммарной стоимости {@link Purchase}
     */
    private List<Purchase> purchases;

    /**
     * Поле - общая стоимость всех покупок этого покупателя за указанный период
     */
    private BigDecimal totalExpenses;

    /**
     * Пустой конструктор класса
     */
    public StatCustomerResults() {
        this.purchases = new ArrayList<>();
    }

    /**
     * Конструктор класса с параметрами
     * @param name фамилия и имя покупателя
     * @param totalExpenses общая стоимость всех покупок этого покупателя за указанный период
     */
    public StatCustomerResults(String name, BigDecimal totalExpenses) {
        this.name = name;
        this.totalExpenses = totalExpenses;
    }

    /**
     * Функция получения фамилии и имени покупателя
     * @return возвращает фамилию и имя покупателя
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура присвоения фамилии и имени покупателю
     * @param name фамилия и имя покупателя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения списка купленных товаров в порядке убывания суммарной стоимости
     * @return возвращает список купленных товаров в порядке убывания суммарной стоимости
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * Процедура присвоения списка купленных товаров в порядке убывания суммарной стоимости
     * @param purchases список купленных товаров в порядке убывания суммарной стоимости
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    /**
     * Функция получения общей стоимости всех покупок этого покупателя за указанный период
     * @return возвращает общую стоимость всех покупок этого покупателя за указанный период
     */
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    /**
     * Процедура присвоения общей стоимости всех покупок этого покупателя за указанный период
     * @param totalExpenses общая стоимость всех покупок этого покупателя за указанный период
     */
    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "\nStatCustomerResults{" +
                "name='" + name + '\'' +
                ", purchases=" + purchases +
                ", \ntotalExpenses=" + totalExpenses +
                '}';
    }
}
