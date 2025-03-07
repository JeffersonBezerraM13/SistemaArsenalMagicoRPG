package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class GridLayoutTest {
    private JFrame janela;
    private JPanel painel;

    public GridLayoutTest(){
        initialize();
    }
    public void initialize(){
        janela = new JFrame();

        janela.setTitle("Grid layout teste");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(500,500);
        janela.setLocationRelativeTo(null);
        /**
         * Se não for mecionada o tamanho do grid irá adicioncar até preencher
         */
        painel = new JPanel(new GridLayout(0,2,10,10)); //os dois ultimos valores são os gaps

        for(int i=1;i<=20;i++){
            JButton botao = new JButton("Botão "+i);
            painel.add(botao);
        }
        janela.add(painel); //se não for mecionanda será adicionada no centro

        janela.pack();

        janela.setVisible(true);
    }
    public static void main(String [] args){
        GridLayoutTest janela = new GridLayoutTest();
    }
}
