package br.dcx.ufpb.jefferson.arsenal.magico;

import br.dcx.ufpb.jefferson.arsenal.magico.futuro.EfeitoElementar;
import br.dcx.ufpb.jefferson.arsenal.magico.futuro.FormaElementar;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SistemaArsenalMagico implements ArsenalMagico{
    private final HashMap<Integer, Magia> magias;

    public SistemaArsenalMagico(){
        this.magias = new HashMap<>();
    }

    @Override
    public void cadastrarMagia(Integer id, String nome, TipoElementar tipo,Double dano, int custoDeMana) throws MagiaJaExisteException {
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
        if(this.magias.containsKey(id)){
            return this.magias.get(id);
        } else throw new MagiaInexistenteException("A magia com o id "+id+" não está cadastrada no sistema");

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
}
