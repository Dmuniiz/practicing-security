package com.shopping.security.domain.services;

import com.shopping.security.domain.user.AuthenticationDTO;
import com.shopping.security.domain.user.RegisterDTO;
import com.shopping.security.domain.user.User;
import com.shopping.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterDTO data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());
        this.userRepository.save(user);
    }

    public Boolean checkUserLogin(RegisterDTO data){
        if(this.userRepository.findByLogin(data.login()) != null){
            return false;
        }
        return true;
    }

    public Page<AuthenticationDTO> getUsers(Pageable pag){
        return userRepository.findAll(pag).map(s -> new AuthenticationDTO(s));
    }

}
