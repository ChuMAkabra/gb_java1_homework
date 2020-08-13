package Assignment_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать
 * сообщения, как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне
 * написать «Привет», нажать Enter, то сообщение должно передаться на сервер и там отпечататься в
 * консоли. Если сделать то же самое на серверной стороне, то сообщение передается клиенту и
 * печатается у него в консоли. Есть одна особенность, которую нужно учитывать: клиент или сервер
 * может написать несколько сообщений подряд. Такую ситуацию необходимо корректно обработать.
 * Разобраться с кодом с занятия: он является фундаментом проекта-чата
 * *ВАЖНО! * Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет
 * ожидать второго/третьего/n-го клиентов
 */
public class ClientServer {
    public static final int PORT = 8189;
    public static DataOutputStream output;
    public static DataInputStream input;
    public static Scanner scanner = new Scanner(System.in);

    protected void inThread() {
        new Thread(() -> {
            try {
                while (true) {
                    String inStr = input.readUTF();
                    if (inStr.equals("/end")) closeConnection();
                    System.out.println("Клиент: " + inStr);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Сервер был отключен!");
                closeConnection();
            }
        }).start();
    }

    protected void outThread() {
        new Thread(() -> {
            while (true) {
                sendMessage();
            }
        }).start();
    }

    protected void sendMessage() {
        if (scanner.hasNext()) {
            try {
                String outStr = scanner.nextLine();
                output.writeUTF(outStr);
                if (outStr.equals("/end")) closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeConnection() {
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сервер был отключен по инициативе пользователя");
        System.exit(0);
    }
}

