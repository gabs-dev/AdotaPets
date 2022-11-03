package com.adotapets.backend.model.form;

import com.adotapets.backend.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PetForm {

    private Long id;

    private String nome;

    private String raca;

    private int tipoPet;

    private double peso;

    private String idade;

    private int sexo;

    private boolean vacinado;

    private boolean castrado;

    private String informacoesAdicionais;

    private LocalDateTime dataAdocao;

    private boolean emAdocao;

    private boolean adotado;

    private Long responsavelId;

}
