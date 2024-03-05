package com.example.assignment1_extra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.Objects;

//Main class
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //Start class
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        //Create a new scene
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        //css file import
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set the icon for the stage

        stage.setTitle("Manga ranking");

        // Load the image for the icon
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("comic.jpeg")));


        // Set the icon for the stage
        stage.getIcons().add(icon);

        // Show the stage
        stage.show();

    }
}
