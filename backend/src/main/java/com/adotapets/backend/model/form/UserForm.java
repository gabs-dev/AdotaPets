package com.adotapets.backend.model.form;

import lombok.Data;

import java.util.Date;

@Data
public class UserForm {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Date dataCadastro;

    private byte[] fotoPerfil;

    private String cnpj;

}
