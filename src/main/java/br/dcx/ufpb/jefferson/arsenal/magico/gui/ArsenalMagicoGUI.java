package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.Magia;
import br.dcx.ufpb.jefferson.arsenal.magico.MagiaInexistenteException;
import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.TipoElementar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ArsenalMagicoGUI extends JFrame {
    JLabel linha1, linha2; //setando o JLabel
    ImageIcon imageIcon = new ImageIcon("collectionsMain180.png"); //Imagem que eu vou usar
    ImageIcon iconNull = new ImageIcon((String) null);
    SistemaArsenalMagico sistema = new SistemaArsenalMagico();
    //São os atributos da classe
    //Pode colocar mais coisas
    JButton botaoCadastrar, botaoPesquisar,botaoAlterar, botaoRemover;

    public ArsenalMagicoGUI() throws IOException {
        setTitle("Título da página - Pode ser alterado");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2); //metade da tela do computador do usuario
        setLocation(screenSize.width / 4, screenSize.height / 4); //ocupa 1/4 to tamanho da tela do usuario (centralizado)
        setResizable(false); //janela não redimensionavel
        getContentPane().setBackground(new Color(138, 76, 228, 255));
        //Acima é o básico da tela inicial
        //Agora com imagens e JLabel
        getContentPane().setLayout(new GridLayout(3, 2));
        linha1 = new JLabel("Arsenal Mágico", JLabel.CENTER);
        linha1.setForeground(new Color(248, 220, 84));
        linha1.setFont(new Font("Serif", Font.BOLD, 50));
        linha2 = new JLabel(imageIcon, JLabel.CENTER);
        botaoCadastrar = new JButton("Cadastrar magia"); //pode ser acrescentado uma virgular para mudar o contrutor e acrescentar uma imagem ex.: ("Nome do Botão", caminho-da-imagem.png); ou a variavel que aponta para a imagem
        botaoCadastrar.addActionListener(new ArsenalMagicoCadastrarControler(sistema,this));
        botaoCadastrar.setSize(new Dimension(30,30));
        botaoPesquisar = new JButton("Botão pesquisar");
        botaoPesquisar.addActionListener((ae) -> {
            String strDisplayMagia = "";
            for(Magia m: sistema.todasAsMagias()) strDisplayMagia += m.toString()+"\n";
            if(strDisplayMagia.isEmpty()){
                JOptionPane.showMessageDialog(null,"Não há magias cadastradas","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
            }
            try {
                Integer idC2 = Integer.parseInt(JOptionPane.showInputDialog(null, strDisplayMagia + "\nInsira o ID da magia que deseja alterar:", "Alterar magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                Magia m = sistema.getMagia(idC2);
                String alterarOptions = JOptionPane.showInputDialog(null, """
                                    1 - ID
                                    2 - Nome
                                    3 - Tipo elementar
                                    4 - Dano
                                    5 - Custo de mana
                                    """);
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
                        TipoElementar[] tipoOptions = {TipoElementar.AGUA
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

            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Valor inserido inválido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
            } catch (MagiaInexistenteException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
            }
        });
        getContentPane().add(linha1);
        getContentPane().add(botaoCadastrar);
        getContentPane().add(linha2);
        getContentPane().add(botaoPesquisar);
        getContentPane().add(new JLabel());
        getContentPane().add(new JLabel());

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
