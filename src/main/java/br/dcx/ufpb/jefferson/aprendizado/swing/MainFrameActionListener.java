package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrameActionListener implements ActionListener {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newMenuItem, saveMenuItem, exitMenuItem;

    public MainFrameActionListener(){
        initialize();
    }
    public void initialize(){
        frame = new JFrame();
        frame.setTitle("JMenu Demo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout(10,10));
        frame.setLocationRelativeTo(null);

        //Change Font Size for menu and menuitem
        Font f = new Font("sans-serif", Font.PLAIN, 18);
        UIManager.put("Menu.font",f);
        UIManager.put("MenuItem.font",f);
        UIManager.put("CheckBoxMenuItem.font",f);
        UIManager.put("RadioButtonMenuItem",f);

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newMenuItem = new JMenuItem("New...");
        newMenuItem.setIcon(new ImageIcon("C:\\Users\\bezer\\IdeaProjects\\SistemaArsenalMagicoRPG\\collectionsMain180.png"));
        newMenuItem.setIconTextGap(10);
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newMenuItem.addActionListener(this);

        saveMenuItem = new JMenuItem("Save...");

        exitMenuItem = new JMenuItem("Exit...");

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);


        fileMenu.add(new JRadioButtonMenuItem("RadioButtonMenuItem"));
        fileMenu.add(new JCheckBoxMenuItem("CheckBoxMenuItem"));
        fileMenu.add(new JMenu("Sub menu"));

        fileMenu.addSeparator();

        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JMenuItem){
            JMenuItem item = (JMenuItem) e.getSource();
            String text = item.getText();
            System.out.println(text);
        }
    }
}
