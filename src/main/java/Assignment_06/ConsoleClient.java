package Assignment_06;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {
    private final String SERVER_ADDR = "localhost"; // адрес эхо-сервера, к кот. подключ. клиент
    private final int SERVER_PORT = 8189;           // номер порта
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    public Scanner scanner = new Scanner(System.in);

    public ConsoleClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Добро пожаловать в наш чат!");
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = input.readUTF();
                    if (strFromServer.equals("/end")) closeConnection();
                    System.out.println("Сервер: " + strFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
                closeConnection();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                sendMessage();
            }
        }).start();
    }

    private void sendMessage() {
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

    private void closeConnection() {
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
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Сервер был отключен по инициативе пользователя");
        System.exit(0);
    }

    public static void main(String[] args) {
        new ConsoleClient();
    }
}
