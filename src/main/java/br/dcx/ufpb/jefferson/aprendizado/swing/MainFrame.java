package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    /**
     * Com JTextField
     */
    private JFrame janela;
    private JPanel painel, painelText;
    private JLabel rotulo, cadastro;
    private JButton botao;
    private JTextField textField;
    private JPasswordField passwordField;

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
        // ------------ text field ------------------
        textField = creatJTextField();

        painelText = new JPanel();
        painelText.setBackground(new Color(0x5AA311));
        cadastro = new JLabel("Nome:");
        cadastro.setFont(new Font("Arial", Font.ITALIC, 16));

        //password field
        passwordField = creatJPasswordField();


        painelText.add(cadastro, BorderLayout.WEST);
        painelText.add(textField);
        painelText.add(passwordField, BorderLayout.SOUTH);
        painel.add(rotulo);
        janela.add(botao, BorderLayout.SOUTH);
        janela.add(painel, BorderLayout.NORTH);
        janela.add(painelText, BorderLayout.CENTER);

    }

    //criando o text field
    public JTextField creatJTextField(){
        JTextField textField1 = new JTextField(10); //tamanho (a unidade é o tamanho o caractere) do text field
        textField1.setFont(new Font("Arial", Font.BOLD, 14));
        textField1.setForeground(Color.ORANGE);
        textField1.setBackground(Color.WHITE);
        textField1.setToolTipText("Enter some text");
        textField1.setMargin(new Insets(10,30,10,10));

        textField1.addActionListener(al -> {
            rotulo.setText(textField1.getText());
            textField1.setText("Enter some more text");
        });
        return textField1;
    }
    public JPasswordField creatJPasswordField(){
        JPasswordField passwordField1 = new JPasswordField(10);


        return  passwordField1;
    }
    public void show(){
        this.janela.setVisible(true);
    }
}
