package dto.response.statistic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StatCustomerResults {
    String name; // Фамилия и имя покупателя
    List<Purchase> purchases;
    BigDecimal totalExpenses;

    public StatCustomerResults() {
        this.purchases = new ArrayList<>();
    }

    public StatCustomerResults(String name, BigDecimal totalExpenses) {
        this.name = name;
        this.totalExpenses = totalExpenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    @Override
    public String toString() {
        return "\nStatCustomerResults{" +
                "name='" + name + '\'' +
                ", purchases=" + purchases +
                ", \ntotalExpenses=" + totalExpenses +
                '}';
    }
}
