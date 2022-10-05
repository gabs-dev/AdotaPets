package com.adotapets.backend.service;

import com.adotapets.backend.model.User;
import com.adotapets.backend.model.dto.UserDto;
import com.adotapets.backend.model.form.UserForm;
import com.adotapets.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createUser(UserForm form) {
        User user = convertToBusiness(form);
        if (user.getId() == null)
            user.setDataCadastro(new Date());
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public List<UserDto> findAllUsers() {
        List<User> all = userRepository.findAll();
        return convertListToDto(all);
    }

    public UserDto findUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent())
            return convertToDto(optional.get());
        return null;
    }

    private User convertToBusiness(UserForm form) {
        User user = new User();
        user.setId(form.getId() != null ? form.getId() : null);
        user.setNome(form.getNome());
        user.setEmail(form.getEmail());
        user.setSenha(encryptPassword(form.getSenha()));
        user.setCnpj(form.getCnpj());
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        dto.setCnpj(user.getCnpj());
        dto.setBloqueado(user.isBloqueado());
        return dto;
    }

    private String encryptPassword(String password) {
        String encryptedPass = "";

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest)
                hexString.append(String.format("%02x", 0xFF & b));

            encryptedPass = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encryptedPass;
    }

    private static List<UserDto> convertListToDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

}
