package com.adotapets.backend.model.dto;

import com.adotapets.backend.model.User;
import lombok.Data;
import org.hibernate.cfg.NotYetImplementedException;

@Data
public class UserDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    // fotoperfil
    private String cnpj;
    private boolean bloqueado;

    public UserDto() {

    }

    public UserDto(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cnpj = user.getCnpj();
        this.bloqueado = user.isBloqueado();
    }

}
