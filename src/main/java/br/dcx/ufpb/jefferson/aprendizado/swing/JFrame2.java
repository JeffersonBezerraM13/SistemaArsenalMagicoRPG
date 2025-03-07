package br.dcx.ufpb.jefferson.aprendizado.swing;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class JFrame2 {
    private JFrame frame;

    /**
     * Versão mais segura pois você não consegue acessar todos os metodos do JFrame JAVA
     */
    public JFrame2 (){
        initialize();
    }
    public void initialize(){
        frame = new JFrame();
        this.frame.setTitle("JFrame2");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //fechar no X da tela

        System.out.print(Toolkit.getDefaultToolkit().getScreenSize());
        this.frame.setSize(800,500);
        this.frame.setVisible(true);
        //basicos
        this.frame.setLocationRelativeTo(null); //centro
        this.frame.setResizable(true);

        JPanel panel = getjPanel();
        //adicionando no fram o meu painel
        this.frame.add(panel, BorderLayout.WEST); //fica da area do tamanho do botão

        this.frame.setVisible(true);

    }

    private static @NotNull JPanel getjPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        panel.setBackground(new Color(255, 0, 0));
        Button button = new Button("Cadastrar");
        panel.add(button); //por causa que o FlowLaylot ta no CENTRO, o botão tbm ficará no centro
        Button button1 = new Button("Botão 2");
        panel.add(button1);
        Button button2 = new Button("Botão3");
        panel.add(button2);
        panel.setPreferredSize(new Dimension(300,50)); //sem isso irá pririzar os tamanhos dos botões
        return panel;
    }

    public static void main(String [] args){
        JFrame2 janela2 = new JFrame2();

    }
}
