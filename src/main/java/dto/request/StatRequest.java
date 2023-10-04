package dto.request;

import java.time.LocalDate;

public class StatRequest {
    private LocalDate startDate;
    private LocalDate endDate;

    public StatRequest() {
    }

    public StatRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StatRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
