package com.example.client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private TextField txtInput;

    public void showMessage() {
        comboBox.getItems().add(txtInput.getText());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().add("oss");
    }

    public void OnTuch(ActionEvent actionEvent) {
        String selectedValue = comboBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedValue);
    }
}