package com.shopping.security.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
