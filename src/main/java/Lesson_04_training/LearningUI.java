package Lesson_04_training;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class LearningUI extends JFrame {
    public LearningUI() {
        // стандартный набор команд
        setTitle("Simple window");                               // заголовок
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // по умолчанию - HIDE_ON_CLOSE
        setBounds(500, 200, 800, 600);         // координаты и размеры окна

        // 1) расположение кнопок по сторонам света:
//        applyBorderLayout();
        // 2) расположение кнопок в строку (можно в столбец):
//        applyBoxLayout();
        // 3) расположение кнопок в строку, с переносом на следующую, если не хватает места:
//        applyFlowLayout();
        // 4) расположение кнопок в виде таблицы:
//        applyGridLayout();
        applyUserLayout();
        // вывод формы на экран
        setVisible(true);

    }

    private void applyUserLayout() {
        // выбор компоновщика элементов
        setLayout(new GridLayout(2,2));
        // создаём четыре панели для размещения элементов и задаем каждой отличный цвет
        JPanel[] jp = new JPanel[4];
        for (int i = 0; i < jp.length; i++) {
            jp[i] = new JPanel();
            add(jp[i]);
            jp[i].setBackground(new Color(100 + i * 40, 100 + i * 40, 100 + i * 40));
        }

        // 1) первая панель:
        jp[0].setLayout(new BorderLayout());                // выбор компоновщика элементов
        JTextArea textArea = new JTextArea();               // многострочное текстовое поле
        JScrollPane scrollPane = new JScrollPane(textArea); // панель прокрутки с текстовым полем
        jp[0].add(scrollPane);                              // добавить элемент на панель 1

        // 2 вторая панель:
        jp[1].setLayout(new FlowLayout());                  // выбор компоновщика элементов
        // добавляем радиокнопки
        JRadioButton[] radioButtons = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();        // группа для объединения радиокнопок
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton("Option #" + i);
            buttonGroup.add(radioButtons[i]);        // добавить кнопку в группу
            jp[1].add(radioButtons[i]);              // добавить кнопку (!не группу) на панель 2
        }

        JCheckBox[] checkBoxes = new JCheckBox[4];
        for (int i = 0; i < 4; i++) {
            checkBoxes[i] = new JCheckBox("Check #" + i);
            jp[1].add(checkBoxes[i]);                // добавить кнопку на панель 2
        }

        // 3) третья панель:
        jp[2].setLayout(new FlowLayout());              // выбор компоновщика элементов
        // создание комбинированных списков
        String[] comboString = {"Item #1", "Item #2", "Item #3", "Item #4"}; // элементы списков
        JComboBox<String> comboBox1 = new JComboBox<>(comboString); // заполнить список элементами
        JComboBox<String> comboBox2 = new JComboBox<>(comboString); // заполнить список элементами
        jp[2].add(comboBox1);
        jp[2].add(comboBox2);
        // добавить обработчик событий выбора одного из пунктов с помощью "анониманого класса"
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Objects.requireNonNull(comboBox1.getSelectedItem()).toString());
            }
        });
        // добавить обработчик событий выбора одного из пунктов с помощью "лямбда-выражения"
        comboBox2.addActionListener(e ->
                System.out.println(Objects.requireNonNull(comboBox2.getSelectedItem()).toString()));

        // 4) четвертая панель
        jp[3].setLayout(null);                                  // выключение компоновщика элементов
        JLabel label = new JLabel("Value: ");              // нередактируемое текстовое поле
        JSlider slider = new JSlider(0, 100, 50);// слайдер (0-100, по дефолту 50)
        // установка координат и размеров элементов вручную (без помощи компоновщика)
        label.setBounds(10, 10, 100, 20);
        slider.setBounds(20, 40, 300, 100);
        jp[3].add(label);
        jp[3].add(slider);
        // добавить обработчик изменения ползунка (слайдера)
        slider.addChangeListener(e -> label.setText("Value: " + slider.getValue()));

        // 5) меню
        // создать панель меню
        JMenuBar menuBar = new JMenuBar();
        // создать выпадающие меню
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        // создать элементы меню
        JMenuItem itemNew = new JMenuItem("New");
        JSeparator separator = new JSeparator();
        JMenuItem itemExit = new JMenuItem("Exit");
        // связать панель меню, выпадающие меню и их элементы
        setJMenuBar(menuBar);    // панель меню добавляется этой командой
        menuBar.add(fileMenu);
        fileMenu.add(itemNew);
        fileMenu.add(separator); // разделительная линия в меню
        fileMenu.add(itemExit);
        menuBar.add(editMenu);

        // обработчик выбора Exit
        itemExit.addActionListener(e -> System.exit(0));
        // обработчик закрытия окна
        /** НЕ РАБОТАЕТ!!!*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("BYE");
            }
        });

    }

    private void applyBorderLayout() {
        // создание кнопок
        JButton[] buttons = new JButton[10];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("bordL#" + i);
        }

        setLayout(new BorderLayout());                           // выбор компоновщика элементов
        add(buttons[0], BorderLayout.EAST);                      // добавление кнопки на форму
        add(buttons[1], BorderLayout.WEST);
        add(buttons[2], BorderLayout.NORTH);
        add(buttons[3], BorderLayout.SOUTH);
        add(buttons[4], BorderLayout.CENTER);
    }

    private void applyBoxLayout() {
        // выбор компоновщика элементов
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        // создание кнопок
        JButton[] buttons = new JButton[10];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("boxL#" + i);
            buttons[i].setAlignmentX(RIGHT_ALIGNMENT);
            System.out.println(buttons[i].getAlignmentX());
//            buttons[i].setAlignmentY(TOP_ALIGNMENT);
            add(buttons[i]);
        }
    }

    private void applyFlowLayout() {
        // выбор компоновщика элементов
        setLayout(new FlowLayout());
        // создание кнопок
        JButton[] buttons = new JButton[10];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("flowL#" + i);
            add(buttons[i]);
        }
    }

    private void applyGridLayout() {
        // выбор компоновщика элементов
        setLayout(new GridLayout(5,2));
        // создание кнопок
        JButton[] buttons = new JButton[10];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("gridL#" + i);
            add(buttons[i]);
        }
    }
}
