package dev.astamur.geekbrains.lessons.lesson8.server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBAuthService implements AuthService {
    private final String url;
    private final String user;
    private final String password;

    public DBAuthService() {
        Properties properties = readProperties();
        this.url = properties.getProperty("db.url");
        this.user = properties.getProperty("db.user");
        this.password = properties.getProperty("db.password");
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }


    @Override
    public String getNickByLoginPass(String login, String pass) {
        User user = getUser(login, pass);
        return user != null ? user.getNick() : null;
    }

    private User getUser(String login, String password) {
        try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement statement = connection.prepareStatement("SELECT id, login, nick from users WHERE login = ? and password = ?")) {

            statement.setString(1, login);
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setNick(rs.getString("nick"));
                    return user;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while querying database", e);
        }

        return null;
    }

    private static Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream is = DBAuthService.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Error reading database properties", e);
        }

        return properties;
    }
}
