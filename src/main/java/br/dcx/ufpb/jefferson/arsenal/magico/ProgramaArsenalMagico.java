package br.dcx.ufpb.jefferson.arsenal.magico;


import br.dcx.ufpb.jefferson.arsenal.magico.futuro.EfeitoElementar;
import br.dcx.ufpb.jefferson.arsenal.magico.futuro.FormaElementar;

import javax.swing.*;

public class ProgramaArsenalMagico {
    public static void main(String [] args){
        SistemaArsenalMagico sistema = new SistemaArsenalMagico();
        ImageIcon iconNull = new ImageIcon((String) null);
        while(true){
            try {
                String opcao = JOptionPane.showInputDialog(null,
                        """
                                1 - Cadastrar Magia
                                2 - Alterar Magia
                                3- Remover Magia
                                """, "Sistema Arsenal Mágico", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString();
                switch (opcao) {
                    default:
                        JOptionPane.showMessageDialog(null,"Escolha uma opção válida","Mesange do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                        break;
                    case "1":
                        try {
                            Integer idMagiaC1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "Insira o ID da magia", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
                            String nomeMagiaC1 = JOptionPane.showInputDialog(null,
                                    "Insira o nome da magia", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString();
                            String[] tipoOptions = {TipoElementar.AGUA.getValor()
                                    , TipoElementar.GELO.getValor()
                                    , TipoElementar.AR.getValor()
                                    , TipoElementar.TERRA.getValor()
                                    , TipoElementar.FOGO.getValor()};
                            JComboBox<String> comboBoxTipoElementar = new JComboBox<>(tipoOptions);
                            int resultadoTipo = JOptionPane.showConfirmDialog(null, comboBoxTipoElementar, "Tipo elementar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,iconNull);
                            TipoElementar tipoElementarC1;
                            if (resultadoTipo == JOptionPane.OK_CANCEL_OPTION) {
                                tipoElementarC1 = (TipoElementar) comboBoxTipoElementar.getSelectedItem();
                            } else {
                                JOptionPane.showMessageDialog(null, "Cadastro cancelado","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                                break;
                            }
                            Double danoC1 = Double.parseDouble(JOptionPane.showInputDialog(null,
                                    "Insira o dano da magia:","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
                            int manaC1 = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "Insira o custo de mana:","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
                            sistema.cadastrarMagia(idMagiaC1, nomeMagiaC1, tipoElementarC1, danoC1, manaC1);
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem de erro", JOptionPane.ERROR_MESSAGE, iconNull);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Insira um valor válido", "Mensagem de erro", JOptionPane.ERROR_MESSAGE, iconNull);
                        }
                }
            } catch (NullPointerException e) {
                break;
            }
        }

    }
}
