package com.adotapets.backend.model.enums;

public enum TipoPet {

    GATO(1, "Gato"),
    CACHORRO(2, "Cachorro");
    //PASSARO(3, "Pássaro");

    private final int codigo;
    private final String descricao;

    TipoPet(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPet getTipoPet(int codigo) {
        for (TipoPet tipo : TipoPet.values()) {
            if (tipo.codigo == codigo)
                return tipo;
        }
        return null;
    }

}
