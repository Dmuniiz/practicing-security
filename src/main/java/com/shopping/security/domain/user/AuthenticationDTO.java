package com.shopping.security.domain.user;

public record AuthenticationDTO(String login, String password) {

    public AuthenticationDTO(User user) {
        this(user.getUsername(), user.getPassword());
    }

}
