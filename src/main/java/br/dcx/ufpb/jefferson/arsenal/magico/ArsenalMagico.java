package br.dcx.ufpb.jefferson.arsenal.magico;

import java.util.Collection;
import java.util.List;

public interface ArsenalMagico {
    void cadastrarMagia(Integer id, String nome, TipoElementar tipo, Double dano, int custoDeMana) throws MagiaJaExisteException;
    void removerMagia(Integer id) throws MagiaInexistenteException;
    Magia getMagia(int id) throws MagiaInexistenteException;
    List<Magia> getMagiasPorTipoElementar(TipoElementar tipoElementar);
    Collection<Magia> todasAsMagias();
}
