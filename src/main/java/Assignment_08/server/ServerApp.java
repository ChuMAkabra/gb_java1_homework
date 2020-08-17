package Assignment_08.server;

public class ServerApp {
    public static void main(String[] args) {
        // AuthService authService = new BaseAuthService();
        AuthService authService = new DBAuthService();
        new MyServer(authService);
    }
}
