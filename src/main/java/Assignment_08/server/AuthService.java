package dev.astamur.geekbrains.lessons.lesson8.server;

public interface AuthService {
    void start();

    String getNickByLoginPass(String login, String pass);

    void stop();
}
