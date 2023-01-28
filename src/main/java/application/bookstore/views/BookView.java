package application.bookstore.views;

import application.bookstore.controllers.BookController;
import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.Category;
import application.bookstore.ui.CreateButton;
import application.bookstore.ui.DeleteButton;
import application.bookstore.ui.EditButton;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static application.bookstore.models.Author.getAuthors;

/*public class BookView extends View {
    private final BorderPane borderPane = new BorderPane();
    private final TableView<Book> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField isbnField = new TextField();
    private final TextField titleField = new TextField();
    private final TextField purchasedPriceField = new TextField();
    private final TextField sellingPriceField = new TextField();
    private final TextField quantityField = new TextField();
    private final ComboBox<Author> authorsComboBox = new ComboBox<>();
    private final Button saveBtn = new CreateButton();
    private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, Float> purchasedPriceCol = new TableColumn<>("Purchased Price");
    private final TableColumn<Book, Float> sellingPriceCol = new TableColumn<>("Selling Price");
    private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
    private final TableColumn<Book, Integer> quantityCol = new TableColumn<>("Quantity");
//    private final TableColumn<Book, LocalDate> dateCol = new TableColumn<>("Date");
    private final TableColumn<Book, String> categoryCol = new TableColumn<>("Category");

    private final Label resultLabel = new Label("");
    private final SearchView searchView = new SearchView("Search for a book");
    private final ComboBox<Category> categoryfield = new ComboBox();
//    DatePicker dateOfBook = new DatePicker();
    private TextField stockField = new TextField();
//    Label stockLabel = new Label("Publisher", stockField);

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public HBox getFormPane() {
        return formPane;
    }

    public TableColumn<Book, String> getAuthorCol() {
        return authorCol;
    }

    public ComboBox<Category> getCategoryfield() {
        return categoryfield;
    }

//    public DatePicker getDateOfBook() {
//        return dateOfBook;
//    }

//    public void setDateOfBook(DatePicker dateOfBook) {
//        this.dateOfBook = dateOfBook;
//    }

    public TextField getStockField() {
        return stockField;
    }

    public void setStockField(TextField stockField) {
        this.stockField = stockField;
    }

//    public Label getStockLabel() {
//        return stockLabel;
//    }

//    public void setStockLabel(Label stockLabel) {
//        this.stockLabel = stockLabel;
//    }

    public TableView<Book> getTableView() {
        return tableView;
    }

    public TextField getIsbnField() {
        return isbnField;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getPurchasedPriceField() {
        return purchasedPriceField;
    }

    public TextField getSellingPriceField() {
        return sellingPriceField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public ComboBox<Author> getAuthorsComboBox() {
        return authorsComboBox;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public TableColumn<Book, String> getIsbnCol() {
        return isbnCol;
    }

    public TableColumn<Book, String> getTitleCol() {
        return titleCol;
    }

    public TableColumn<Book, Float> getPurchasedPriceCol() {
        return purchasedPriceCol;
    }

    public TableColumn<Book, Float> getSellingPriceCol() {
        return sellingPriceCol;
    }

    public TableColumn<Book, Integer> getQtyCol() {
        return quantityCol;
    }



    public Label getResultLabel() {
        return resultLabel;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    private boolean editable;

    public BookView(boolean editable) {
        this.editable=editable;
        setTableView();
        setForm();
        // inject the controller
        new BookController(this);
    }

    private void setForm() {
        Label categoryFieldLabel = new Label("Category ", categoryfield);
        categoryFieldLabel.setContentDisplay(ContentDisplay.TOP);
//        Label dateLabel = new Label("Date", dateOfBook);
//        dateLabel.setContentDisplay(ContentDisplay.TOP);
//        stockLabel.setContentDisplay(ContentDisplay.TOP);
        authorsComboBox.getItems().setAll(getAuthors());
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        Label isbnLabel = new Label("ISBN: ", isbnField);
        isbnLabel.setContentDisplay(ContentDisplay.TOP);
        Label titleLabel = new Label("Title: ", titleField);
        titleLabel.setContentDisplay(ContentDisplay.TOP);
        Label purchasedPriceLabel = new Label("Purchased price", purchasedPriceField);
        purchasedPriceLabel.setContentDisplay(ContentDisplay.TOP);
        Label sellingPriceLabel = new Label("Selling price", sellingPriceField);
        Label quantityLabel = new Label("Quantity", quantityField);
        quantityLabel.setContentDisplay(ContentDisplay.TOP);
        sellingPriceLabel.setContentDisplay(ContentDisplay.TOP);
        Label authorLabel = new Label("Author", authorsComboBox);
        authorsComboBox.getItems().setAll(getAuthors());
        // set default selected the first author
        if (!getAuthors().isEmpty())
            authorsComboBox.setValue(getAuthors().get(0));
        authorLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(isbnLabel, titleLabel, purchasedPriceLabel, sellingPriceLabel,
                authorLabel, categoryFieldLabel, quantityLabel, saveBtn);
    }

    private void setTableView() {

        categoryfield.getItems().addAll(Category.ACTION, Category.CLASSIC, Category.COMIC, Category.DETECTIVE, Category.FANTASY, Category.HISTORICAL
                , Category.HORROR, Category.LITERAL);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );
        // to edit the value inside the table view
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        purchasedPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("purchasedPrice")
        );
        purchasedPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        sellingPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );
        sellingPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));


        authorCol.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );


        // -----------------------------------EDIT AUTHOR IN "ADD A BOOK" ---------------------------------------------------
//        authorCol.setCellFactory(ComboBoxTableCell.forTableColumn("Test", "Foo", "Bar"));
        authorCol.setCellFactory(ComboBoxTableCell.forTableColumn(Arrays.toString(new ArrayList[]{getAuthors()})));



        quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//
//        dateCol.setCellValueFactory(
//                new PropertyValueFactory<>("date")
//        );
//        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        categoryCol.setCellValueFactory(
                new PropertyValueFactory<>("category")
        );
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(isbnCol, titleCol, purchasedPriceCol, sellingPriceCol, authorCol, quantityCol);

    }


    @Override
    public Parent getView() {
        borderPane.setCenter(tableView);
        if (editable) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(5);
            vBox.getChildren().addAll(formPane, resultLabel);
            borderPane.setBottom(vBox);
        }
        borderPane.setTop(searchView.getSearchPane());
        Scene scene = new Scene(borderPane, 1400, 450);
        Stage st = new Stage();
        st.setScene(scene);
        st.show();
        return borderPane;

    }
}*/

