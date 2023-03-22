package com.example.client.controller;


import com.example.client.Entity.BookEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EditBookController {

    private Stage editBookStage;
    private BookEntity book;
    private int bookID;
    private boolean okClicked = false;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField AddKind;

        @FXML
        private TextField AddTitle;

        @FXML
        private TextField AddYear;

        @FXML
        private ComboBox SetAuthor;

        @FXML
        private ComboBox SetPublisher;

    @FXML
    private void SaveBook() throws IOException {
        if (isInputValid()) {
            book.setTitle(AddTitle.getText());
            /*book.setAuthor(SetAuthor.getText());
            book.setPublisher(SetPublisher.getText());*/
            book.setYear(AddYear.getLength());
            book.setKind(AddKind.getText());

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
            assert AddKind != null : "fx:id=\"AddKind\" was not injected: check your FXML file 'EditBook.fxml'.";
            assert AddTitle != null : "fx:id=\"AddTitle\" was not injected: check your FXML file 'EditBook.fxml'.";
            assert AddYear != null : "fx:id=\"AddYear\" was not injected: check your FXML file 'EditBook.fxml'.";
            assert SetAuthor != null : "fx:id=\"SetAuthor\" was not injected: check your FXML file 'EditBook.fxml'.";
            assert SetPublisher != null : "fx:id=\"SetPublisher\" was not injected: check your FXML file 'EditBook.fxml'.";

        }

    public boolean isOkClicked() {
            return isOkClicked();
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (AddTitle.getText() == null || AddTitle.getText().length() == 0)
            errorMessage += "Не обнаружено название книги \n";
      /*  if (SetAuthor.getText()== null || SetAuthor.getText().length() == 0)
            errorMessage += "Не обнаружен автор книги! \n";
        if (SetPublisher.getText() == null || SetPublisher.getText().length() == 0)
            errorMessage += "Нe обнаружено издание книги!\n";*/
        if (AddYear.getText() == null || AddYear.getText().length() == 0)
            errorMessage += "Нe обнаружен год книги!\n";
        if (AddKind.getText() == null || AddKind.getText().length() == 0)
            errorMessage += "Нe обнаружен жанр книги!\n";

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



