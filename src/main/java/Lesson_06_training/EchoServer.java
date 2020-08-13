package Lesson_06_training;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int PORT = 8189;

    public static void main(String[] args) {
//        Socket socket = null;
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидаем подключения...");
            Socket client = server.accept();
            System.out.println("Клиент подключился");
            DataInputStream   input = new DataInputStream (client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            while (true) {
                String inStr = input.readUTF();
                if (inStr.equals("/end")) break;
                output.writeUTF("Эхо: " + inStr);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
