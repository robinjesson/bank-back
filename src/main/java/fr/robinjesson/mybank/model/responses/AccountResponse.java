package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Account;

import java.time.LocalDate;

public class AccountResponse {
    private Long id;
    private String name;
    private Double total;
    private LocalDate update;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String name, Double total, LocalDate update) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.update = update;
    }

    public AccountResponse(Account account) {
        this(account.getId(), account.getName(), account.getTotal(), account.getLastUpdate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

    public LocalDate getUpdate() {
        return update;
    }
}
