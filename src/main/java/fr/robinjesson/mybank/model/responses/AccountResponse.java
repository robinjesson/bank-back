package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.Entry;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountResponse {
    private Long id;
    private String name;
    private Double total;
    private LocalDate update;
    private Set<EntryResponse> lastEntries;


    public AccountResponse() {
    }

    public AccountResponse(Long id, String name, Double total, LocalDate update, Set<EntryResponse> lastEntries) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.update = update;
        this.lastEntries = lastEntries;
    }

    public AccountResponse(Account account) {
        this(account.getId(), account.getName(), account.getTotal(), account.getLastUpdate(), new HashSet<>());
        int count = 0;
        for(Entry e: account.getEntries().stream().sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())).collect(Collectors.toList())) {
            this.lastEntries.add(new EntryResponse(e));
            if(count++ == 2)
                break;
        }
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

    public Set<EntryResponse> getLastEntries() {
        return lastEntries;
    }
}
