package com.example.lr_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private static File fileToCheck;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1285, 820);
        stage.setTitle("ТИ: Лабораторная работа №4 вариант 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static File getFileToCheck() {
        return fileToCheck;
    }

    public static void setFileToCheck(File file) {
        fileToCheck = file;
    }
}