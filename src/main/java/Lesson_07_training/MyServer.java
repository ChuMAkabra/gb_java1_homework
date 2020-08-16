package Lesson_07_training;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private List<ClientHandler> clients;
    private AuthService authService;


    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT))
        {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        }
        finally {
            if (authService != null) authService.stop();
        }
    }


    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy (String nick) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

    public synchronized void unsubscribe (ClientHandler o) {
        clients.remove(o);
    }

    public void broadcastMsg(String s) {
        for (ClientHandler client : clients) {
            client.sendMsg(s);
        }
    }
}