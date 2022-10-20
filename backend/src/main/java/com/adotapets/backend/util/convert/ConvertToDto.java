package com.adotapets.backend.util.convert;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertToDto {

    public static UserDto user(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setCnpj(user.getCnpj());
        dto.setBloqueado(user.isBloqueado());
        return dto;
    }

    public static List<UserDto> userList(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

}
