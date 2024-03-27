package br.eletra.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.util.Objects;

public class Main extends Application {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 625;

    @Override
    public void start(Stage primaryStage) throws Exception {

        String iconePath = "icon.png";

        File iconFile = new File(iconePath);
        if (iconFile.exists()) {
            Image icon = new Image(iconFile.toURI().toString());
            primaryStage.getIcons().add(icon);
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/screen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Projeto de integração");
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaxHeight(HEIGHT);

        String cssPath = Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm();
        if (cssPath != null) {
            primaryStage.getScene().getStylesheets().add(cssPath);
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}