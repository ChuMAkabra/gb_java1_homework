package Assignment_07.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    FXMLLoader loader;
    public Thread authWaitThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("/client7.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("BrainsChat Client");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> this.stop());
    }

    public void stop() {
        ((Controller) loader.getController()).sendExit();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}