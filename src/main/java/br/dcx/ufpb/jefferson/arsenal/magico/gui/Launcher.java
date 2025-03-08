package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import java.awt.*;
import java.io.IOException;

public class Launcher {
    public static void main(String [] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /**
                for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                    if("Nimbus".equals(info.getName())){
                        try {
                            UIManager.setLookAndFeel(info.getClassName());
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (UnsupportedLookAndFeelException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
                 */
                MainFrame frame = null;
                try {
                    frame = new MainFrame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                frame.showMain();
            }
        });
    }
}
