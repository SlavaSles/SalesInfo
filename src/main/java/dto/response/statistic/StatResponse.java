package dto.response.statistic;

import dto.response.Response;
import dto.response.ResponseType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализована структура ответа на запрос статистики за указанный период времени в соответствие
 * со спецификацией API
 */
public class StatResponse extends Response {

    /**
     * Поле - тип ответа "stat" {@link ResponseType}
     */
    private ResponseType type;

    /**
     * Поле - общее количество дней за указанный период из двух дат, включительно, без учета выходных дней
     */
    private Integer totalDays;

    /**
     * Поле - список покупателей в порядке убывания их общей стоимости покупок {@link StatCustomerResults}
     */
    private List<StatCustomerResults> customers;

    /**
     * Поле - сумма покупок всех покупателей за указанный период
     */
    private BigDecimal totalExpenses;

    /**
     * Поле - средние затраты всех покупателей за указанный период
     */
    private BigDecimal avgExpenses; // Сумма покупок всех покупателей за указанный период, деленная на количество покупателей

    /**
     * Конструктор класса
     */
    public StatResponse() {
        this.type = ResponseType.stat;
        this.customers = new ArrayList<>();
    }

    /**
     * Функция получения типа ответа
     * @return возвращает тип ответа "stat"
     */
    public ResponseType getType() {
        return type;
    }

    /**
     * Функция получения количества дней за указанный период из двух дат, включительно, без учета выходных дней
     * @return возвращает количество дней за указанный период из двух дат, включительно, без учета выходных дней
     */
    public Integer getTotalDays() {
        return totalDays;
    }

    /**
     * Процедура присвоения количества дней за указанный период из двух дат, включительно, без учета выходных дней
     * @param totalDays количество дней за указанный период из двух дат, включительно, без учета выходных дней
     */
    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    /**
     * Функция получения списка покупателей в порядке убывания их общей стоимости покупок
     * @return возвращает список покупателей в порядке убывания их общей стоимости покупок
     */
    public List<StatCustomerResults> getCustomers() {
        return customers;
    }

    /**
     * Процедура присвоения списка покупателей в порядке убывания их общей стоимости покупок
     * @param customers список покупателей в порядке убывания их общей стоимости покупок
     */
    public void setCustomers(List<StatCustomerResults> customers) {
        this.customers = customers;
    }

    /**
     * Функция получения суммы покупок всех покупателей за указанный период
     * @return возвращает сумму покупок всех покупателей за указанный период
     */
    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    /**
     * Процедура присвоения суммы покупок всех покупателей за указанный период
     * @param totalExpenses сумма покупок всех покупателей за указанный период
     */
    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    /**
     * Функция получения средних затрат всех покупателей за указанный период
     * @return возвращает средние затраты всех покупателей за указанный период
     */
    public BigDecimal getAvgExpenses() {
        return avgExpenses;
    }

    /**
     * Процедура присвоения средних затраты всех покупателей за указанный период
     * @param avgExpenses средние затраты всех покупателей за указанный период
     */
    public void setAvgExpenses(BigDecimal avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "StatResponse{" +
                "type=" + type +
                ", totalDays=" + totalDays +
                ", customers=" + customers +
                ", \ntotalExpenses=" + totalExpenses +
                ", avgExpenses=" + avgExpenses +
                '}';
    }
}
