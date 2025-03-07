package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JCheckBoxMainFrame {
    private JFrame frame;
    private JPanel panel;
    private JCheckBox checkBox;

    public JCheckBoxMainFrame(){
        frame = new JFrame();
        frame.setTitle("JCheckox Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout(10,10));

        //Change Font Size for Menu and MenuItem
        Font f =  new Font("sans-serif", Font.PLAIN, 24);
        UIManager.put("CheckBox.font",f);

        panel = new JPanel();

        checkBox = new JCheckBox();
        checkBox.setText("CheckBox");
        System.out.println(checkBox.getText());
        checkBox.setMnemonic(KeyEvent.VK_C);

        panel.add(checkBox);

        frame.add(panel);
    }
    public void show(){
        this.frame.setVisible(true);
    }
}
