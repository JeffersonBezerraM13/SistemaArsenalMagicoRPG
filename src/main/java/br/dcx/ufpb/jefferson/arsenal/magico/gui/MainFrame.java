package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.ArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.TipoElementar;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame {
    private JFrame frame,actionFrame,messageFrame;
    private JPanel panel,actionPanel,messagePanel;
    private JLabel label, actionLabel,messageLabel;
    private JMenuBar menuBar;
    private JMenu systemMenu;
    private JMenuItem registerMenu,saveMenuItem, changeMenuItem, removeMenuItem;

    private JTextField idField,nomeField, tipoField, danoField, custoManaField; //TODO o field tipo


    //private JTextPane textPanel;
    private final ImageIcon miniIcon = new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\src\\main\\resources\\icons\\miniatura.png");
    private final ImageIcon backGround = new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\src\\main\\resources\\icons\\backGroundMain.png");
    private final ImageIcon backGroundDesfocado = new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\src\\main\\resources\\icons\\backGroundDefocado.png");
    private GridBagConstraints gbc = new GridBagConstraints();;

    private ArsenalMagico system  = new SistemaArsenalMagico();

    public MainFrame() throws IOException {
        initialize();
    }

    public void initialize() throws IOException {
        messageFrameBasic(); //frame com painel basico padrão para mensagens do sistema
        actionFrameBasic(); //frane com painel basico padrão para outras janelas sem ser a principal


        this.frame = new JFrame("Seu Arsenal Mágico");
        Font fontPadrao = new Font(Font.DIALOG_INPUT, Font.BOLD, 16);
        Color corDaBarra = frame.getForeground();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(backGround.getIconWidth(),backGround.getIconHeight());
        frame.setResizable(false);
        frame.setFont(fontPadrao);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        frame.setIconImage(miniIcon.getImage());

        { //Barra de menu
            UIManager.put("Menu.font",fontPadrao); //font da barra e do menu
            UIManager.put("MenuItem.font",fontPadrao);
            //UIManager.setLookAndFeel(NimbusLookAndFeel);

            this.menuBar = new JMenuBar();
            menuBar.setToolTipText("Menu do sistema");
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
                    reproduzirSom("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\src\\main\\resources\\sounds\\plimv2.wav");
                    System.out.println("Salvou confia");
                    //system.gravarDados();
                });


                this.registerMenu = new JMenuItem("Cadastrar");
                registerMenu.addActionListener(ral -> {
                    actionFrameCadastro();
                    actionFrameBasicShow();
                    this.hideMain();
                });
                this.changeMenuItem = new JMenuItem("Alterar");
                changeMenuItem.addActionListener(cal -> {
                    actionFrameAlterar();
                    actionFrameBasicShow();
                    this.hideMain();
                });
                this.removeMenuItem = new JMenuItem("Remover");
                removeMenuItem.addActionListener(ral -> {
                    actionFrameRemover();
                    actionFrameBasicShow();
                    this.hideMain();
                });
            systemMenu.add(registerMenu);
            systemMenu.add(changeMenuItem);
            systemMenu.add(removeMenuItem);
            systemMenu.add(saveMenuItem);

            menuBar.add(systemMenu);
        }
        this.panel = new JPanel(new GridBagLayout());
        this.label = new JLabel(backGround);
        label.setLayout(new GridBagLayout());

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
        this.label.add(menuBar, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        this.panel.add(this.label);
        gbc.anchor = GridBagConstraints.CENTER;
        this.frame.add(this.panel, gbc);
    }
    private void actionFrameBasic(){
        this.actionFrame = new JFrame();
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
        if(actionFrame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE){ //tentatinha de fazer voltar para o main quando apertar no X
            this.showMain();
        }
        Dimension screanSize = Toolkit.getDefaultToolkit().getScreenSize();
         */
        actionFrame.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        actionFrame.setLocationRelativeTo(null);
        actionFrame.setResizable(false);
        actionFrame.setIconImage(miniIcon.getImage());
        actionFrame.setLayout(new GridBagLayout());

        this.actionPanel = new JPanel(new GridBagLayout());
        this.actionLabel = new JLabel(backGroundDesfocado);
        actionLabel.setLayout(new GridBagLayout());
        //actionLabel.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    private void actionFrameBasicShow(){
        this.actionFrame.setVisible(true);
    }
    private void actionFrameCadastro(){
        this.actionFrame.setTitle("Cadastrar Magia");
        GridBagConstraints gbcCad = new GridBagConstraints();
        gbcCad.insets = new Insets(5,5,5,5);
        gbcCad.anchor = GridBagConstraints.EAST; //para ficar colado na barra de busca
        idField = adicionarCampo(actionLabel, gbcCad, "ID:",0);
        nomeField = adicionarCampo(actionLabel, gbcCad,"Nome:", 1);
        tipoField = adicionarCampo(actionLabel, gbcCad,"Tipo elementar:",2);
        danoField = adicionarCampo(actionLabel, gbcCad,"Dano:",3);
        custoManaField = adicionarCampo(actionLabel, gbcCad,"Custo de mana:",4);

        gbcCad.gridx = 0;
        gbcCad.gridy = 5;
        gbcCad.gridwidth = 0; //ocupa duas colunas
        gbcCad.anchor = GridBagConstraints.CENTER; //centralizar botão
        JButton cadastrarButton = new JButton("Cadastrar"); //TODO: adicionar um jeito de quando apertar enter ele vai apertar o botao
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        actionLabel.add(cadastrarButton, gbcCad);
        gbcCad.gridx = 0;
        gbcCad.gridy = 5;
        gbcCad.gridwidth = 1; //ocupa duas colunas
        gbcCad.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMain();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        gbcCad.gridy = 1;
        gbcCad.gridx = 1;
        actionPanel.add(actionLabel, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        actionFrame.add(actionPanel,gbcCad);
    }
    private static @NotNull JTextField adicionarCampo(@NotNull JLabel labelFundoPadrao, @NotNull GridBagConstraints gbcM, String labelText, int linha){
        JLabel label2 = new JLabel(labelText);
        label2.setFont(new Font("sans-serif", Font.BOLD, 16));
        label2.setForeground(new Color(0xFFFFFF));
        gbcM.gridx = 0;
        gbcM.gridy = linha;
        labelFundoPadrao.add(label2, gbcM);

        JTextField field = new JTextField(20);
        gbcM.gridx = 1;
        gbcM.gridy = linha;
        labelFundoPadrao.add(field, gbcM);

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
            userMessageSystem("Mesangem de erro", "Insira um número válido");
        }
    }
    private void actionFrameAlterar(){
        actionFrame.setTitle("Alterar Magia");

        actionLabel.removeAll();





        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; //ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMain();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbc);

        actionPanel.add(actionLabel, gbc);

        actionFrame.add(actionPanel);
    }
    private void actionFrameRemover(){
        this.actionFrame.setTitle("Remover Magia");


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1; //ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMain();
            this.actionFrame.setVisible(false);
        });
        actionLabel.add(voltarButton, gbc);

        actionLabel.add(voltarButton, gbc);

        JScrollPane painelRemover = new JScrollPane();
        painelRemover.setLayout(new ScrollPaneLayout());
        painelRemover.add(actionLabel, gbc);

        actionFrame.add(painelRemover);
    }
    private void userMessageSystem(String title,String message){
        messageFrame.setTitle(title);
        messageLabel.setText(message);
        messageFrameBasicShow();
    }
    private void messageFrameBasic(){ //Frame padrão para mensagens do sistema
        this.messageFrame = new JFrame();
        messageFrame.setSize(300,200);
        messageFrame.setLocationRelativeTo(null);
        messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messageFrame.setLayout(new GridBagLayout());
        messageFrame.setResizable(false);
        messageFrame.setIconImage(miniIcon.getImage());

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        //Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // Topo, esquerda, baixo, direita
        //Border lineBorder = BorderFactory.createLineBorder(Color.RED, 2); // Cor e espessura
        Border raisedBorder = BorderFactory.createRaisedBevelBorder(); // Relevo LINDO ESSE
        //Border loweredBorder = BorderFactory.createLoweredBevelBorder(); // Rebaixado
        Border titledBorder = BorderFactory.createTitledBorder("Mensagem do Sistema"); //LEGAL
        //Border outerBorder = BorderFactory.createLineBorder(Color.BLUE, 2); //azul
        //Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border compoundBorder = BorderFactory.createCompoundBorder(raisedBorder, titledBorder); //Mesclando varias bordas
        //Border matteBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN); // Topo, esquerda, baixo, direita, cor
        //Border etchedBorder = BorderFactory.createEtchedBorder(); // Efeito de gravura
        painel.setBorder(compoundBorder);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(okal -> {
            messageFrame.setVisible(false);
        });
        gbc.anchor = GridBagConstraints.SOUTH;
        messageFrame.add(okButton, gbc);
        //TODO essa função de quando apertar o enter ele já entender como OK!
        //okButton.setMnemonic(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK).getKeyChar());

        this.messageLabel = new JLabel();
        messageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        messageLabel.setVerticalTextPosition(SwingConstants.CENTER);
        messageLabel.setFont(new Font("sans-serif", Font.BOLD, 18));
        painel.add(messageLabel, BorderLayout.CENTER);
        painel.add(okButton, BorderLayout.LINE_END); //Queria deixar ele mais no centro e embaixo
        messageFrame.add(painel);
    }
    private void messageFrameBasicShow(){
        this.messageFrame.setVisible(true);
    }
    private static void reproduzirSom(String caminhoDoSom) {
        try {
            // Carrega o arquivo de som
            File arquivoDeSom = new File(caminhoDoSom);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoDeSom);

            // Obtém um Clip para reproduzir o som
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproduz o som
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Erro ao reproduzir o som: " + e.getMessage());
        }
    }
    public void showMain(){
        this.frame.setVisible(true);
    }
    private void hideMain(){
        this.frame.setVisible(false);
    }
}
