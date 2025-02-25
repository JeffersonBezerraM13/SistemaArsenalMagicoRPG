package br.dcx.ufpb.jefferson.arsenal.magico.gui;

import br.dcx.ufpb.jefferson.arsenal.magico.MagiaJaExisteException;
import br.dcx.ufpb.jefferson.arsenal.magico.SistemaArsenalMagico;
import br.dcx.ufpb.jefferson.arsenal.magico.TipoElementar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArsenalMagicoCadastrarControler implements ActionListener {
    private JFrame janela;
    private SistemaArsenalMagico sistema;
    ImageIcon iconNull = new ImageIcon();

    public ArsenalMagicoCadastrarControler(SistemaArsenalMagico arsenalMagico, JFrame jFrame){
        this.janela = jFrame;
        this.sistema = arsenalMagico;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer idMagiaC1 = Integer.parseInt(JOptionPane.showInputDialog(janela,
                    "Insira o ID da magia:", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString());
            String nomeMagiaC1 = JOptionPane.showInputDialog(janela,
                    "Insira o nome da magia:", "Cadastro de magia", JOptionPane.QUESTION_MESSAGE, iconNull, null, null).toString();
            if(nomeMagiaC1.isEmpty()){
                JOptionPane.showMessageDialog(janela,"Insira um nome válido","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                //break;
            }
            TipoElementar[] tipoOptions = {TipoElementar.AGUA
                    , TipoElementar.GELO
                    , TipoElementar.AR
                    , TipoElementar.TERRA
                    , TipoElementar.FOGO};
            JComboBox<TipoElementar> comboBoxTipoElementar = new JComboBox<>(tipoOptions);
            TipoElementar tipoElementarC1 = null;
            if (JOptionPane.showConfirmDialog(janela,comboBoxTipoElementar,"Tipo elementar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,iconNull) == JOptionPane.OK_OPTION) {
                tipoElementarC1 = (TipoElementar) comboBoxTipoElementar.getSelectedItem();
            } else {
                JOptionPane.showMessageDialog(janela, "Cadastro cancelado!","Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
                //break;
            }
            Double danoC1 = Double.parseDouble(JOptionPane.showInputDialog(janela,
                    "Insira o dano da magia (um número real):","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
            int manaC1 = Integer.parseInt(JOptionPane.showInputDialog(janela,
                    "Insira o custo de mana:","Cadastro de magia",JOptionPane.QUESTION_MESSAGE,iconNull,null,null).toString());
            sistema.cadastrarMagia(idMagiaC1, nomeMagiaC1, tipoElementarC1, danoC1, manaC1);
            JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso", "Cadastro de magia",JOptionPane.INFORMATION_MESSAGE,iconNull);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Insira um valor válido", "Mensagem de erro", JOptionPane.ERROR_MESSAGE, iconNull);
        } catch (MagiaJaExisteException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Mensagem do sistema",JOptionPane.INFORMATION_MESSAGE,iconNull);
        }
    }
}
