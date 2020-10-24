package fr.robinjesson.mybank.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String title;
    protected Double amount;
    protected LocalDate date;
    @ManyToOne
    @JoinColumn(name = "entry_period_id", nullable = true)
    private EntryPeriod entryPeriod;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    protected Account account;

    public Entry() {
    }

    public Entry(String title, Double amount, LocalDate date, Account account) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public Entry(String title, Double amount, LocalDate date, EntryPeriod entryPeriod, Account account) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.entryPeriod = entryPeriod;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EntryPeriod getEntryPeriod() {
        return entryPeriod;
    }

    public void setEntryPeriod(EntryPeriod entryPeriod) {
        this.entryPeriod = entryPeriod;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", entryPeriod=" + entryPeriod +
                ", account=" + account +
                '}';
    }
}
