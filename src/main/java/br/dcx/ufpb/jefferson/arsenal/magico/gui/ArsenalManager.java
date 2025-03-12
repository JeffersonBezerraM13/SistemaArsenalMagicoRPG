package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.entities.Magia;
import br.dcx.ufpb.jefferson.arsenal.magico.entities.TipoElementar;
import br.dcx.ufpb.jefferson.arsenal.magico.exception.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.system.ArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.system.SistemaArsenalMagico;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class ArsenalManager {
    private JFrame mainFrame,actionFrame,messageFrame;
    private JPanel mainPanel,messagePanel;
    private JLabel backGroundMainLabel, backGroundActionLabel,messageLabel;
    private JMenuBar mainMenuBar;
    private JMenu systemMenu;
    private JMenuItem registerMenu,saveMenuItem, changeMenuItem, removeMenuItem;

    private JTextField idField,nomeField, danoField, custoManaField;
    private JComboBox<TipoElementar> tipoElementarComboBox;

    private JTable magicTable;
    private DefaultTableModel tableModel;

    private final ImageIcon miniIcon = new ImageIcon("src/main/resources/icons/miniatura.png");
    private final ImageIcon backGround = new ImageIcon("src/main/resources/icons/backGroundMain.png");
    private final ImageIcon backGroundDesfocado = new ImageIcon("src/main/resources/icons/backGroundDefocado.png");
    private final GridBagConstraints gbc = new GridBagConstraints();;

    private ArsenalMagico magicSystem = new SistemaArsenalMagico();

    public ArsenalManager() {
        initializeMainFrame();
    }

    public void initializeMainFrame()  {
        try {
            magicSystem.recuperarDados();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buildMessageFrame(); //mainFrame com painel basico padrão para mensagens do sistema
        // frane com painel basico padrão para outras janelas sem ser a principal


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
                    try {
                        magicSystem.gravarDados();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                this.registerMenu = new JMenuItem("Cadastrar");
                registerMenu.addActionListener(ral -> {
                    buildActionFrameForCadastro();
                    this.actionFrame.setVisible(true);
                    this.hideMain();
                });
                this.changeMenuItem = new JMenuItem("Alterar");
                changeMenuItem.addActionListener(cal -> {
                    buildActionFrameForAlteracao();
                    this.actionFrame.setVisible(true);
                    this.hideMain();
                });
                this.removeMenuItem = new JMenuItem("Remover");
                removeMenuItem.addActionListener(ral -> {
                    buildActionFrameForRemocao();
                    actionFrame.setVisible(true);
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

    private void buildActionFrameForCadastro(){
        this.actionFrame =  new JFrame("Cadastrar Magia");
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionFrame.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        actionFrame.setLocationRelativeTo(null);
        actionFrame.setResizable(true);
        actionFrame.setIconImage(miniIcon.getImage());
        actionFrame.setLayout(new GridBagLayout());

        this.backGroundActionLabel = new JLabel(backGroundDesfocado);
        backGroundActionLabel.setLayout(new GridBagLayout());




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
        backGroundActionLabel.add(label2, gbcCad);
        tipoElementarComboBox = new JComboBox<>(TipoElementar.values());
        tipoElementarComboBox.setSize(50,20);
        gbcCad.gridx = 1;
        gbcCad.gridy = 2;
        gbcCad.anchor = GridBagConstraints.WEST;
        backGroundActionLabel.add(tipoElementarComboBox,gbcCad);
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
        backGroundActionLabel.add(cadastrarButton, gbcCad);

        gbcCad.gridx = 0;
        gbcCad.gridy = 5;
        gbcCad.gridwidth = 1; //ocupa coluna da esquerda
        gbcCad.anchor = GridBagConstraints.CENTER;
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        backGroundActionLabel.add(voltarButton, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        gbcCad.gridy = 1;
        gbcCad.gridx = 1;
        //actionPanel.add(backGroundActionLabel, gbcCad);
        gbcCad.anchor = GridBagConstraints.CENTER;
        actionFrame.add(backGroundActionLabel,gbcCad);
    }
    private JTextField adicionarCampoAoActionFrame(GridBagConstraints gbcM, String labelText, int linha){
        JLabel label2 = new JLabel(labelText);
        label2.setFont(new Font("sans-serif", Font.BOLD, 16));
        label2.setForeground(new Color(0xFFFFFF));
        gbcM.gridx = 0;
        gbcM.gridy = linha;
        backGroundActionLabel.add(label2, gbcM);

        JTextField field = new JTextField(20);
        gbcM.gridx = 1;
        gbcM.gridy = linha;
        backGroundActionLabel.add(field, gbcM);

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
    private void buildActionFrameForAlteracao(){
        this.actionFrame = new JFrame("Alterar Magia");
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionFrame.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        actionFrame.setLocationRelativeTo(null);
        actionFrame.setResizable(true);
        actionFrame.setIconImage(miniIcon.getImage());

        this.tableModel = new DefaultTableModel() { //cria o modelo da tabela
            @Override
            public boolean isCellEditable(int row, int column){
                return false; //nenhum é editavel
            }

        };
        //adiciona as colunas ao modelo
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Dano");
        tableModel.addColumn("Custo");
        for(Magia m: magicSystem.todasAsMagias()){ //adicionando todas as magias na tabela

            tableModel.addRow(new Object[]{
                    m.getId(),
                    m.getNome(),
                    m.getTipo().getValor(),
                    m.getDano(),
                    m.getCustoDeMana()
            });
        }
        //config do modelo
        magicTable = new JTable(tableModel);
        magicTable.getColumnModel().getColumn(0).setPreferredWidth(15); //largura da coluna do check box
        magicTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        magicTable.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        magicTable.setPreferredScrollableViewportSize(new Dimension(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight()));

        //adiciona a tebala a um jscrollpane que para permitir a rolagem
        JScrollPane scrollPane = new JScrollPane(magicTable);
        actionFrame.add(scrollPane,BorderLayout.CENTER);

        //botao para remover todas as magias
        JButton botaoSelecionar = new JButton("Selecionar Magia");
        botaoSelecionar.addActionListener(alal -> {
            ImageIcon iconNull = new ImageIcon((String)null);
            int linhaSelecionada = magicTable.getSelectedRow();

            Magia m = magicSystem.getMagia((Integer)tableModel.getValueAt(linhaSelecionada,0));
            String alterarOptions = JOptionPane.showInputDialog(null, """
                                    1 - ID
                                    2 - Nome
                                    3 - Tipo elementar
                                    4 - Dano
                                    5 - Custo de mana
                                    ""","Escolhe o dado que deseja alterar",JOptionPane.QUESTION_MESSAGE ,iconNull,null,null).toString();
            switch (alterarOptions){
                case "1":
                    try {
                        Integer idAlterar = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o ID novo para a magia " + m.toString(), "Alterar magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                        m.setId(idAlterar);
                        JOptionPane.showMessageDialog(null, "ID alterado\n" + m,"Alterar ID",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"Insira um valor inteiro válido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    }
                    break;
                case "2":
                    String nomeAlterar = JOptionPane.showInputDialog(null,"Insira um nome novo para a magia "+m.toString(),"Alterar magia", JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString();
                    if(nomeAlterar.isEmpty() || nomeAlterar.isBlank()){
                        JOptionPane.showMessageDialog(null,"Insira um novo nome válido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        break;
                    }
                    m.setNome(nomeAlterar);
                    JOptionPane.showMessageDialog(null,"Nome alterado\n"+m,"Alterar nome",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    break;
                case "3":
                    TipoElementar [] tipoOptions = {TipoElementar.AGUA
                            , TipoElementar.GELO
                            , TipoElementar.AR
                            , TipoElementar.TERRA
                            , TipoElementar.FOGO};
                    JComboBox<TipoElementar> comboBoxTipoElementar = new JComboBox<>(tipoOptions);
                    TipoElementar tipoElementarAlterar;
                    if (JOptionPane.showConfirmDialog(null,comboBoxTipoElementar,"Tipo elementar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,iconNull) == JOptionPane.OK_OPTION) {
                        tipoElementarAlterar = (TipoElementar) comboBoxTipoElementar.getSelectedItem();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado!","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        break;
                    }
                    m.setTipo(tipoElementarAlterar);
                    JOptionPane.showMessageDialog(null,"Tipo elementar alterado "+m,"Alterar tipo elementar",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    break;
                case "4":
                    try {
                        Double danoAlterar = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira um dano novo para a magia " + m.toString(), "Alterar dano", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                        m.setDano(danoAlterar);
                        JOptionPane.showMessageDialog(null, "Dano alterado " + m, "Alterar dano", JOptionPane.INFORMATION_MESSAGE, iconNull);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"Insira um valor real para representar o dano da magia","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    }
                    break;
                case "5":
                    try {
                        int manaAletar = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira um novo valor para a mana da magia " + m.toString(), "Alterar mana", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                        m.setCustoDeMana(manaAletar);
                        JOptionPane.showMessageDialog(null, "Mana alterada " + m, "Mensagem do sistema", JOptionPane.INFORMATION_MESSAGE, iconNull);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"Insira um número inteiro válido referenta ao novo custo de mana","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                    }
                    break;
                default:
                    break;
            }
            this.actionFrame.setVisible(false);
            this.mainFrame.setVisible(true);
        });
        //botoa de voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        //painel para botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER)); //no centro do painel
        painelBotoes.add(voltarButton);
        painelBotoes.add(botaoSelecionar);
        actionFrame.add(painelBotoes, BorderLayout.SOUTH); //e o painel está em baixo




    }
    private void buildActionFrameForRemocao(){
        this.actionFrame = new JFrame("Remover Magia");
        actionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionFrame.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        actionFrame.setLocationRelativeTo(null);
        actionFrame.setResizable(true);
        actionFrame.setIconImage(miniIcon.getImage());

        this.tableModel = new DefaultTableModel() { //cria o modelo da tabela
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 0; //apenas a coluna do checkBox é editavel
            }
            @Override
            public Class<?> getColumnClass(int columnIndex){
                //define o tipo de dados da coluna do checkBox
                if(columnIndex == 0){
                    return Boolean.class;
                }
                return String.class;
            }

        };
        //adiciona as colunas ao modelo
        tableModel.addColumn(""); //coluna do check box
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Dano");
        tableModel.addColumn("Custo");
        for(Magia m: magicSystem.todasAsMagias()){ //adicionando todas as magias na tabela

            tableModel.addRow(new Object[]{
                    false, //do checkbox
                    m.getId(),
                    m.getNome(),
                    m.getTipo().getValor(),
                    m.getDano(),
                    m.getCustoDeMana()
            });
        }
        //config do modelo
        magicTable = new JTable(tableModel);
        magicTable.getColumnModel().getColumn(0).setPreferredWidth(15); //largura da coluna do check box
        magicTable.getColumnModel().getColumn(1).setPreferredWidth(10);
        magicTable.setSize(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight());
        magicTable.setPreferredScrollableViewportSize(new Dimension(backGroundDesfocado.getIconWidth(),backGroundDesfocado.getIconHeight()));

        //adiciona a tebala a um jscrollpane que para permitir a rolagem
        JScrollPane scrollPane = new JScrollPane(magicTable);
        actionFrame.add(scrollPane,BorderLayout.CENTER);

        //botao para remover todas as magias
        JButton botaoSelecionar = new JButton("Remover mágias");
        botaoSelecionar.addActionListener(remal -> {
            boolean magiasNaoForamSelecionas = false;
            for(int linha = 0; linha < tableModel.getRowCount(); linha++){
                if (tableModel.getValueAt(linha, 0).equals(true)) {
                    magiasNaoForamSelecionas = false;
                    magicSystem.removerMagia((Integer)tableModel.getValueAt(linha,1));
                } else {
                    magiasNaoForamSelecionas = true;
                }
            }
            if(magiasNaoForamSelecionas){
                showMessage("Mensagem do sistema","Não há mágias selecionadas");
            } else {
                this.showMainScreen();
                this.actionFrame.setVisible(false);
                showMessage("Mensagem do sistema", "Mágias removidas com sucesso!");
            }
        });
        //botoa de voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(val -> {
            this.showMainScreen();
            this.actionFrame.setVisible(false);
        });
        //painel para botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER)); //no centro do painel
        painelBotoes.add(voltarButton);
        painelBotoes.add(botaoSelecionar);
        actionFrame.add(painelBotoes, BorderLayout.SOUTH); //e o painel está em baixo

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
