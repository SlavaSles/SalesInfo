package dto.response.statistic;

import dto.response.Response;
import dto.response.ResponseType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatResponse extends Response {
    ResponseType type;
    Integer totalDays;
    List<StatCustomerResults> customers; // Надо упорядочить по убыванию общей стоимости покупок
    BigDecimal totalExpenses; // Всех покупателей
    BigDecimal avgExpenses; // Общие траты всех покупателей, деленные на количество покупателей

    public StatResponse() {
        this.type = ResponseType.stat;
        this.customers = new ArrayList<>();
    }

    public ResponseType getType() {
        return type;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<StatCustomerResults> getCustomers() {
        return customers;
    }

    public void setCustomers(List<StatCustomerResults> customers) {
        this.customers = customers;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(BigDecimal avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

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
