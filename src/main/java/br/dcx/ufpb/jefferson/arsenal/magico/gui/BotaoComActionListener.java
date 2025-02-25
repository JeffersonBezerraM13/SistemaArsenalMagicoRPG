package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotaoComActionListener {
    public static void main(String[] args) {
        // Cria uma instância de JFrame
        JFrame frame = new JFrame("Exemplo de Botão com ActionListener");

        // Define o tamanho da JFrame
        frame.setSize(400, 300);

        // Define o comportamento de fechamento da JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria um botão
        JButton botao = new JButton("Clique Aqui");

        // Adiciona um ActionListener ao botão
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica que será executada quando o botão for clicado
                JOptionPane.showMessageDialog(frame, "Botão clicado!");
            }
        });

        // Adiciona o botão ao JFrame
        frame.getContentPane().add(botao);

        // Centraliza a janela na tela
        frame.setLocationRelativeTo(null);

        // Torna a JFrame visível
        frame.setVisible(true);
    }
}