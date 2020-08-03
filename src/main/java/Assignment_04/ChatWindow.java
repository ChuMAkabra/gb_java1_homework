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

        // ДОБАВЛЕНИЕ ОБРАБОТЧИКА СОБЫТИЙ для поля ввода (4 способами, для закрепления материала)
        fourWaysToAddAL();

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
        sendButton.addActionListener(this::sendTextAction); // добавление обработчика события

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

    // 4 способа добавить обработчик события
    private void fourWaysToAddAL() {
        // Вариант №1: анонимный класс
        editText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendTextAlgorithm();
            }
        });
        // Вариант №2: лямбда-выражение
        editText.addActionListener(e -> sendTextAlgorithm());
        // Вариант №3: ссылка на метод
        editText.addActionListener(this::sendTextAction);
        // Вариант №4: экземпляр обработчика событий
        editText.addActionListener(new sendTextActionListener());
    }

    // метод-аналог actionPerformed(ActionEvent e)
    private void sendTextAction(ActionEvent e) {
        sendTextAlgorithm();
    }

    // обработчик события отправки сообщений (по нажатии Enter или кнопки Send)
    private class sendTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sendTextAlgorithm();
        }
    }

    // алгоритм отправки сообщения
    private void sendTextAlgorithm() {
        String edit = editText.getText();
        if (!edit.equals("")) {
            String chat = chatText.getText();
            chatText.setText(chat + (!chat.equals("") ? "\n" : "") + "Me:\n" + edit);
            editText.setText("");
        }
    }
}