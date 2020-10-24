package fr.robinjesson.mybank.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account")
    private Set<Entry> entries;

    @OneToMany(mappedBy = "account")
    private Set<EntryPeriod> entryPeriods;

    private Double total;

    @Column(columnDefinition = "date default NOW()")
    private LocalDate lastUpdate;



    public Account(Long id, String name, User user, Set<Entry> entries, Double total, LocalDate update) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.entries = entries;
        this.total = total;
        this.lastUpdate = update;
    }

    public Account(@NonNull String name, @NonNull User user, @NonNull Double total) {
        this.name = name;
        this.user = user;
        this.total = total;
        this.lastUpdate = LocalDate.now();
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> writings) {
        this.entries = writings;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(@NonNull Double total) {
        this.total = total;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate update) {
        this.lastUpdate = update;
    }

    public Set<EntryPeriod> getEntryPeriods() {
        return entryPeriods;
    }

    public void setEntryPeriods(Set<EntryPeriod> entryPeriods) {
        this.entryPeriods = entryPeriods;
    }
}
