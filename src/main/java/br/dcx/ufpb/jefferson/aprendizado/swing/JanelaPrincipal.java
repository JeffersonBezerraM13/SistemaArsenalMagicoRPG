package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JanelaPrincipal {
    private JFrame janela;
    private JPanel painel;
    private JButton botao1, botao2;
    public JanelaPrincipal(){
        initialize();
    }
    public void initialize(){
        janela = new JFrame();
        janela.setTitle("Janela teste de botao");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(800,500);
        janela.setLocationRelativeTo(null);

        painel = new JPanel();
        painel.setBackground(new Color(218, 223, 109,255));
        janela.add(painel, BorderLayout.CENTER);

        botao1 = crieBotao();
        painel.add(botao1);


        janela.setVisible(true);
    }
    public JButton crieBotao(){
        JButton botao = new JButton("Print");
        botao.setFocusable(false);
        ImageIcon imageBotao = new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\collectionsMain180.png");
        botao.setIcon(imageBotao);
        botao.setIconTextGap(50);
        botao.setMnemonic(KeyEvent.VK_P);
        botao.setToolTipText("Vai pritar um grande hello world");
        botao.setFont(new Font("Arial", Font.PLAIN, 50));
        botao.setMargin(new Insets(10,10,50,10));
        botao.addActionListener(al -> {
            System.out.println("Hello World");
        });
        //botao.setEnabled(false);
        botao.doClick(); //executa a ação do click do botao

        botao.setVerticalTextPosition(SwingConstants.CENTER);
        botao.setHorizontalTextPosition(SwingConstants.LEFT);

        botao.setPreferredSize(new Dimension(400,400));



        return botao;
    }
    public static  void main(String [] args){
        JanelaPrincipal janela = new JanelaPrincipal();
    }
}
