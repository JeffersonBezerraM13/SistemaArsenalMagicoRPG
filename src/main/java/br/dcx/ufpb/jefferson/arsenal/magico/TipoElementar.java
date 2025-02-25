package br.dcx.ufpb.jefferson.arsenal.magico;

import java.util.ArrayList;
import java.util.List;

public enum TipoElementar {
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
