package dto.response.statistic;

import java.math.BigDecimal;

public class Purchase {
    private String name; // ProductName
    private BigDecimal expenses;

    public Purchase() {
    }

    public Purchase(String name, BigDecimal expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "\nPurchase{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}