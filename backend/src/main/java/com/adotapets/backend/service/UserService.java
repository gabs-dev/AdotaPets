package com.adotapets.backend.service;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.repository.UserRepository;
import com.adotapets.backend.util.DateWithTimeZone;
import com.adotapets.backend.util.Security;
import com.adotapets.backend.util.convert.ConvertToBusiness;
import com.adotapets.backend.util.convert.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDto saveUser(UserForm form) {
        User user = ConvertToBusiness.user(form);
        if (user.getId() == null)
            user.setDataCadastro(DateWithTimeZone.getDateWithTimeZone());
        user.setSenha(Security.encryptPassword(form.getSenha()));
        user = repository.save(user);
        return ConvertToDto.user(user);
    }

    public List<UserDto> findAllUsers() {
        List<User> all = repository.findAll();
        return ConvertToDto.userList(all);
    }

    public UserDto findUserById(Long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent())
            return ConvertToDto.user(optional.get());
        return null;
    }

}
