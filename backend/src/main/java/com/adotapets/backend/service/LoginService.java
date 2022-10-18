package com.adotapets.backend.service;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.LoginForm;
import com.adotapets.backend.repository.UserRepository;
import com.adotapets.backend.util.Security;
import com.adotapets.backend.util.convert.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public UserDto login(LoginForm loginForm) {
        User user = userRepository.findUserByEmail(loginForm.getEmail());
        if (user == null)
            return null;
        String pass = Security.encryptPassword(loginForm.getSenha());
        if (!pass.equals(user.getSenha()))
            return null;
        return ConvertToDto.user(user);
    }

}
