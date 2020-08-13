package Assignment_06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public final class ConsoleClient extends ClientServer {
    private final String SERVER_ADDR = "localhost"; // адрес эхо-сервера, к кот. подключ. клиент

    private ConsoleClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Добро пожаловать в наш чат!");
    }

    private void openConnection() throws IOException {
        Socket socket = new Socket(SERVER_ADDR, PORT);
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

        inThread();
        outThread();
    }

    public static void main(String[] args) {
        new ConsoleClient();
    }
}