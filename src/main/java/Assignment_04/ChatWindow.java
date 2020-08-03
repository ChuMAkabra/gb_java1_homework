package Assignment_04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

    private final JTextArea chatText;
    private final JTextField editText;

    public ChatWindow(){
        // основные параметры окна
        setTitle("Chat");
        setBounds(500, 300, 700, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // выбор компановщика элементов
        setLayout(new GridBagLayout());

        // создание текстового поля (и скролл-баров)
        chatText = new JTextArea();
        chatText.setBackground(new Color(230,230,230));
        chatText.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatText);

        // установка ограничений текстового поля чата
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 0.98;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(chatScroll, constraints);

        // создание однострочного поля ввода (и скролл-баров)
        editText = new JTextField();
        editText.addActionListener(new sendTextActionListener());

        // установка ограничений однострочного поля ввода
        JScrollPane editScroll = new JScrollPane(editText);
        constraints.weightx = 0.99;
        constraints.weighty = 0.02;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,0,5,0);
        add(editScroll, constraints);

        // создание кнопки
        JButton enterButton = new JButton("Send");
        enterButton.addActionListener(new sendTextActionListener());

        // установка ограничений кнопки
        constraints.gridx = 1;
        constraints.weightx = 0.01;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = null;
        constraints.insets = new Insets(0,0,0,0);
        add(enterButton, constraints);

        setVisible(true);
    }

    public class sendTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String chat = chatText.getText();
            chatText.setText(chat + (!chat.equals("") ? "\n" : "") + "Me:\n" + editText.getText());
            editText.setText("");
        }
    }
}