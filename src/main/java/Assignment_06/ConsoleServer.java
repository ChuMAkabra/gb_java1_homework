package Assignment_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static final int PORT = 8189;
    public static Scanner svScanner = new Scanner(System.in);
    public static ServerSocket server;
    public static DataOutputStream output;
    public static DataInputStream  input;

    public static void main(String[] args) {
//        Socket socket = null;
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидаем подключения...");
            Socket client = server.accept();
            System.out.println("Клиент подключился");
            input = new DataInputStream (client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String inStr = null;
                        inStr = input.readUTF();
                        if (inStr.equals("/end")) closeConnection();
                        System.out.println("Клиент: " + inStr);
                    }
                    } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Сервер был отключен!");
                    closeConnection();
                    }
            }).start();

            new Thread(() -> {
                while (true) {
                    sendMessage();
                }
            }).start();

//            while (true) {
//                String inStr = input.readUTF();
//                if (inStr.equals("/end")) closeConnection();
//                System.out.println("Клиент: " + inStr);
//            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
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
//        try {
//            server.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Сервер был отключен по инициативе пользователя");
        System.exit(0);
    }

    private static void sendMessage() {
        if (svScanner.hasNext()) {
            try {
                String outStr = svScanner.nextLine();
                output.writeUTF(outStr);
                if (outStr.equals("/end")) closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
