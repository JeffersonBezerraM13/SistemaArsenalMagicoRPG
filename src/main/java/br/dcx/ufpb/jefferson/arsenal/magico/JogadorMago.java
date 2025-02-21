package br.dcx.ufpb.jefferson.arsenal.magico;

public class JogadorMago {
    private String nome;
    private int vida;
    private int mana;

    public JogadorMago(String nome, int vida, int mana) {
        this.nome = nome;
        this.vida = vida;
        this.mana = mana;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
