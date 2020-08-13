package Assignment_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class ConsoleServer extends ClientServer{

    private ConsoleServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен. Ожидаем подключения...");
            Socket client = server.accept();
            System.out.println("Клиент подключился");
            input = new DataInputStream (client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());

            inThread();
            outThread();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ConsoleServer();
    }
}
