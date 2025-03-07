package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutTest {
    private JFrame janela;
    private JPanel painel;

    public FlowLayoutTest(){
        initialize();
    }
    public void initialize(){
        janela = new JFrame();
        janela.setTitle("Janela de flow layout");

        painel =  new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        painel.setBackground(new Color(0xFFE879FA, true));
        janela.setLayout(new BorderLayout());

        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null); //centro
        janela.setSize(800,500);


        janela.add(painel, BorderLayout.NORTH);

        for(int i=1 ;i<=5;i++){
            JButton button = new JButton("Botão "+ i);
            painel.add(button);
        }

    }
    public static void main(String [] args){
        FlowLayoutTest janela = new FlowLayoutTest();
    }
}
