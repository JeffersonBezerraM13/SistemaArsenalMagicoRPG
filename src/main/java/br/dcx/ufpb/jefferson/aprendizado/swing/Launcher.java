package br.dcx.ufpb.jefferson.aprendizado.swing;

import java.awt.*;

public class Launcher {
    public static void main(String [] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.show();
            }
        });
    }
}
