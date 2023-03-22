package com.example.client;

import com.example.client.Entity.BookEntity;
import com.example.client.Entity.PublisherEntity;
import com.example.client.controller.EditBookController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private AnchorPane main;


    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("main.fxml"));
            main = (AnchorPane) loader.load();


            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(main);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean showPublisherAddDialog(PublisherEntity pubObj, Long id) {
        try {
// Загрузка fxml создание сцены для всплывающего окна.
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(Application.class.getResource("EditPublisher.fxml"));
            AnchorPane page = (AnchorPane) Loader.load();
// Создание окна Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление книги");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            EditBookController controller = Loader.getController();
           /* controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj, id);*/
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {
        launch();
    }
}