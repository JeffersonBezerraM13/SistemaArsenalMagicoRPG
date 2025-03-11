package br.dcx.ufpb.jefferson.arsenal.magico.system;

import br.dcx.ufpb.jefferson.arsenal.magico.entities.Magia;
import br.dcx.ufpb.jefferson.arsenal.magico.entities.TipoElementar;
import br.dcx.ufpb.jefferson.arsenal.magico.exception.MagiaInexistenteException;
import br.dcx.ufpb.jefferson.arsenal.magico.exception.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.gravador.GravadorDeDados;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SistemaArsenalMagico implements ArsenalMagico {
    private HashMap<Integer, Magia> magias;
    private GravadorDeDados gravadorDeDados;

    public SistemaArsenalMagico(){
        this.magias = new HashMap<>();
        this.gravadorDeDados = new GravadorDeDados();
    }

    @Override
    public void cadastrarMagia(Integer id, String nome, TipoElementar tipo, Double dano, int custoDeMana) throws MagiaJaExisteException {
        if(!this.magias.containsKey(id)){
            this.magias.put(id, new Magia(id,nome,tipo,dano,custoDeMana));
        } else throw new MagiaJaExisteException("A magia com o ID "+id+" já está cadastrado no sistema");
    }

    @Override
    public void removerMagia(Integer id) throws MagiaInexistenteException {
        if(this.magias.containsKey(id)){
            this.magias.remove(id);
        } else throw new MagiaInexistenteException("A magia do ID "+id+" que você quer remover não existe");
    }

    @Override
    public Magia getMagia(int id) throws MagiaInexistenteException {
        if(this.magias.containsKey(id)) return this.magias.get(id);
        else throw new MagiaInexistenteException("A magia com o id "+id+" não está cadastrada no sistema");
    }

    @Override
    public List<Magia> getMagiasPorTipoElementar(TipoElementar tipoElementar) {
        List<Magia> magiasPorTipoElementar = new LinkedList<>();
        for(Magia m: this.magias.values()){
            if(m.getTipo() == tipoElementar){
                magiasPorTipoElementar.add(m);
            }
        }
        return magiasPorTipoElementar;
    }

    @Override
    public Collection<Magia> todasAsMagias() {
        return this.magias.values();
    }

    @Override
    public void gravarDados() throws IOException {
        this.gravadorDeDados.gravaMagia(this.magias);
    }

    @Override
    public void recuperarDados() throws IOException {
        this.magias = this.gravadorDeDados.recuperaMagias();
    }
}
