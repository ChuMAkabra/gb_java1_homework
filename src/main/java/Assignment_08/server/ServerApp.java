package dev.astamur.geekbrains.lessons.lesson8.server;

public class ServerApp {
    public static void main(String[] args) {
        // AuthService authService = new BaseAuthService();
        AuthService authService = new DBAuthService();
        new MyServer(authService);
    }
}
