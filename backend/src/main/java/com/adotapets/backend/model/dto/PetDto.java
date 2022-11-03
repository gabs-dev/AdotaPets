package com.adotapets.backend.model.dto;

import com.adotapets.backend.model.Pet;
import com.adotapets.backend.model.User;
import com.adotapets.backend.model.enums.Sexo;
import com.adotapets.backend.model.enums.TipoPet;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PetDto {

    private Long id;
    private String nome;
    private String raca;
    private TipoPet tipoPet;
    private double peso;
    private String idade;
    private Sexo sexo;
    private boolean vacinado;
    private boolean castrado;
    private String informacoesAdicionais;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAdocao;
    private boolean emAdocao;
    private boolean adotado;
    private Long responsavelId;

    public PetDto() {

    }

    public PetDto(Long id, String nome, String raca, TipoPet tipoPet, double peso, String idade, Sexo sexo,
                  boolean vacinado, boolean castrado, String informacoesAdicionais, LocalDateTime dataCadastro,
                  LocalDateTime dataAdocao, boolean emAdocao, boolean adotado, User responsavel) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.tipoPet = tipoPet;
        this.peso = peso;
        this.idade = idade;
        this.sexo = sexo;
        this.vacinado = vacinado;
        this.castrado = castrado;
        this.informacoesAdicionais = informacoesAdicionais;
        this.dataCadastro = dataCadastro;
        this.dataAdocao = dataAdocao;
        this.emAdocao = emAdocao;
        this.adotado = adotado;
        this.responsavelId = responsavel.getId();
    }

    public PetDto(Pet pet) {
        this.id = pet.getId();
        this.nome = pet.getNome();
        this.raca = pet.getRaca();
        this.tipoPet = pet.getTipoPet();
        this.peso = pet.getPeso();
        this.idade = pet.getIdade();
        this.sexo = pet.getSexo();
        this.vacinado = pet.isVacinado();
        this.castrado = pet.isCastrado();
        this.informacoesAdicionais = pet.getInformacoesAdicionais();
        this.dataCadastro = pet.getDataCadastro();
        this.dataAdocao = pet.getDataAdocao();
        this.emAdocao = pet.isEmAdocao();
        this.adotado = pet.isAdotado();
        this.responsavelId = pet.getResponsavel().getId();
    }
}
