package com.adotapets.backend.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USUARIO")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "Senha", nullable = false, length = 255)
    private String senha;

    @Column(name = "DataCadastro", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    @Lob
    @Column(name = "FotoPerfil")
    private byte[] fotoPerfil;

    @Column(name = "CNPJ", nullable = true, length = 14)
    private String cnpj;

    @Column(name = "Bloqueado")
    private boolean bloqueado;

    @OneToMany(mappedBy = "responsavel", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pet> pets;

}
