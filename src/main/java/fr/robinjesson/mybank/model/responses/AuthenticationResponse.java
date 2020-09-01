package fr.robinjesson.mybank.model.responses;

import fr.robinjesson.mybank.model.entities.User;

public class AuthenticationResponse {
    private final String token;
    private final UserResponse user;

    public AuthenticationResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }
}
