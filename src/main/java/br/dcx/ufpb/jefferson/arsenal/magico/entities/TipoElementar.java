package br.dcx.ufpb.jefferson.arsenal.magico.entities;

import java.io.Serializable;

public enum TipoElementar implements Serializable {
    FOGO("Fogo"),
    TERRA("Terra"),
    AGUA("Água"),
    GELO("Gelo"),
    AR("Ar");

    private String valor;
    private final int QUANT_MAX = 5;

    TipoElementar(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
