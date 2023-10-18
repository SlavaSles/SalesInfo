package dto.request;

import dto.criteria.Criteriya;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализована структура запроса поиска покупателей в соответствие со спецификацией API
 */
public class SearchRequest extends Request {
    /**
     * Поле - список критериев поиска {@link Criteriya}
     */
    private List<Criteriya> criteriyas = new ArrayList<>();

    /**
     * Конструктор класса
     */
    public SearchRequest() {
    }

    /**
     * Функция получения критериев поиска из запроса
     * @return возвращает список критериев поиска
     */
    public List<Criteriya> getCriteriyas() {
        return criteriyas;
    }

    /**
     * Процедура присвоения списка критериев поиска
     * @param criteriyas список критериев поиска
     */
    public void setCriteriyas(List<Criteriya> criteriyas) {
        this.criteriyas = criteriyas;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "SearchRequest{" +
                "criterias=" + criteriyas +
                '}';
    }
}
