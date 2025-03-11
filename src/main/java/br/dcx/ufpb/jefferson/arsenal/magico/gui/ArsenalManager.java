package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.ArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.TipoElementar;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class ArsenalManager {
    private JFrame mainFrame,actionFrame,messageFrame;
    private JPanel mainPanel,actionPanel,messagePanel;
    private JLabel backGroundMainLabel, actionLabel,messageLabel;

    private JMenuBar mainMenuBar;
    private JMenu systemMenu;
    private JMenuItem registerMenu,saveMenuItem, changeMenuItem, removeMenuItem;

    private JTextField idField,nomeField, danoField,tipoField, custoManaField;
    private JComboBox<TipoElementar> tipoElementarComboBox;

    private JScrollPane painelScroll;
    private JScrollBar barraScroll;
    private JSpinner spinner;

    private JRadioButton botaoSelec;

    //private JTextPane textPanel;
    private final ImageIcon miniIcon = new ImageIcon("src/main/resources/icons/miniatura.png");
    private final ImageIcon backGround = new ImageIcon("src/main/resources/icons/backGroundMain.png");
    private final ImageIcon backGroundDesfocado = new ImageIcon("src/main/resources/icons/backGroundDefocado.png");
    private final GridBagConstraints gbc = new GridBagConstraints();;

    private ArsenalMagico magicSystem = new SistemaArsenalMagico();

    public ArsenalManager() {
        initializeMainFrame();
    }

    public void initializeMainFrame()  {
        buildMessageFrame(); //mainFrame com painel basico padrão para mensagens do sistema
        buildActionFrame(); //frane com painel basico padrão para outras janelas sem ser a principal


        this.mainFrame = new JFrame("Seu Arsenal Mágico");
        Font fontPadrao = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
        Color corDaBarra = mainFrame.getForeground();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(backGround.getIconWidth(),backGround.getIconHeight());
        mainFrame.setResizable(false);
        mainFrame.setFont(fontPadrao);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setIconImage(miniIcon.getImage());

        { //Barra de menu
            UIManager.put("Menu.font",fontPadrao); //font da barra e do menu
            UIManager.put("MenuItem.font",fontPadrao);
            //UIManager.setLookAndFeel(NimbusLookAndFeel);

            this.mainMenuBar = new JMenuBar();
            mainMenuBar.setToolTipText("Menu do sistema");
            //Talvez mudar a tooltip de alguma forma

            this.systemMenu = new JMenu("Sistema");
                systemMenu.setBorder(BorderFactory.createLineBorder(corDaBarra, 1));
                systemMenu.setIconTextGap(8);
                systemMenu.setBorderPainted(true);
                systemMenu.setForeground(corDaBarra);
                systemMenu.menuSelectionChanged(false);
                systemMenu.addSeparator();

                this.saveMenuItem = new JMenuItem("Salvar");
                saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                saveMenuItem.addActionListener(al -> {
                    reproduzirSom();
                    System.out.println("Salvou confia");
                    //magicSystem.gravarDados();
                });


                this.registerMenu = new JMenuItem("Cadastrar");
                registerMenu.addActionListener(ral -> {
                    rebuildActionFrameForCadastro();
                    this.actionFrame.setVisible(true);
                    this.hideMain();
                });
                this.changeMenuItem = new JMenuItem("Alterar");
                changeMenuItem.addActionListener(cal -> {
                    rebuildActionFrameForAlteracao();
                    this.actionFrame.setVisible(true);
                    this.hideMain();
                });
                this.removeMenuItem = new JMenuItem("Remover");
                removeMenuItem.addActionListener(ral -> {
                    rebuildActionFrameForRemocao();
                    this.actionFrame.setVisible(true);
                    this.hideMain();
                });
            systemMenu.add(registerMenu);
            systemMenu.add(changeMenuItem);
            systemMenu.add(removeMenuItem);
            systemMenu.add(saveMenuItem);

            mainMenuBar.add(systemMenu);
        }
        this.mainPanel = new JPanel(new GridBagLayout());
        this.backGroundMainLabel = new JLabel(backGround);
        backGroundMainLabel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(0,0,0,0); //top,left,botton,right - isso é a distancia de cima,esquerda,baixo e direita do componente com o grid do container
        //gbc.ipady = 0; //esses 3 juntos
        //gbc.ipadx = 0; //esse
        gbc.anchor = GridBagConstraints.NORTHWEST; //e esse é a distancia x y no sentino northwest que o componente ta se distanciando no outro
        //gbc.fill = 0; //usa contatantes para determinar onde o componente deverá ficar no container E.: GridBagConstraints.BOTH
        //gbc.gridy = 1; // nao entendi muito bem oq ele faz
        //gbc.gridx = 1; // não entendi muito bem oq ele faz
        gbc.weighty = 1.0; // se for 1 gruda na direcao da ancora
        gbc.weightx = 1.0; // se for 1 gruda na direcao da ancora
        //gbc.gridheight = 0;
        //gbc.gridwidth = 0;
        this.backGroundMainLabel.add(mainMenuBar, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        this.mainPanel.add(this.backGroundMainLabel);
        gbc.anchor = GridBagConstraints.CENTER;
        this.mainFrame.add(this.mainPanel, gbc);
    }
    private void buildActionFrame(){
        this.actionFrame = new JFrame();
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
        if(actionFrame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE){ //tentatinha de fazer voltar para o main quando apertar no X
            this.showMainScreen();
        }
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
         */
        actionFrame.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        actionFrame.setLocationRelativeTo(null);
        actionFrame.setResizable(true);
        actionFrame.setIconImage(miniIcon.getImage());
        actionFrame.setLayout(new GridBagLayout());

        this.actionLabel = new JLabel(backGroundDesfocado);
        actionLabel.setLayout(new GridBagLayout());
        //actionLabel.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private void rebuildActionFrameForCadastro(){
        this.actionFrame.setTitle("Cadastrar Magia");
        GridBagConstraints gbcCad = new GridBagConstraints();
        gbcCad.insets = new Insets(5,5,5,5);
        gbcCad.anchor = GridBagConstraints.EAST; //para ficar colado na barra de busca
        idField = adicionarCampoAoActionFrame(gbcCad, "ID:",0);
        nomeField = adicionarCampoAoActionFrame(gbcCad,"Nome:", 1);
        JLabel label2 = new JLabel("Tipo elementar:");
        label2.setFont(new Font("sans-serif", Font.BOLD, 16));
        label2.setForeground(new Color(0xFFFFFF));
        gbcCad.gridx = 0;
        gbcCad.gridy = 2;
        actionLabel.add(label2, gbcCad);
        tipoElementarComboBox = new JComboBox<>(TipoElementar.values());
        tipoElementarComboBox.setSize(50,20);
        gbcCad.gridx = 1;
        gbcCad.gridy = 2;
        gbcCad.anchor = GridBagConstraints.WEST;
        actionLabel.add(tipoElementarComboBox,gbcCad);
        gbcCad.anchor = GridBagConstraints.EAST;
        danoField = adicionarCampoAoActionFrame(gbcCad,"Dano:",3);
        custoManaField = adicionarCampoAoActionFrame(gbcCad,"Custo de mana:",4);

        gbcCad.gridx = 0;
        gbcCad.gridy = 5;
        gbcCad.gridwidth = 0; //ocupa coluna da direita
        gbcCad.anchor = GridBagConstraints.CENTER; //centralizar botão
        JButton cadastrarButton = new JButton("Cadastrar"); //TODO: adicionar um jeito de quando apertar enter ele vai apertar o botao
        cadastrarButton.addActionListener(cal -> {
            cadastrarMagia();
        });
        actionLabel.add(cadastrarButton, gbcCad);

        gbcCad.gridx = 0;
        gbcCad.gridy = 5;
        gbcCad.gridwidth = 1; //ocupa coluna da esquerda
        gbcCad.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        gbcCad.gridy = 1;
        gbcCad.gridx = 1;
        //actionPanel.add(actionLabel, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        actionFrame.add(actionLabel,gbcCad);
    }
    private JTextField adicionarCampoAoActionFrame(GridBagConstraints gbcM, String labelText, int linha){
        JLabel label2 = new JLabel(labelText);
        label2.setFont(new Font("sans-serif", Font.BOLD, 16));
        label2.setForeground(new Color(0xFFFFFF));
        gbcM.gridx = 0;
        gbcM.gridy = linha;
        actionLabel.add(label2, gbcM);

        JTextField field = new JTextField(20);
        gbcM.gridx = 1;
        gbcM.gridy = linha;
        actionLabel.add(field, gbcM);

        return field;
    }
    private void cadastrarMagia() throws MagiaJaExisteException {
        try {
            Integer id = Integer.parseInt(idField.getText());
            String nome = nomeField.getText();
            assert !nome.isBlank() || !nome.isEmpty();
            TipoElementar tipo = (TipoElementar) tipoElementarComboBox.getSelectedItem(); //TODO mudar para algo que possa ser selecionado
            Double dano = Double.parseDouble(danoField.getText());
            int custoMana = Integer.parseInt(custoManaField.getText());
            magicSystem.cadastrarMagia(id,nome, tipo, dano, custoMana);
            showMessage("Mensagem do sistema", "Magia cadastrada com sucesso!");
            idField.setText("");
            nomeField.setText("");
            //tipo
            danoField.setText("");
            custoManaField.setText("");
        } catch (NumberFormatException e){
            showMessage("Mesangem de erro", "Insira um número válido");
        }
    }
    private void rebuildActionFrameForAlteracao(){
        actionFrame.setTitle("Alterar Magia");

        actionLabel.removeAll();





        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; //ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbc);

        actionPanel.add(actionLabel, gbc);

        actionFrame.add(actionPanel);
    }
    private void rebuildActionFrameForRemocao(){
        this.actionFrame.setTitle("Remover Magia");
        this.painelScroll = new JScrollPane();
        painelScroll.setLayout(new ScrollPaneLayout());
        this.barraScroll = new JScrollBar();
        painelScroll.add(barraScroll);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; //ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbc);

        actionLabel.add(voltarButton, gbc);

        painelScroll.add(actionLabel);

        actionFrame.add(painelScroll, gbc);
    }
    private void showMessage(String title, String message){
        messageFrame.setTitle(title);
        messageLabel.setText(message);
        this.messageFrame.setVisible(true);
    }
    private void buildMessageFrame(){ //Frame padrão para mensagens do sistema
        this.messageFrame = new JFrame();
        messageFrame.setSize(300,200);
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageFrame.setLayout(new GridBagLayout());
        messageFrame.setResizable(false);
        messageFrame.setIconImage(miniIcon.getImage());
        //talvez fazer um backGroundMainLabel para colocar uma imagem de fundo

        messagePanel = new JPanel();
        messagePanel.setLayout(new GridBagLayout());
        /**
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Topo, esquerda, baixo, direita
        Border lineBorder = BorderFactory.createLineBorder(Color.RED, 2); // Cor e espessura
        Border raisedBorder = BorderFactory.createRaisedBevelBorder(); // Relevo LINDO ESSE
        Border loweredBorder = BorderFactory.createLoweredBevelBorder(); // Rebaixado
        Border titledBorder = BorderFactory.createTitledBorder("Mensagem do Sistema"); //LEGAL
        Border outerBorder = BorderFactory.createLineBorder(Color.BLUE, 2); //azul
        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compoundBorder = BorderFactory.createCompoundBorder(raisedBorder, titledBorder); //Mesclando varias bordas
        Border matteBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN); // Topo, esquerda, baixo, direita, cor
        Border etchedBorder = BorderFactory.createEtchedBorder(); // Efeito de gravura
        messagePainel.setBorder(compoundBorder);
        */
        messageLabel = new JLabel(); //botar um background depois
        messageLabel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(20,5,20,5);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(okal -> {
            messageFrame.setVisible(false);
        });
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        messageFrame.add(okButton, gbc);
        //TODO essa função de quando apertar o enter ele já entender como OK!
        //okButton.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK).getKeyChar());

        //messageLabel.setHorizontalTextPosition(GridBagConstraints.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        messageLabel.setFont(new Font("sans-serif", Font.BOLD, 18));
        messagePanel.add(messageLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        messageFrame.add(messagePanel, gbc);
    }

    private static void reproduzirSom() {
        try {
            File arquivoDeSom = new File("src/main/resources/sounds/plimv2.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoDeSom);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir o som: " + e.getMessage());
        }
    }
    public void showMainScreen(){
        this.mainFrame.setVisible(true);
    }
    private void hideMain(){
        this.mainFrame.setVisible(false);
    }
}
