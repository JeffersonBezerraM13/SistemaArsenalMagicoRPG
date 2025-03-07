package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class BordeLayoutTest {
    private JFrame janela;

    public BordeLayoutTest(){
        initizalite();
    }
    public void initizalite(){
        this.janela = new JFrame();
        janela.setVisible(true);
        janela.setTitle("Janela BorderLayout");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        janela.setSize(screenSize.getSize());
        janela.setLocationRelativeTo(null); //centro
        janela.setResizable(true);

        //O que é borderLayout
        //se for vazio (BorderLauout()) não vai ter espaço entre os botões
        //tbm pode ser inicializado por fora e setando o vertical gap e horizontal gap atravez de metodos
        janela.setLayout(new BorderLayout(50,50));
        //this.frame.add(new Button("EAST"),BorderLayout.EAST);
        janela.add(new Button("NORTH"),BorderLayout.NORTH);
        janela.add(new Button("WEST"),BorderLayout.WEST);
        janela.add(new Button("SOUTH"),BorderLayout.SOUTH);
        janela.add(new Button("CENTER"),BorderLayout.CENTER);
        janela.add(new Button("DEITADO NA ESQUERDA"),BorderLayout.AFTER_LAST_LINE);
        janela.add(new Button("DIREITONA"),BorderLayout.AFTER_LINE_ENDS);

    }
    public static void main(String [] args){
        BordeLayoutTest janela = new BordeLayoutTest();
    }
}
