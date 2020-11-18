package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    private int height = 800;
    private int width = 800;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Image image = new Image("sample/icons/logo.jpg");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: BLUE");

        ToolBar toolBar = new ToolBar();
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        //toolBar.getItems().add(new WindowButtons());

        borderPane.setTop(toolBar);
        //primaryStage.getIcons().add(image);
        primaryStage.setTitle("Team Generator");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
