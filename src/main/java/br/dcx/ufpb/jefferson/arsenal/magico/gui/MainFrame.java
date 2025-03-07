package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.ArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.TipoElementar;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame {
    private JFrame frame,actionFrame,messageFrame;
    private JMenuBar menuBar; //A propria barra de menu
    private JMenu systemMenu; //"Sistema"
    private JMenuItem registerMenu,saveMenuItem; //"Sistema-Cadastrar","Sistema-Salvar"
    private JPanel mainPanel,actionPanel;
    private JTextField idField,nomeField, tipoField, danoField, custoManaField; //TODO o field
    private ArsenalMagico system  = new SistemaArsenalMagico();
    private JTextArea userMessage;

    public MainFrame(){
        initialize();
    }

    public void initialize(){
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screanSize.width); //width: 1366
        System.out.println(screanSize.height); //heitgh:768
        frame.setSize((int) (screanSize.width/2), (int) (screanSize.height/2));
        frame.setTitle("Seu Arsenal Magico");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0xFF0000));
        frame.setLayout(new BorderLayout(10,10));
        frame.setIconImage(new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\collectionsMain180.png").getImage());
        //frame.setIconImage = a imagem que fica no título
        //Quero mudar a cor da barra onde fica o titulo

        this.mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0xDADF6D));
        JButton botaoTest = new JButton("Test");
        botaoTest.addActionListener(botaoAl -> System.out.println("Clicado"));
        { //Barra de menu
            Font f =  new Font("sans-serif", Font.BOLD, 15);
            UIManager.put("Menu.font",f);
            UIManager.put("MenuItem.font",f);

            this.menuBar = new JMenuBar();
            menuBar.getComponent().setBackground(new Color(0x47932B));
            menuBar.setToolTipText("Tooltip da barra do menu");

            this.systemMenu = new JMenu("Sistema");
                this.saveMenuItem = new JMenuItem("Salvar");
                saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                saveMenuItem.addActionListener(al -> {
                    System.out.println("Salvou hahaha");
                });
                saveMenuItem.setIconTextGap(10);

                this.registerMenu = new JMenuItem("Cadastrar");
                registerMenu.addActionListener(alRegis -> {
                    /**
                    this.frame = new JFrame("Nome cadastrados");
                    frame.setLocation(0,0);
                    frame.setSize(screanSize);
                    frame.setVisible(true);
                    */
                    this.actionFrame = new JFrame("Cadastrar magia");
                    actionFrame.setLayout(new GridBagLayout());
                    actionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    actionFrame.setSize(screanSize.width/2,screanSize.height/2);
                    actionFrame.setLocationRelativeTo(null);

                    this.actionPanel =  new JPanel(new GridBagLayout());
                    actionPanel.setBackground(new Color(0xE879FA));
                    actionPanel.setSize(screanSize.width/2,screanSize.height/2);
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.insets = new Insets(5,5,5,5);
                    constraints.anchor = GridBagConstraints.WEST;

                    idField = adicionarCampo(actionPanel, constraints, "ID:",0);
                    nomeField = adicionarCampo(actionPanel, constraints,"Nome:", 1);
                    tipoField = adicionarCampo(actionPanel, constraints,"Tipo elementar:",2);
                    danoField = adicionarCampo(actionPanel, constraints,"Dano:",3);
                    custoManaField = adicionarCampo(actionPanel, constraints,"Custo de mana:",4);

                    JButton cadastrarButton = new JButton("Cadastrar");
                    constraints.gridx = 0;
                    constraints.gridy = 5;
                    constraints.gridwidth = 2; //ocupa duas colunas
                    constraints.anchor = GridBagConstraints.CENTER; //centralizar botão
                    actionPanel.add(cadastrarButton, constraints);

                    cadastrarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cadastrar();
                        }
                    });

                    actionFrame.add(actionPanel);
                    actionFrame.setVisible(true);
                    //system.cadastrarMagia(nome,tipo,dano,custoDeMana);
                });
        }
        //Adicionando as camadas
                systemMenu.add(saveMenuItem);
                systemMenu.add(registerMenu);
            menuBar.add(systemMenu);

        frame.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(botaoTest);
        frame.add(mainPanel);
    }
    private static @NotNull JTextField adicionarCampo(@NotNull JPanel panel, @NotNull GridBagConstraints contrains, String labelText, int linha){
        JLabel label = new JLabel(labelText);
        contrains.gridx = 0;
        contrains.gridy = linha;
        panel.add(label, contrains);

        JTextField field = new JTextField(20);
        contrains.gridx = 1;
        contrains.gridy = linha;
        panel.add(field,contrains);

        return field;
    }
    private void cadastrar() throws MagiaJaExisteException {
        try {
            Integer id = Integer.parseInt(idField.getText());
            String nome = nomeField.getText();
            assert !nome.isBlank() || !nome.isEmpty();
            TipoElementar tipo = TipoElementar.FOGO; //TODO mudar para algo que possa ser selecionado
            Double dano = Double.parseDouble(danoField.getText());
            int custoMana = Integer.parseInt(custoManaField.getText());
            system.cadastrarMagia(id,nome, tipo, dano, custoMana);
        } catch (NumberFormatException e){
            userMessageSystem(e.getMessage());
        }
    }
    private void userMessageSystem(String message){
        messageFrame = new JFrame();
        messageFrame.setVisible(true);
        messageFrame.setSize(300,200);
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userMessage = new JTextArea(message);
        userMessage.setFont(new Font("sans-serif", Font.BOLD, 18));
        messageFrame.add(userMessage);
    }
    public void show(){
        this.frame.setVisible(true);
    }
    public void hide(){
        this.frame.setVisible(false);
    }
}
