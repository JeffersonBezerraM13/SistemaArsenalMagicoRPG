package br.dcx.ufpb.jefferson.aprendizado;

import br.dcx.ufpb.jefferson.arsenal.magico.futuro.JogadorMago;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class testandoLambdaEStream {
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
         * O que é a API Stream?
         * A API Stream foi introduzida no Java 8 e faz parte do pacote java.util.stream.
         * Ela permite processar coleções (como listas, conjuntos e mapas) de forma declarativa e funcional,
         * usando operações como filter, map, reduce, collect, entre outras.
         *
         * A ideia principal é permitir que você trabalhe com dados de maneira mais expressiva e eficiente,
         * evitando loops explícitos e código boilerplate.
         *
         * O que são Interfaces Funcionais?
         * Interfaces funcionais são interfaces que têm apenas um método abstrato. Elas são a base para o uso de lambdas no Java. Alguns exemplos comuns de interfaces funcionais são:
         *
         * Consumer<T>: Recebe um argumento e não retorna nada (void).
         *
         * Function<T, R>: Recebe um argumento e retorna um resultado.
         *
         * Predicate<T>: Recebe um argumento e retorna um valor booleano.
         *
         * Supplier<T>: Não recebe argumentos e retorna um valor.
         *
         * Comparator<T>: Compara dois objetos e retorna um inteiro.
         *
         * Explicação do códgio abaixo:
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
        listaTal.stream().sorted(); //sorted da api stream

        List<Integer> listaDeManaManorQue50 = jogadoresMago.stream()
                .map(c-> c.getMana())
                .filter(d -> d<=50).
                collect(Collectors.toList());

        List<Integer> listaDeManaManorQue50Sugestoes = jogadoresMago.stream()
                .map(JogadorMago::getMana)
                .filter(d -> d<=50).
                toList();


        //Sorting com lambda comparando pelo tamanho do string dentro do array
        /**
         * Componentes de uma Lambda:
         * Parâmetros: São as entradas da função. Por exemplo, (s1, s2) são dois parâmetros.
         *
         * Operador de seta (->): Separa os parâmetros do corpo da função.
         *
         * Corpo: É a lógica que será executada. Pode ser uma expressão simples (como s1.length() - s2.length()) ou um bloco de código com {}.
         */
        List<String> nomes = Arrays.asList("Jefferson","Ana", "Carlos", "Bruno", "Daniela");


        nomes.sort((s1, s2) -> s1.length() - s2.length());
        nomes.sort((c1,c2) -> c1.length() - c2.length());

        System.out.println(nomes);
        /**
         * Lambdas são frequentemente usadas com interfaces funcionais, como Comparator, Runnable, Consumer, Predicate, etc.
         * Por exemplo, a interface Comparator tem um único método abstrato chamado compare, que recebe dois objetos e retorna
         * um inteiro indicando a ordem entre eles.
         *
         * O que acontece no sort da lista acima:
         *
         * 1. O método sort espera um Comparator.
         * 2. A lambda (s1, s2) -> s1.length() - s2.length() implementa o método compare do Comparator.
         * 3. A lambda compara duas strings (s1 e s2) pelo seu comprimento (length()).
         * 4. O resultado é uma lista ordenada pelo tamanho das strings.
         *
         * Didaticamente: Lambdas são como "ordens rápidas" que você dá ao Java para fazer algo sem escrever muito código.
         * Tecnicamente: Lambdas são uma forma concisa de implementar interfaces funcionais,
         * reduzindo a verbosidade do código e permitindo programação funcional.
         *
         */
        //Sem lambda
        nomes.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        nomes.sort((s1, s2) -> s1.length() - s2.length());
    }
}
