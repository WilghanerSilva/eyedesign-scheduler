package com.eyedesign.scheduler.controllers;

import com.eyedesign.scheduler.domain.user.AuthenticationDTO;
import com.eyedesign.scheduler.domain.user.RegisterDTO;
import com.eyedesign.scheduler.domain.user.ReturnUserDTO;
import com.eyedesign.scheduler.domain.user.User;
import com.eyedesign.scheduler.infra.security.TokenService;
import com.eyedesign.scheduler.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public ResponseEntity<ReturnUserDTO> register(@RequestBody @Valid RegisterDTO data) throws Exception {
        var createdUser = this.service.createUser(data);
        return ResponseEntity.ok(createdUser);
    }
    ;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok(token);
    }
}
