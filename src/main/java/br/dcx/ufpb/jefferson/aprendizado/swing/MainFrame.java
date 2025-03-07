package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame janela;
    private JPanel painel;
    private JLabel rotulo;
    private JButton botao;

    public MainFrame(){
        initialize();
    }
    public void initialize(){
        janela = new JFrame();
        janela.setTitle("JLabel - rotulo");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setSize(800,500);
        janela.setLocationRelativeTo(null);

        painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        painel.setBackground(new Color(0x3636AC));



        rotulo = new JLabel("Rotulo");
        rotulo.setForeground(Color.PINK);
        rotulo.setFont(new Font("Arial",Font.BOLD, 36));
        rotulo.setIcon(new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\collectionsMain180.png"));
        rotulo.setIconTextGap(30);
        rotulo.setHorizontalTextPosition(SwingConstants.LEFT);
        rotulo.setVerticalTextPosition(SwingConstants.CENTER);

        botao = new JButton("Update label text");
        botao.addActionListener(al -> {
            rotulo.setText("<html>My cool app <br> Buy it now </html>");
        });

        painel.add(rotulo);
        janela.add(botao, BorderLayout.SOUTH);
        janela.add(painel, BorderLayout.NORTH);
    }
    public void show(){
        this.janela.setVisible(true);
    }
}
