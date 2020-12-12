package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.PeriodUnit;

import java.time.LocalDate;

public class EntryResponse {
    private Long id;
    private String title;
    private Double amount;
    private LocalDate date;
    private Long entryPeriodId;

    public EntryResponse(Long id, String title, Double amount, LocalDate date, Long entryPeriodId) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.entryPeriodId = entryPeriodId;
    }

    public EntryResponse(Entry entry) {
        this.id = entry.getId();
        this.title = entry.getTitle();
        this.amount = entry.getAmount();
        this.date = entry.getDate();
        if(entry.getEntryPeriod() != null)
            this.entryPeriodId = entry.getEntryPeriod().getId();
        else
            this.entryPeriodId = null;
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

    public Long getEntryPeriodId() {
        return entryPeriodId;
    }
}
