package com.shopping.security.repositories;

import com.shopping.security.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    //vai ser usado pelo spring security
    UserDetails findByLogin(String login);

}
