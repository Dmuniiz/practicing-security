package com.shopping.security.controllers;

import com.shopping.security.domain.user.AuthenticationDTO;
import com.shopping.security.domain.user.LoginResponseDTO;
import com.shopping.security.domain.user.RegisterDTO;
import com.shopping.security.domain.services.UserService;
import com.shopping.security.domain.user.User;
import com.shopping.security.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        //VALIDAR SE O LOGIN EXISTE, E VERIFICAR SE A SENHA TA SALVA
        //SENHA SALVA EM HASH
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        System.out.println(userNamePassword);

        var auth = this.manager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal()); //get principal(user)

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(!userService.checkUserLogin(data)){
            return ResponseEntity.badRequest().build();
        }else {
            userService.registerUser(data);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Page<AuthenticationDTO>> getAllUsers(Pageable paginacao){
        Page page = userService.getUsers(paginacao);
        return ResponseEntity.ok(page);
    }

}
