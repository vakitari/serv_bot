package com.example.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.client.Entity.BookEntity;
import com.example.client.Entity.PublisherEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.example.client.controller.MainController.booksData;

public class AddPublisherController {

        private Stage editPublisherStage;
        public static ObservableList<PublisherEntity> pubData = FXCollections.observableArrayList();
        private BookEntity publisher;
        private int publisherID;
        private boolean okClicked = false;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField PublisherCity;

        @FXML
        private TextField PublisherTitle;


        @FXML
        private void SavePublisher() throws IOException {
                if (isInputValid()) {
                        publisher.getPublisher().setTitle(PublisherTitle.getText());
                        publisher.getPublisher().setCity(PublisherCity.getText());

                        okClicked = true;
                        editPublisherStage.close();
                        booksData.set(publisherID, publisher);
                }
        }
        @FXML
        private void ClosePublisher() {
                editPublisherStage.close();
        }

        @FXML
        void initialize() {
                assert PublisherCity != null : "fx:id=\"PublisherCity\" was not injected: check your FXML file 'EditPublisher.fxml'.";
                assert PublisherTitle != null : "fx:id=\"PublisherName\" was not injected: check your FXML file 'EditPublisher.fxml'.";


        }
        private boolean isInputValid() {
                String errorMessage = "";

                if (PublisherTitle.getText() == null || PublisherTitle.getText().length() == 0)
                        errorMessage += "Не обнаружено название книги \n";
                if (PublisherCity.getText() == null || PublisherCity.getText().length() == 0)
                        errorMessage += "Нe обнаружен год книги!\n";


                else {
                        if (errorMessage.length() == 0) return true;
                        else {
                                // Показываем сообщение об ошибке.
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.initOwner(editPublisherStage);
                                alert.setTitle("Ошибка заполнения");
                                alert.setHeaderText("Пожалуйста, укажите корректные значения текстовых полей");
                                alert.setContentText(errorMessage);

                                alert.showAndWait();

                                return false;
                        }
                }
                return true;
        }
}



