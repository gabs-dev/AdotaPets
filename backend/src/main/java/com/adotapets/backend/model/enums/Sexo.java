package com.adotapets.backend.model.enums;

public enum Sexo {

    MACHO(1),
    FEMEA(2);

    private final int valor;

    Sexo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static Sexo getSexo(int valor) {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.valor == valor)
                return sexo;
        }
        return null;
    }
}
