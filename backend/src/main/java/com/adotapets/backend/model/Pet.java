package com.adotapets.backend.model;

import com.adotapets.backend.model.enums.Sexo;
import com.adotapets.backend.model.enums.TipoPet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "Raca", length = 255)
    private String raca;

    @Column(name = "TipoPet", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPet tipoPet;

    @Column(name = "Peso")
    private double peso;

    @Column(name = "Idade", length = 30)
    private String idade;

    @Column(name = "Sexo")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "Vacinado")
    private boolean vacinado;

    @Column(name = "Castrado")
    private boolean castrado;

    @Column(name = "InformacoesAdicionais", length = 500)
    private String informacoesAdicionais;

    @Column(name = "DataCadastro", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    @Column(name = "DataAdotacao")
    private LocalDateTime dataAdocao;

    @Column(name = "EmAdocao")
    private boolean emAdocao;

    @Column(name = "Adotado")
    private boolean adotado;

    @ManyToOne
    @JoinColumn(name = "ResponsavelId")
    @JsonBackReference
    private User responsavel;

}

