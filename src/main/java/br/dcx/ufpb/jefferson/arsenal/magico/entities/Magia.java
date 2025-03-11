package br.dcx.ufpb.jefferson.arsenal.magico.entities;

import java.io.Serializable;
import java.util.Objects;

public class Magia implements Serializable {
    private Integer id;
    private String nome;
    private TipoElementar tipo;
    private Double dano;
    private int custoDeMana;

    public Magia(Integer id, String nome, TipoElementar tipo,Double dano, int custoDeMana) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.dano = dano;
        this.custoDeMana = custoDeMana;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoElementar getTipo() {
        return tipo;
    }

    public void setTipo(TipoElementar tipo) {
        this.tipo = tipo;
    }

    public Double getDano() {
        return dano;
    }

    public void setDano(Double dano) {
        this.dano = dano;
    }

    public int getCustoDeMana() {
        return custoDeMana;
    }

    public void setCustoDeMana(int custoDeMana) {
        this.custoDeMana = custoDeMana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magia magia = (Magia) o;
        return Objects.equals(id, magia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return ("Magia "+this.id+
                ":\nNome: " + this.nome +
                ", tipo: " + this.tipo +
                ", dano: " + this.dano +
                ", custoDeMana: "+this.custoDeMana);
    }
}
