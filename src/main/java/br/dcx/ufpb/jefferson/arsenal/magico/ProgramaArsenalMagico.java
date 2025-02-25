package br.dcx.ufpb.jefferson.arsenal.magico;

import br.dcx.ufpb.jefferson.arsenal.magico.gui.ArsenalMagicoGUI;

import javax.swing.*;
import java.io.IOException;

public class ProgramaArsenalMagico {
    public static void main(String [] args) throws IOException {
        SistemaArsenalMagico sistema = new SistemaArsenalMagico();
        sistema.recuperarDados();
        JFrame janelaMain = new ArsenalMagicoGUI();
        ImageIcon iconNull = new ImageIcon((String) null);
        while(true){
            String strDisplayMagia = "";
            for(Magia m: sistema.todasAsMagias()) strDisplayMagia += m.toString()+"\n";
            try {
                String opcao = JOptionPane.showInputDialog(null,
                        """
                                1 - Cadastrar Magia
                                2 - Alterar Magia
                                3 - Remover Magia
                                """, "Sistema Arsenal Mágico", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString();
                switch (opcao) {
                    default:
                        JOptionPane.showMessageDialog(null,"Escolha uma opção válida","Mesange do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        break;
                    case "1":
                        try {
                            Integer idMagiaC1 = Integer.parseInt(JOptionPane.showInputDialog(janelaMain,
                                    "Insira o ID da magia:", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                            String nomeMagiaC1 = JOptionPane.showInputDialog(janelaMain,
                                    "Insira o nome da magia:", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString();
                            if(nomeMagiaC1.isEmpty()){
                                JOptionPane.showMessageDialog(janelaMain,"Insira um nome válido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                                //break;
                            }
                            TipoElementar[] tipoOptions = {TipoElementar.AGUA
                                    , TipoElementar.GELO
                                    , TipoElementar.AR
                                    , TipoElementar.TERRA
                                    , TipoElementar.FOGO};
                            JComboBox<TipoElementar> comboBoxTipoElementar = new JComboBox<>(tipoOptions);
                            TipoElementar tipoElementarC1 = null;
                            if (JOptionPane.showConfirmDialog(janelaMain,comboBoxTipoElementar,"Tipo elementar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,iconNull) == JOptionPane.OK_OPTION) {
                                tipoElementarC1 = (TipoElementar) comboBoxTipoElementar.getSelectedItem();
                            } else {
                                JOptionPane.showMessageDialog(janelaMain, "Cadastro cancelado!","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                                //break;
                            }
                            Double danoC1 = Double.parseDouble(JOptionPane.showInputDialog(janelaMain,
                                    "Insira o dano da magia (um número real):","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
                            int manaC1 = Integer.parseInt(JOptionPane.showInputDialog(janelaMain,
                                    "Insira o custo de mana:","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
                            sistema.cadastrarMagia(idMagiaC1, nomeMagiaC1, tipoElementarC1, danoC1, manaC1);
                            JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso", "Cadastro de magia",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(null, "Insira um valor válido", "Mensagem de erro", JOptionPane.ERROR_MESSAGE, iconNull);
                        } catch (MagiaJaExisteException exception){
                            JOptionPane.showMessageDialog(null,exception.getMessage(),"Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        }
                        break;
                    case "2":
                        if(strDisplayMagia.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Não há magias cadastradas","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                            break;
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

                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Valor inserido inválido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        } catch (MagiaInexistenteException e){
                            JOptionPane.showMessageDialog(null,e.getMessage(),"Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        } catch (NullPointerException e){
                            break;
                        }
                        break;
                    case "3":
                        if(strDisplayMagia.isEmpty()){
                            JOptionPane.showMessageDialog(null,"Não há magias cadastradas","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                            break;
                        }
                        try {
                            Integer idC3 = Integer.parseInt(JOptionPane.showInputDialog(null, strDisplayMagia + "\n Insira o ID da magia que deseja remover:", "Remover magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                            sistema.removerMagia(idC3);
                            JOptionPane.showMessageDialog(null,"Magia removida com sucesso","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null,"Insira um número inteiro referente ao ID da magia","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        } catch (MagiaInexistenteException e){
                            JOptionPane.showMessageDialog(null,e.getMessage(),"Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        }
                        break;
                }
            } catch (NullPointerException e) {

                break;
            }
            sistema.gravarDados();
        }

    }
}
