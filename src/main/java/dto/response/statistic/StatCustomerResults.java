package dto.response.statistic;

import java.math.BigDecimal;
import java.util.List;

public class StatCustomerResults {
    String name; // Фамилия и имя покупателя
    List<Purchase> purchases;
    BigDecimal totalExpenses;
}
