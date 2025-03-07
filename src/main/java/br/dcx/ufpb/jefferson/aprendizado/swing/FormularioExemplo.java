package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class FormularioExemplo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        constraints.anchor = GridBagConstraints.WEST; // Alinha os componentes à esquerda

        // Adiciona os labels e fields
        adicionarCampo(panel, constraints, "Nome:", 0);
        adicionarCampo(panel, constraints, "Idade:", 1);
        adicionarCampo(panel, constraints, "Email:", 2);
        adicionarCampo(panel, constraints, "Telefone:", 3);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void adicionarCampo(JPanel panel, GridBagConstraints constraints, String labelText, int linha) {
        // Configura o label
        JLabel label = new JLabel(labelText);
        constraints.gridx = 0; // Coluna 0 (labels)
        constraints.gridy = linha; // Linha atual
        panel.add(label, constraints);

        // Configura o field
        JTextField field = new JTextField(20);
        constraints.gridx = 1; // Coluna 1 (fields)
        constraints.gridy = linha; // Linha atual
        panel.add(field, constraints);
    }
}
