package br.dcx.ufpb.jefferson.aprendizado.swing;

import br.dcx.ufpb.jefferson.arsenal.magico.ArsenalMagico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioGUI {
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextField emailField;
    private JTextField telefoneField;
    private ArsenalMagico cadastroService; // Referência para a classe de backend

    public FormularioGUI() {
        // Inicializa o serviço de cadastro
        //cadastroService = new CadastroService();

        // Cria a janela
        JFrame frame = new JFrame("Formulário de Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Cria o painel com GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Espaçamento
        constraints.anchor = GridBagConstraints.WEST; // Alinha à esquerda

        // Adiciona os campos
        nomeField = adicionarCampo(panel, constraints, "Nome:", 0);
        idadeField = adicionarCampo(panel, constraints, "Idade:", 1);
        emailField = adicionarCampo(panel, constraints, "Email:", 2);
        telefoneField = adicionarCampo(panel, constraints, "Telefone:", 3);

        // Botão de cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2; // Ocupa duas colunas
        constraints.anchor = GridBagConstraints.CENTER; // Centraliza o botão
        panel.add(cadastrarButton, constraints);

        // Adiciona ação ao botão
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        // Adiciona o painel à janela
        frame.add(panel);
        frame.setVisible(true);
    }

    private JTextField adicionarCampo(JPanel panel, GridBagConstraints constraints, String labelText, int linha) {
        // Adiciona o label
        JLabel label = new JLabel(labelText);
        constraints.gridx = 0;
        constraints.gridy = linha;
        panel.add(label, constraints);

        // Adiciona o field
        JTextField field = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = linha;
        panel.add(field, constraints);

        return field; // Retorna o field para poder acessá-lo depois
    }

    private void cadastrar() {
        // Coleta os valores dos campos
        String nome = nomeField.getText();
        int idade = Integer.parseInt(idadeField.getText()); // Converte para int
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        // Chama o método de cadastro do backend
        //cadastroService.cadastrar(nome, idade, email, telefone);

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }

    public static void main(String[] args) {
        new FormularioGUI(); // Inicia a interface gráfica
    }
}
