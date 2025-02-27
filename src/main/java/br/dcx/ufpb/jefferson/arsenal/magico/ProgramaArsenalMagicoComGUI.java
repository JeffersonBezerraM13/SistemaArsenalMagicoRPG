package br.dcx.ufpb.jefferson.arsenal.magico;

import br.dcx.ufpb.jefferson.arsenal.magico.gui.ArsenalMagicoGUI;

import javax.swing.*;
import java.io.IOException;

public class ProgramaArsenalMagicoComGUI {
    public static void main(String [] args) throws IOException {
        JFrame janela = new ArsenalMagicoGUI();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
