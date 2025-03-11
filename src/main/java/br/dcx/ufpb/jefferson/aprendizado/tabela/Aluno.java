package br.dcx.ufpb.jefferson.aprendizado.tabela;

public class Aluno {
    private String nome;
    private String matricula;
    private int idade;
    private String genero;

    public Aluno(String nome, String matricula, int idade, String genero) {
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
        this.genero = genero;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}