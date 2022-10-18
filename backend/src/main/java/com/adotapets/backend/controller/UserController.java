package com.adotapets.backend.controller;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.response.ResponseHandler;
import com.adotapets.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<Object> create(@RequestBody UserForm userForm) {
        UserDto user = userService.createUser(userForm);
        HttpStatus status = user != null ? HttpStatus.CREATED : HttpStatus.MULTI_STATUS;
        return ResponseHandler.generateResponse("", status, user);
    }

    @GetMapping
    public ResponseEntity<Object> list() {
        List<UserDto> users = userService.findAllUsers();
        HttpStatus status = users.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseHandler.generateResponse("", status, users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        UserDto user = userService.findUserById(id);
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseHandler.generateResponse("", status, user);
    }

}
