package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    public static void main(String [] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                ArsenalManager frame = new ArsenalManager();
                frame.showMainScreen();
            }
        });
    }
}
