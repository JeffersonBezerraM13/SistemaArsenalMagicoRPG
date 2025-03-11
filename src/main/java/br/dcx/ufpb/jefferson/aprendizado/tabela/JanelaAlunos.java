package br.dcx.ufpb.jefferson.aprendizado.tabela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JanelaAlunos extends JFrame {

    private JTable tabelaAlunos;
    private DefaultTableModel tableModel;
    private JButton botaoSelecionar;

    public JanelaAlunos() {
        setTitle("Lista de Alunos");
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cria o modelo da tabela
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Retorna false para todas as células, tornando a tabela não editável
                return false;
            }
        };

        tableModel.addColumn("Nome");
        tableModel.addColumn("Matrícula");
        tableModel.addColumn("Idade");
        tableModel.addColumn("Gênero");

        // Cria a tabela com o modelo
        tabelaAlunos = new JTable(tableModel);

        // Adiciona a tabela a um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabelaAlunos);
        add(scrollPane, BorderLayout.CENTER);

        // Cria o botão para selecionar o aluno
        botaoSelecionar = new JButton("Selecionar Aluno");
        botaoSelecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a linha selecionada na tabela
                int linhaSelecionada = tabelaAlunos.getSelectedRow();

                // Verifica se uma linha foi selecionada
                if (linhaSelecionada >= 0) {
                    // Recupera os dados do aluno selecionado
                    String nome = (String) tableModel.getValueAt(linhaSelecionada, 0);
                    String matricula = (String) tableModel.getValueAt(linhaSelecionada, 1);
                    int idade = (int) tableModel.getValueAt(linhaSelecionada, 2);
                    String genero = (String) tableModel.getValueAt(linhaSelecionada, 3);

                    // Cria um objeto Aluno com os dados selecionados
                    Aluno alunoSelecionado = new Aluno(nome, matricula, idade, genero);

                    // Realiza uma ação com o aluno selecionado (exemplo: exibir os dados)
                    JOptionPane.showMessageDialog(JanelaAlunos.this,
                            "Aluno selecionado:\n" +
                                    "Nome: " + alunoSelecionado.getNome() + "\n" +
                                    "Matrícula: " + alunoSelecionado.getMatricula() + "\n" +
                                    "Idade: " + alunoSelecionado.getIdade() + "\n" +
                                    "Gênero: " + alunoSelecionado.getGenero(),
                            "Aluno Selecionado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Nenhuma linha selecionada
                    JOptionPane.showMessageDialog(JanelaAlunos.this,
                            "Nenhum aluno selecionado!", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Adiciona o botão em um painel na parte inferior da janela
        JPanel painelBotao = new JPanel();
        painelBotao.add(botaoSelecionar);
        add(painelBotao, BorderLayout.SOUTH);

        // Adiciona alguns alunos de exemplo
        adicionarAlunosExemplo();
    }

    private void adicionarAlunosExemplo() {
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("João Silva", "12345", 20, "Masculino"));
        alunos.add(new Aluno("Maria Oliveira", "67890", 22, "Feminino"));
        alunos.add(new Aluno("Carlos Souza", "11223", 19, "Masculino"));
        alunos.add(new Aluno("Ana Costa", "44556", 21, "Feminino"));
        alunos.add(new Aluno("Pedro Rocha", "77889", 23, "Masculino"));
        alunos.add(new Aluno("Luiza Mendes", "99001", 20, "Feminino"));
        alunos.add(new Aluno("Fernando Lima", "22334", 24, "Masculino"));
        alunos.add(new Aluno("Juliana Alves", "55667", 22, "Feminino"));

        for (Aluno aluno : alunos) {
            tableModel.addRow(new Object[]{
                    aluno.getNome(),
                    aluno.getMatricula(),
                    aluno.getIdade(),
                    aluno.getGenero()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaAlunos janela = new JanelaAlunos();
            janela.setVisible(true);
        });
    }
}