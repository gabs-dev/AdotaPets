package com.adotapets.backend.controller;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto create(@RequestBody UserForm userForm) {
        return userService.createUser(userForm);
    }

    @GetMapping
    public List<UserDto> list() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

}
