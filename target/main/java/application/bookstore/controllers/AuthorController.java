package application.bookstore.controllers;

import application.bookstore.auxiliaries.FileHandler;
import application.bookstore.models.Author;
import application.bookstore.views.AuthorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AuthorController {

    private static AuthorView authorView;

    public AuthorController(AuthorView authorView) {
        this.authorView = authorView;
        setSaveListener();
        setDeleteListener();
        setSearchListener();
        setEditListener();
    }

    private void setEditListener() {
        // anonymous inner class
        authorView.getFirstNameCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Author, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Author, String> event) {
                Author authorToEdit = event.getRowValue();
                int index = Author.getAuthors().indexOf(authorToEdit);
                Author.getAuthors().get(index).setFirstName(event.getNewValue());
                Author.saveToNewFile();
            }
        });
        // with lambda now
        authorView.getLastNameCol().setOnEditCommit(event -> {
            Author authorToEdit = event.getRowValue();
            int index = Author.getAuthors().indexOf(authorToEdit);
            Author.getAuthors().get(index).setLastName(event.getNewValue());
            Author.saveToNewFile();
        });
        // if the user clicks edit button, save the changes into the file
        authorView.getEditBtn().setOnAction(e -> {
            try {
                // todo change it
                FileHandler.overwriteCurrentListToFile(Author.DATA_FILE, Author.getAuthors());
                authorView.getResultLabel().setText("Authors were updated successfully");
            } catch (IOException ex) {
                authorView.getResultLabel().setText("Writing authors to the file failed!");
                ex.printStackTrace();
            }
        });
    }

    private void setSearchListener() {
        authorView.getSearchView().getClearBtn().setOnAction(e -> {
            authorView.getSearchView().getSearchField().setText("");
            authorView.getTableView().setItems(FXCollections.observableArrayList(Author.getAuthors()));
        });
        authorView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = authorView.getSearchView().getSearchField().getText().toLowerCase();
            if (authorExists(searchText)) {
                authorView.getTableView().getItems().removeAll(Author.getAuthors());
                for (Author author : Author.getAuthors()) {
                    String allSt = author.getFirstName().toLowerCase() + author.getLastName().toLowerCase();
                    if (allSt.contains(searchText)) {
                        authorView.getTableView().getItems().add(author);
                    }
                }
            }

        });
        authorView.getSearchView().getClearBtn().setOnAction(event -> {
            authorView.getSearchView().getSearchField().clear();
            authorView.getTableView().getItems().clear();
            authorView.getTableView().getItems().addAll(Author.getAuthors());
        });
    }

    private void setDeleteListener() {
        authorView.getDeleteBtn().setOnAction(e -> {
            ObservableList<Author> authorsInTable = authorView.getTableView().getItems();
            ObservableList<Integer> indices = authorView.getTableView().getSelectionModel().getSelectedIndices();
            for (int index : indices)
                authorsInTable.get(index).deleteFromFile();
            authorView.getTableView().setItems(FXCollections.observableArrayList(Author.getAuthors()));
            authorView.getResultLabel().setTextFill(Color.DARKGREEN);
            authorView.getResultLabel().setText("Authors deleted successfully!");
        });
    }

    private void setSaveListener() {
        authorView.getSaveBtn().setOnAction(e -> {
            String firstName = authorView.getFirstNameField().getText();
            String lastName = authorView.getLastNameField().getText();
            Author author = new Author(firstName, lastName);
            System.out.println(Author.findAuthor(author));
            if (Author.findAuthor(author) == null) {
                authorView.getTableView().getItems().add(author);
                Author.getAuthors().add(author);
                Author.saveToNewFile();
                authorView.getResultLabel().setText("Author created successfully!");
                authorView.getResultLabel().setTextFill(Color.DARKGREEN);
                authorView.getFirstNameField().setText("");
                authorView.getLastNameField().setText("");
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Author creation failed");
                a.show();
            }

        });

    }

    public static void displayAuthor() {
        VBox vb = new VBox(authorView.getTableView());
        Scene scene = new Scene(vb);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private boolean authorExists(String name) {
        for (Author author : Author.getAuthors()) {
            String fullname = author.getFirstName().toLowerCase() + author.getLastName().toLowerCase();
            if (fullname.contains(name)) {
                return true;
            }
        }
        return false;
    }
}


    /*private AuthorView authorView;
    public AuthorController(AuthorView authorView) {
        this.authorView = authorView;
        setSaveListener();
        setDeleteListener();
        setSearchListener();
        setEditListener();
    }

    private void setEditListener() {
        // anonymous inner class
        authorView.getFirstNameCol().setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Author, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Author, String> event) {
                Author authorToEdit = event.getRowValue();
                int index = Author.getAuthors().indexOf(authorToEdit);
                Author.getAuthors().get(index).setFirstName(event.getNewValue());
            }
        });
        // with lambda now
        authorView.getLastNameCol().setOnEditCommit(event -> {
            Author authorToEdit = event.getRowValue();
            int index = Author.getAuthors().indexOf(authorToEdit);
            Author.getAuthors().get(index).setLastName(event.getNewValue());
        });
        // if the user clicks edit button, save the changes into the file
        authorView.getEditBtn().setOnAction(e -> {
            try {
                // todo change it
                FileHandler.overwriteCurrentListToFile(Author.DATA_FILE, Author.getAuthors());
                authorView.getResultLabel().setText("Authors were updated successfully");
            } catch (IOException ex) {
                authorView.getResultLabel().setText("Writing authors to the file failed!");
                ex.printStackTrace();
            }
        });
    }

    private void setSearchListener() {
        authorView.getSearchView().getClearBtn().setOnAction(e -> {
            authorView.getSearchView().getSearchField().setText("");
            authorView.getTableView().setItems(FXCollections.observableArrayList(Author.getAuthors()));
        });
        authorView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = authorView.getSearchView().getSearchField().getText();
            ArrayList<Author> searchResults = Author.getSearchResults(searchText);
            authorView.getTableView().setItems(FXCollections.observableArrayList(searchResults));
        });
    }

    private void setDeleteListener() {
        authorView.getDeleteBtn().setOnAction(e -> {
            ObservableList<Author> authorsInTable = authorView.getTableView().getItems();
            ObservableList<Integer> indices = authorView.getTableView().getSelectionModel().getSelectedIndices();
            for (int index: indices)
                authorsInTable.get(index).deleteFromFile();
            authorView.getTableView().setItems(FXCollections.observableArrayList(Author.getAuthors()));
            authorView.getResultLabel().setTextFill(Color.DARKGREEN);
            authorView.getResultLabel().setText("Authors deleted successfully!");
        });
    }

    private void setSaveListener() {
        authorView.getSaveBtn().setOnAction(e -> {
            String firstName = authorView.getFirstNameField().getText();
            String lastName = authorView.getLastNameField().getText();
            Author author = new Author(firstName, lastName);
            if (author.saveInFile()){
                authorView.getTableView().getItems().add(author);
                authorView.getResultLabel().setText("Author created successfully!");
                authorView.getResultLabel().setTextFill(Color.DARKGREEN);
                authorView.getFirstNameField().setText("");
                authorView.getLastNameField().setText("");
            }
            else {
                authorView.getResultLabel().setText("Author creation failed!");
                authorView.getResultLabel().setTextFill(Color.DARKRED);
            }

        });
    }
    public static void displayAuthor(){
        VBox vb=  new VBox(AuthorView.getTableView());
        Scene scene = new Scene(vb);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }*/
