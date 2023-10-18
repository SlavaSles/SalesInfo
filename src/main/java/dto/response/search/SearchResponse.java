package dto.response.search;

import dto.response.Response;
import dto.response.ResponseType;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, в котором реализована структура ответа поиска покупателей в соответствие со спецификацией API
 */
public class SearchResponse extends Response {

    /**
     * Поле - тип ответа "search" {@link ResponseType}
     */
    private ResponseType type;

    /**
     * Поле - список результатов поиска покупателей {@link CriteriyaResult}
     */
    private List<CriteriyaResult> results;

    /**
     * Конструктор класса
     */
    public SearchResponse() {
        this.type = ResponseType.search;
        this.results = new ArrayList<>();
    }

    /**
     * Функция получения типа ответа
     * @return возвращает тип ответа "search"
     */
    public ResponseType getType() {
        return type;
    }

    /**
     * Функция получения списка результатов поиска покупателей
     * @return возвращает список результатов поиска покупателей
     */
    public List<CriteriyaResult> getResults() {
        return results;
    }

    /**
     * Процедура присвоения списка результатов поиска покупателей
     * @param results список результатов поиска покупателей
     */
    public void setResults(List<CriteriyaResult> results) {
        this.results = results;
    }

    /**
     * Функция преобразования полей объекта в строку
     * @return возвращает содержимое объекта в виде строки
     */
    @Override
    public String toString() {
        return "SearchResponse{" +
                "type=" + type +
                ", criteriyaResults=" + results +
                '}';
    }
}
