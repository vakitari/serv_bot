package com.example.client.controller;

import com.example.client.Application;
import com.example.client.Entity.BookEntity;
import com.example.client.Entity.PublisherEntity;
import com.example.client.utils.HTTPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static com.example.client.Application.showPublisherAddDialog;
import static com.example.client.controller.AddPublisherController.pubData;

public class MainController {

        public static String api = "http://localhost:28242/api/v1/book/";
        public static ObservableList<BookEntity> booksData = FXCollections.observableArrayList();
        static HTTPUtils http = new HTTPUtils();
        static Gson gson = new Gson();
        @FXML
        public TableView<BookEntity> tableBooks;

        @FXML
        private TableColumn<BookEntity, String> bookTitle;

        @FXML
        private TableColumn<BookEntity, String> bookAuthor;

        @FXML
        private TableColumn<BookEntity, String> bookPublisher;

        @FXML
        private TableColumn<BookEntity, String> bookYear;

        @FXML
        private TableColumn<BookEntity, String> bookKind;

        @FXML
        private void initialize() throws Exception {
                getData();
                updateTable();
        }

        private void updateTable() throws Exception {
                bookTitle.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
                bookPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
                bookYear.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
                bookKind.setCellValueFactory(new PropertyValueFactory<BookEntity, String>( "kind"));
                bookAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
                tableBooks.setItems(booksData);
        }

        @FXML
        private void add() throws IOException {
                try {
                BookEntity tempBook = new BookEntity();
                booksData.add(tempBook);
                //Application.showPersonEditDialog(tempBook, booksData.size() - 1);
                Long id = addBook(tempBook);
                tempBook.setId(id);
                }catch (Exception e){}
        }

        @FXML
        private void click_removeBook() throws IOException {
                BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                        System.out.println(selectedPerson.getId());
                        http.delete(api + "delete/", selectedPerson.getId());
                        booksData.remove(selectedPerson);
                } else {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Ничего не выбрано");
                        alert.setHeaderText("Отсутствует выбраный пользователь");
                        alert.setContentText("Пожалуйста, выберите пользователя в таблице");
                        alert.showAndWait();
                }
        }
        

        @FXML
        private void click_editBook() throws IOException {
                BookEntity selectedPerson = tableBooks.getSelectionModel().getSelectedItem();
                if (selectedPerson != null){
                        //Application.showPersonEditDialog(selectedPerson, booksData.indexOf(selectedPerson));
                        http.put(api + "update", gson.toJson(selectedPerson).toString());}
                else {
                        // Ничего не выбрано
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        //alert.initOwner(Application.getPrimaryStage());
                        alert.setTitle("Hичего не выбрно");
                        alert.setHeaderText("Отсутствует выбраный польватель");
                        alert.setContentText("Пожалуйста, выберите пользвоателя в таблице");
                        alert.showAndWait();
                }
        }

        public void AddPublisherController(){
                PublisherEntity tempBook = new PublisherEntity();
                pubData.add(tempBook);
                showPublisherAddDialog(tempBook, tempBook.getId());
        }

        public static void getData() throws Exception {
                String res = http.get(api, "all");
                System.out.println(res);
                // Получение базового значения по каркасу BaseEntity
                JsonObject base = gson.fromJson(res, JsonObject.class);
                JsonArray dataArr = base.getAsJsonArray("data");
                // Парсинг значений со вторго уровня вложенности (data) в массив книг в таблице ObservableList
                for (int i = 0; i < dataArr.size(); i++) {
                        BookEntity newBook = gson.fromJson(dataArr.get(i).toString(), BookEntity.class);
                        booksData.add(newBook);
                }
        }

        public static Long addBook(BookEntity book) throws IOException {
                System.out.println(book.toString());
                book.setId(null);
               String res = http.post(api + "add", gson.toJson(book).toString());
                JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
                Long tempId = jsonObject.getAsJsonObject("data").get("id").getAsLong();
                return tempId;

        }
                public static void updateBook (BookEntity book) throws IOException {
                http.put(api + "update", gson.toJson(book).toString());

                }
        }

