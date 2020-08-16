package Lesson_07_training;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}
