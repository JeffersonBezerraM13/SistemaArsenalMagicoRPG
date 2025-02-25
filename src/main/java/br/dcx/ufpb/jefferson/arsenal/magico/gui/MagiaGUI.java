package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MagiaGUI extends JFrame {
    public MagiaGUI(){
        setTitle("Janela de magias teste");
        setSize(400,200);
        setLocation(150,150);
        setResizable(false); //janela não redimensionavel
        getContentPane().setBackground(Color.CYAN);

    }
    public static void main(String [] args){
        JFrame janela = new MagiaGUI();
        janela.setVisible(true); //ativando a janela
        WindowListener fechadorDeJanela = new WindowAdapter() { //WindowAdapter é uma class abstrata
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        janela.addWindowListener(fechadorDeJanela); //parando o pragrama fechando a janela
    }
}
