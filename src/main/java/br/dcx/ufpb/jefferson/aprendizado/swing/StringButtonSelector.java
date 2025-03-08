package br.dcx.ufpb.jefferson.aprendizado.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StringButtonSelector extends JFrame {

    private List<JButton> buttons;
    private List<String> selectedStrings;

    public StringButtonSelector(List<String> strings) {
        super("Selecionar Strings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        buttons = new ArrayList<>();
        selectedStrings = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String str : strings) {
            JButton button = new JButton(str);
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selectedStrings.contains(str)) {
                        selectedStrings.remove(str);
                        button.setBackground(null); // Desmarcar o botão
                    } else {
                        selectedStrings.add(str);
                        button.setBackground(Color.YELLOW); // Marcar o botão
                    }
                }
            });
            buttons.add(button);
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        JButton deleteButton = new JButton("Apagar Selecionados");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String str : selectedStrings) {
                    for (JButton button : buttons) {
                        if (button.getText().equals(str)) {
                            panel.remove(button);
                            buttons.remove(button);
                            break;
                        }
                    }
                }
                selectedStrings.clear();
                panel.revalidate();
                panel.repaint();
            }
        });

        add(deleteButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Alice");
        strings.add("Bob");
        strings.add("Charlie");
        strings.add("David");
        strings.add("Eve");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StringButtonSelector(strings).setVisible(true);
            }
        });
    }
}
