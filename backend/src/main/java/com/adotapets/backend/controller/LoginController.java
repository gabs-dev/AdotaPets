package com.adotapets.backend.controller;

import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.LoginForm;
import com.adotapets.backend.response.ResponseHandler;
import com.adotapets.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) {
        UserDto dto = service.login(loginForm);
        HttpStatus status = dto != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseHandler.generateResponse("", status, dto);
    }

}