public class BookView extends View{
    private final BorderPane borderPane = new BorderPane();
    private  final TableView<Book> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField isbnField = new TextField();
    private final TextField titleField = new TextField();
    private final TextField purchasedPriceField = new TextField();
    private final TextField sellingPriceField = new TextField();
    private final ComboBox<Author> authorsComboBox = new ComboBox<>();
    private final Button saveBtn = new CreateButton();
    private final Button DeleteBtn = new DeleteButton();
    private final Button editButton = new EditButton();

    public Button getDeleteBtn() {
        return DeleteBtn;
    }

    public Button getEditButton() {
        return editButton;
    }

    private final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
    private final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
    private final TableColumn<Book, Float> purchasedPriceCol = new TableColumn<>("Purchased Price");
    private final TableColumn<Book, Float> sellingPriceCol = new TableColumn<>("Selling Price");
    private final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
    private final TableColumn<Book, Integer> quantityCol = new TableColumn<>("Quantity");
    private final TableColumn<Book, String> categoryCol = new TableColumn<>("Category");

    public TableColumn<Book, Integer> getQuantityCol() {
        return quantityCol;
    }

    private final Label resultLabel = new Label("");
    private final SearchView searchView = new SearchView("Search for a book by its title");
    private final ComboBox<Category> categoryfield = new ComboBox();
//    DatePicker dateOfBook = new DatePicker();

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public HBox getFormPane() {
        return formPane;
    }

    public TableColumn<Book, String> getAuthorCol() {
        return authorCol;
    }

    public ComboBox<Category> getCategoryfield() {
        return categoryfield;
    }

//    public DatePicker getDateOfBook() {
//        return dateOfBook;
//    }
//
//    public void setDateOfBook(DatePicker dateOfBook) {
//        this.dateOfBook = dateOfBook;
//    }

