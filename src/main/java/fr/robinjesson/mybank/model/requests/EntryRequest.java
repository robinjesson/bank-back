package fr.robinjesson.mybank.model.requests;

import fr.robinjesson.mybank.model.entities.PeriodUnit;

import java.time.LocalDate;

public class EntryRequest {
    private String title;
    private Double amount;
    private LocalDate date;
    private LocalDate end;
    private PeriodUnit periodUnit;
    private Integer period;
    private Long accountId;

    public EntryRequest(String title, Double amount, LocalDate date, LocalDate end, PeriodUnit periodUnit, Integer period, Long accountId) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.end = end;
        this.periodUnit = periodUnit;
        this.period = period;
        this.accountId = accountId;
    }

    public EntryRequest(String title, Double amount, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public EntryRequest() {
    }

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getEnd() {
        return end;
    }

    public PeriodUnit getPeriodUnit() {
        return periodUnit;
    }

    public Integer getPeriod() {
        return period;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "EntryRequest{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", end=" + end +
                ", periodUnit=" + periodUnit +
                ", period=" + period +
                ", accountId=" + accountId +
                '}';
    }
}
