package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.Account;
import fr.robinjesson.mybank.model.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserResponse {
    private Long id;
    private String username;

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponse() {
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
