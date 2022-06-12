package com.example.lb_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    public static File fileToEncode;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 860);

        stage.setTitle("ТИ: Лабораторная работа №1 вариант 5");
        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setCodeFile (File file) {
        fileToEncode = file;
    }
    public static File getCodeFile () {
        return fileToEncode;
    }

}