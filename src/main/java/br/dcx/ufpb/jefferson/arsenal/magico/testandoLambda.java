package br.dcx.ufpb.jefferson.arsenal.magico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class testandoLambda {
    public static void main(String [] args){


        List<JogadorMago> jogadoresMago = new ArrayList<>();
        jogadoresMago.add(new JogadorMago("JM1",100,100));
        jogadoresMago.add(new JogadorMago("JM2",50,100));
        jogadoresMago.add(new JogadorMago("JM3",30,100));
        jogadoresMago.add(new JogadorMago("JM4",100,100));
        jogadoresMago.add(new JogadorMago("JM5",120,100));
        jogadoresMago.add(new JogadorMago("JM6",150,100));
        jogadoresMago.add(new JogadorMago("JM7",100,100));

        //Tradicional com corpo para possíveis novos códigos
        for(JogadorMago jm: jogadoresMago){
            System.out.println(jm.getNome());
            System.out.println(jm.getVida());
        }

        //Tradicional sem corpo e com exepressão única
        for(JogadorMago jm: jogadoresMago) System.out.println(jm.getNome());

        //forEach de método com lambda dentro
        jogadoresMago.forEach(c -> System.out.println(c.getNome()));

        //metodo forEach e com lambda com corpo
        jogadoresMago.forEach(c -> { //essa setinha se chama arrow operator
            System.out.println(c.getNome());
            System.out.println(c.getVida());
        });

        ///Stream API
        /**
         * O Stream está filtando cada jogador mago (apartir da expressão lambda)
         *que tem mais de 100 de vida e contando essa quantidade para depois retornar
         * e um long que está sendo guardada numa variável long
         */
        long quantMaisDe100 =  jogadoresMago.stream().filter(c -> c.getVida() >= 100).count();
        System.out.println(quantMaisDe100 + " que veio do long");
        /**
         * O Stream esá filtrando cada jogar mago (apartir da expressão lambda)
         * que tem mais de 100 de vida e retornando um List de jogadorMago e guardando na variável jogadoresComMaisDe100
         */
        List<JogadorMago> jogadoresComMaisDe100 =  jogadoresMago.stream().filter(c -> c.getVida() >= 100).toList();
        System.out.println(jogadoresComMaisDe100.size()+" que veio do size() da lista");

        jogadoresMago.stream().filter(c -> c.getMana()>100).collect(Collectors.toList());
        jogadoresMago.stream().filter(c -> c.getVida()==100).toList();
        jogadoresMago.stream().filter(c -> c.getVida()==100).toArray();
        jogadoresMago.stream().filter(c -> c.getVida()==100).forEach(c -> System.out.println(c));
        List<String> listaTal = jogadoresMago.stream()
                .filter(c -> c.getMana() > 50)
                .map(JogadorMago::getNome) //acho que esse map tem a ver com
                .toList();
        listaTal.sort(null); //null para ordenar normal
        listaTal.sort(Comparator.reverseOrder()); //revertendo a ordem
        listaTal.stream().sorted(); //sorted da api stream

        List<Integer> listaDeManaManorQue50 = jogadoresMago.stream()
                .map(c-> c.getMana())
                .filter(d -> d<=50).
                collect(Collectors.toList());

        List<Integer> listaDeManaManorQue50Sugestoes = jogadoresMago.stream()
                .map(JogadorMago::getMana)
                .filter(d -> d<=50).
                toList();


    }
}
