package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ArsenalMagicoGUI extends JFrame {
    JLabel linha1, linha2; //setando o JLabel
    ImageIcon imageIcon = new ImageIcon("collectionsMain180.png"); //Imagem que eu vou usar
    //São os atributos da classe
    //Pode colocar mais coisas
    JButton botaoAdicionar, botaoPesquisar, botaoRemover;
    SistemaArsenalMagico arsenalMagico = new SistemaArsenalMagico();
    public ArsenalMagicoGUI() throws IOException {
        setTitle("Título da página - Pode ser alterado");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2); //metade da tela do computador do usuario
        setLocation(screenSize.width / 4, screenSize.height / 4); //ocupa 1/4 to tamanho da tela do usuario (centralizado)
        setResizable(false); //janela não redimensionavel
        getContentPane().setBackground(Color.CYAN);
        //Acima é o básico da tela inicial
        //Agora com imagens e JLabel
        linha1 = new JLabel("Arsenal Mágico", JLabel.CENTER);
        linha1.setForeground(Color.RED);
        linha1.setFont(new Font("Serif", Font.BOLD, 24));
        linha2 = new JLabel(imageIcon, JLabel.CENTER);
        getContentPane().setLayout(new GridLayout(3, 2));
        getContentPane().add(linha1);
        getContentPane().add(linha2);
        //Adicionando os botões
        botaoAdicionar = new JButton("Cadastrar magia"); //pode ser acrescentado uma virgular para mudar o contrutor e acrescentar uma imagem ex.: ("Nome do Botão", caminho-da-imagem.png); ou a variavel que aponta para a imagem
        botaoAdicionar.addActionListener(new ArsenalMagicoCadastrarControler(arsenalMagico,this));
        getContentPane().add(botaoAdicionar);
    }
    public static void main(String [] args) throws IOException {
        JFrame janela = new ArsenalMagicoGUI();
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
