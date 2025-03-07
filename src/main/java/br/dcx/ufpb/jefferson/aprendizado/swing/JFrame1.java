package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;

public class JFrame1 extends JFrame {
    public JFrame1(){
        initialize();
    }
    public void initialize(){ //forma menos segura
        setTitle("JFrame1");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //fechar no X da tela
        setSize(800,500);
        setVisible(true);
        //basicos
        setLocationRelativeTo(null); //centro
        setResizable(false);
    }
    public static void main(String [] args){
        JFrame1 jframe1 = new JFrame1();
        //jframe1. usar esse codigo me permite acessar todos os metodos do Jframe,
        //O que é corrigido no JFrame2
        JFrame2 janela2 = new JFrame2();
        //janela2. como visto aqui
    }
}
