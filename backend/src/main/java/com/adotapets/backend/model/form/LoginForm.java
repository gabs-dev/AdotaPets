package com.adotapets.backend.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    @NotBlank(message = "O email não pode estar em branco")
    private String email;
    @NotBlank(message = "A senha não pode ser vazia")
    private String senha;

}
