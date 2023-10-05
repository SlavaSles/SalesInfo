package dto.response.statistic;

import dto.response.Response;
import dto.response.ResponseType;

import java.math.BigDecimal;
import java.util.List;

public class StatResponse extends Response {
    ResponseType type = ResponseType.STAT;
    Integer totalDays;
    List<StatCustomerResults> customers; // Надо упорядочить по убыванию общей стоимости покупок
    BigDecimal totalExpenses; // Всех покупателей
    BigDecimal avgExpenses;
}
