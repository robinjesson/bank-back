package fr.robinjesson.mybank.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class EntryPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double amount;
    private LocalDate begin;
    private LocalDate end;
    private PeriodUnit periodUnit;
    private Integer period;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public EntryPeriod(String title, Double amount, LocalDate begin, LocalDate end, PeriodUnit periodUnit, Integer period, Account account) {
        this.title = title;
        this.amount = amount;
        this.begin = begin;
        this.end = end;
        this.periodUnit = periodUnit;
        this.period = period;
        this.account = account;
    }

    public EntryPeriod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public PeriodUnit getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(PeriodUnit periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "EntryPeriod{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", periodUnit=" + periodUnit +
                ", period=" + period +
                ", account=" + account +
                '}';
    }
}
