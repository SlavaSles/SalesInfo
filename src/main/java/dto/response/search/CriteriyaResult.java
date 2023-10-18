package dto.response.search;

import dto.criteria.Criteriya;

import java.util.List;

/**
 * Класс, в котором приведены результаты поиска заданному критерию
 */
public class CriteriyaResult {

    /**
     * Поле - критерий поиска
     */
    private Criteriya criteria;

    /**
     * Поле - список покупателей {@link Customer}, найденных по заданному критерию
     */
    private List<Customer> results;

    /**
     * Пустой конструктор класса
     */
    public CriteriyaResult() {
    }

    /**
     * Конструктор класса с параметрами
     * @param criteria критерий поиска
     * @param results список покупателей, найденных по заданному критерию
     */
    public CriteriyaResult(Criteriya criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    /**
     * Функция получения критерия поиска
     * @return возвращает критерий поиска
     */
    public Criteriya getCriteria() {
        return criteria;
    }

    /**
     * Процедура присвоения критерия поиска
     * @param criteria критерий поиска
     */
    public void setCriteria(Criteriya criteria) {
        this.criteria = criteria;
    }

    /**
     * Функция получения списка покупателей, найденных по заданному критерию
     * @return возвращает список покупателей, найденных по заданному критерию
     */
    public List<Customer> getResults() {
        return results;
    }

    /**
     * Процедура присвоения списка покупателей, найденных по заданному критерию
     * @param results список покупателей, найденных по заданному критерию
     */
    public void setResults(List<Customer> results) {
        this.results = results;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "\nCriteriyaResult{" +
                "criteriya=" + criteria +
                ", \nresults=" + results +
                '}';
    }
}