    public  TableView<Book> getTableView() {
        return tableView;
    }

    public TextField getIsbnField() {
        return isbnField;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getPurchasedPriceField() {
        return purchasedPriceField;
    }

    public TextField getSellingPriceField() {
        return sellingPriceField;
    }

    public ComboBox<Author> getAuthorsComboBox() {
        return authorsComboBox;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public TableColumn<Book, String> getIsbnCol() {
        return isbnCol;
    }

    public TableColumn<Book, String> getTitleCol() {
        return titleCol;
    }

    public TableColumn<Book, Float> getPurchasedPriceCol() {
        return purchasedPriceCol;
    }

    public TableColumn<Book, Float> getSellingPriceCol() {
        return sellingPriceCol;
    }

    TextField quantityField = new TextField();

    public TextField getQuantityField() {
        return quantityField;
    }

    public Label getResultLabel() {
        return resultLabel;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    private boolean editable;

    public BookView(boolean editable) {
        this.editable=editable;
        setTableView();
        setForm();
        // inject the controller
        new BookController(this);
    }

    private void setForm() {
        Label categoryFieldLabel = new Label("Category ",categoryfield);
        categoryFieldLabel.setContentDisplay(ContentDisplay.TOP);
        authorsComboBox.getItems().setAll(Author.getAuthors());
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        Label isbnLabel = new Label("ISBN: ", isbnField);
        isbnLabel.setContentDisplay(ContentDisplay.TOP);
        Label titleLabel = new Label("Title: ", titleField);
        titleLabel.setContentDisplay(ContentDisplay.TOP);
        Label purchasedPriceLabel = new Label("Purchased price", purchasedPriceField);
        purchasedPriceLabel.setContentDisplay(ContentDisplay.TOP);
        Label sellingPriceLabel = new Label("Selling price", sellingPriceField);
        sellingPriceLabel.setContentDisplay(ContentDisplay.TOP);
        Label quantityLabel = new Label("Quantity", quantityField);
        quantityLabel.setContentDisplay(ContentDisplay.TOP);
        Label authorLabel = new Label("Author", authorsComboBox);
        authorsComboBox.getItems().setAll(Author.getAuthors());
        // set default selected the first author
        if (!Author.getAuthors().isEmpty())
            authorsComboBox.setValue(Author.getAuthors().get(0));
        authorLabel.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(isbnLabel, titleLabel, purchasedPriceLabel, sellingPriceLabel, authorLabel,
                categoryFieldLabel,quantityLabel,saveBtn,DeleteBtn);
    }

    private void setTableView() {

        categoryfield.getItems().addAll(Category.ACTION,Category.CLASSIC,Category.COMIC,Category.DETECTIVE,Category.FANTASY,Category.HISTORICAL
                ,Category.HORROR,Category.LITERAL);

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));

        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );
        // to edit the value inside the table view
        isbnCol.setCellFactory(TextFieldTableCell.forTableColumn());

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        purchasedPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("purchasedPrice")
        );
        purchasedPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        sellingPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );
        sellingPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));


        authorCol.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );
//        authorCol.setCellFactory(ComboBoxTableCell.forTableColumn("Test", "Foo", "Bar"));
        authorCol.setCellFactory(ComboBoxTableCell.forTableColumn(Arrays.toString(new ArrayList[]{getAuthors()})));

        quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        categoryCol.setCellValueFactory(
                new PropertyValueFactory<>("category")
        );
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().addAll(isbnCol, titleCol, purchasedPriceCol, sellingPriceCol, authorCol, categoryCol , quantityCol);

    }

    @Override
    public Parent getView() {
        borderPane.setCenter(tableView);
        if(editable) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(5);
            vBox.getChildren().addAll(formPane, resultLabel);
            borderPane.setBottom(vBox);
        }
        borderPane.setTop(searchView.getSearchPane());
        Scene scene = new Scene(borderPane, 1500, 450);
        Stage st = new Stage();
        st.setScene(scene);
        st.show();
        return borderPane;

    }
}
