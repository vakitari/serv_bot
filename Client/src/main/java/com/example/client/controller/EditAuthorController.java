package com.example.client.controller;


import com.example.client.Entity.AuthorEntity;
import com.example.client.Entity.BookEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class EditAuthorController {

        private Stage editBookStage;
        private AuthorEntity author;
        private boolean okClicked = false;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField AuthorName_field;

        @FXML
        private TextField AuthorOtch_field;

        @FXML
        private TextField AuthorSur_field;

        @FXML
        private GridPane tableBooks;

        @FXML
        private void SaveBook() throws IOException {
            if (isInputValid()) {
                author.setName(AuthorName_field.getText());
                author.setSurName(AuthorSur_field.getText());
                author.setLastName(AuthorOtch_field.getText());

                okClicked = true;
                editBookStage.close();
                //booksData.set(bookID, book);
            }
        }
        @FXML
        private void CloseBook() {
            editBookStage.close();
        }

        @FXML
        void initialize() {
            assert AuthorName_field != null : "fx:id=\"AuthorName_field\" was not injected: check your FXML file 'EditAuthor.fxml'.";
            assert AuthorOtch_field != null : "fx:id=\"AuthorOtch_field\" was not injected: check your FXML file 'EditAuthor.fxml'.";
            assert AuthorSur_field != null : "fx:id=\"AuthorSur_field\" was not injected: check your FXML file 'EditAuthor.fxml'.";
            assert tableBooks != null : "fx:id=\"tableBooks\" was not injected: check your FXML file 'EditAuthor.fxml'.";

        }


        private boolean isInputValid() {
            String errorMessage = "";

            if (AuthorName_field.getText() == null || AuthorName_field.getText().length() == 0)
                errorMessage += "Не обнаружено название книги \n";
            if (AuthorSur_field.getText() == null || AuthorSur_field.getText().length() == 0)
                errorMessage += "Не обнаружен автор книги! \n";
            if (AuthorOtch_field.getText() == null || AuthorOtch_field.getText().length() == 0)
                errorMessage += "Нe обнаружено изни книи!\n";

            else {
                if (errorMessage.length() == 0) return true;
                else {
                    // Показываем сообщение об ошибке.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(editBookStage);
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




