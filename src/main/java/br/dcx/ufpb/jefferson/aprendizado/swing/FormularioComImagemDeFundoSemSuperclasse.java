package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;


public class FormularioComImagemDeFundoSemSuperclasse {
    public static void main(String[] args) {
        // Cria a janela
        JFrame frame = new JFrame("Formulário com Imagem de Fundo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Cria um painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Carrega a imagem de fundo
        try {
            Image imagemDeFundo = ImageIO.read(new File("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\src\\main\\resources\\icons\\backGroundDefocado.png"));
            ImageIcon icon = new ImageIcon(imagemDeFundo.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
            JLabel fundo = new JLabel(icon);
            fundo.setLayout(new GridBagLayout()); // Usa GridBagLayout para organizar os componentes

            // Adiciona os campos do formulário sobre o fundo
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes

            gbc.gridx = 0;
            gbc.gridy = 0;
            fundo.add(new JLabel("Nome:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            fundo.add(new JTextField(20), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            fundo.add(new JLabel("Idade:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            fundo.add(new JTextField(20), gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            fundo.add(new JLabel("Email:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            fundo.add(new JTextField(20), gbc);

            // Botão no final
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2; // Ocupa duas colunas
            gbc.anchor = GridBagConstraints.CENTER; // Centraliza o botão
            JButton botao = new JButton("Enviar");
            fundo.add(botao, gbc);

            // Adiciona o fundo ao painel principal
            painelPrincipal.add(fundo, BorderLayout.CENTER);
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem de fundo: " + e.getMessage());
        }

        // Adiciona o painel principal à janela
        frame.add(painelPrincipal);

        // Exibe a janela
        frame.setVisible(true);
    }
}
