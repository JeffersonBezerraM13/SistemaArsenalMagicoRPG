package br.dcx.ufpb.jefferson.arsenal.magico.system;

import br.dcx.ufpb.jefferson.arsenal.magico.entities.Magia;
import br.dcx.ufpb.jefferson.arsenal.magico.entities.TipoElementar;
import br.dcx.ufpb.jefferson.arsenal.magico.exception.MagiaInexistenteException;
import br.dcx.ufpb.jefferson.arsenal.magico.exception.MagiaJaExisteException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Interface para
 */
public interface ArsenalMagico {
    /**
     * Cadastra uma magia
     * @param id É o ID da magia
     * @param nome É o nome da magia
     * @param tipo É o TipoElementar da magia
     * @param dano É o dano da magia [Double]
     * @param custoDeMana É o custo de mana da magia
     * @throws MagiaJaExisteException Lança essa Exception quando já há uma magia cadastrada com o mesmo ID.
     */
    void cadastrarMagia(Integer id, String nome, TipoElementar tipo, Double dano, int custoDeMana) throws MagiaJaExisteException;
    void removerMagia(Integer id) throws MagiaInexistenteException;

    /**
     *
     * @param id
     * @return Retorna uma Magia cadastrada a partir do ID.
     * @throws MagiaInexistenteException
     */
    Magia getMagia(int id) throws MagiaInexistenteException;
    List<Magia> getMagiasPorTipoElementar(TipoElementar tipoElementar);
    Collection<Magia> todasAsMagias();
    void gravarDados() throws IOException;
    void recuperarDados() throws IOException;
}
