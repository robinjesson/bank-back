package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.PeriodUnit;

import java.time.LocalDate;

public class EntryResponse {
    private Long id;
    private String title;
    private Double amount;
    private LocalDate date;

    public EntryResponse(Long id, String title, Double amount, LocalDate date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public EntryResponse(Entry entry) {
        this.id = entry.getId();
        this.title = entry.getTitle();
        this.amount = entry.getAmount();
        this.date = entry.getDate();
    }

    public EntryResponse() {
    }

    public Long getId() {
        return id;
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
}
