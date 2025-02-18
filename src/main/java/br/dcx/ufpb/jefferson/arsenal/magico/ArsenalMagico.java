package br.dcx.ufpb.jefferson.arsenal.magico;

import java.util.Collection;

public interface ArsenalMagico {
    void cadastrarMagia(Integer id, String nome, TipoElementar tipo, Double dano, int custoDeMana) throws MagiaJaExisteException;
    void removerMagia(Integer id) throws MagiaInexistenteException;
    Magia getMagia(int id) throws MagiaInexistenteException;
    Collection<Magia> todasAsMagias();
}
