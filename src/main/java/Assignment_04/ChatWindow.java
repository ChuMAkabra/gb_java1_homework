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
        chatText.setEditable(false); // теперь текст нельзя редактировать вручную
        JScrollPane chatScroll = new JScrollPane(chatText);

        // установка ограничений текстового поля чата
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;    // занимает 100% по горизонтали
        constraints.weighty = 0.98; // занимает  98% по вертикали
        constraints.gridx = 0;      // начинается в  первой колонке
        constraints.gridy = 0;      // начинается на первой строке
        constraints.gridwidth = 2;  // занимает обе колонки первой строки
        add(chatScroll, constraints);

        // создание однострочного поля ввода (и скролл-баров)
        editText = new JTextField();
        editText.addActionListener(new sendTextActionListener());

        // установка ограничений однострочного поля ввода
        JScrollPane editScroll = new JScrollPane(editText);
        constraints.weightx = 1;    // занимает  100% по горизонтали
        constraints.weighty = 0.02; // занимает   2% по вертикали
        constraints.gridx = 0;      // начинается в  первой колонке
        constraints.gridy = 1;      // начинается на второй строке
        constraints.gridwidth = 1;  // занимает одну колонку второй строки
        constraints.insets = new Insets(5,0,5,0); // отступы от краев области
        add(editScroll, constraints);

        // создание кнопки
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new sendTextActionListener());

        // установка ограничений кнопки
        constraints.weightx = 0;    // занимает 0% по горизонтали (не меняет размер при расширении)
        constraints.weighty = 0;    // занимает 0% по вертикали (не меняет размер при расширении)
        constraints.gridx = 1;      // начинается во второй колонке
        constraints.fill = GridBagConstraints.NONE;     // не меняет размер при расширении
        constraints.anchor = GridBagConstraints.CENTER; // располагается по центру своей области
        constraints.insets = new Insets(0,0,0,0); // не имеет отступов
        add(sendButton, constraints);

        setVisible(true);
    }

    // обработчик события отправки сообщений (по нажатии Enter или кнопки Send)
    public class sendTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String edit = editText.getText();
            if (!edit.equals("")) {
                String chat = chatText.getText();
                chatText.setText(chat + (!chat.equals("") ? "\n" : "") + "Me:\n" + editText.getText());
                editText.setText("");
            }
        }
    }
}