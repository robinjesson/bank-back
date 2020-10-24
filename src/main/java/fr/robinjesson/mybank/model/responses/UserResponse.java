package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Entry;
import fr.robinjesson.mybank.model.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private Long id;
    private String username;
    private List<EntryResponse> periodicEntries;

    public UserResponse(Long id, String username, List<Entry> periodicEntries) {
        this.id = id;
        this.username = username;
        this.periodicEntries = periodicEntries.stream().map(entry -> new EntryResponse(entry)).collect(Collectors.toList());
    }

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponse() {
    }

    public UserResponse(User user, List<Entry> periodicEntries) {
        this(user.getId(), user.getUsername(), periodicEntries);
    }

    public UserResponse(User user) {
        this(user.getId(), user.getUsername());
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
