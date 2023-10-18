package dto.request;

import java.time.LocalDate;

/**
 * Класс, в котором реализована структура запроса статистики в соответствие со спецификацией API
 */
public class StatRequest extends Request {

    /**
     * Поле - дата начала периода для вывода статистики
     */
    private LocalDate startDate;

    /**
     * Поле - дата окончания периода для вывода статистики
     */
    private LocalDate endDate;

    /**
     * Пустой конструктор класса
     */
    public StatRequest() {
    }

    /**
     * Конструктор класса с параметрами
     * @param startDate дата начала периода для вывода статистики
     * @param endDate дата окончания периода для вывода статистики
     */
    public StatRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Функция получения даты начала периода для вывода статистики
     * @return возвращает дату начала периода для вывода статистики
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Процедура присвоения даты начала периода для вывода статистики
     * @param startDate дата начала периода для вывода статистики
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Функция получения даты окончания периода для вывода статистики
     * @return возвращает дату окончания периода для вывода статистики
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Процедура присвоения даты начала окончания для вывода статистики
     * @param endDate дата окончания периода для вывода статистики
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "StatRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
