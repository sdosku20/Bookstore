package application.bookstore.views;

import application.bookstore.models.*;
import application.bookstore.ui.CreateButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BookOrder implements Serializable {
    private static final long serialVersionUID = 2716901475978004580L;
    private String name;
    private static final String FILE_PATH = "data/orders.ser";
    private LocalDate date;
    private User user;
    private   ArrayList<Ordered_Book> booksOrdered;
    private  static ArrayList<Ordered_Book> booksOrderedCopy=new ArrayList<>();
    int i;
    int j;
    private static ArrayList<BookOrder> orders = new ArrayList<>();


    public BookOrder() {
    }

    public BookOrder(String name, ArrayList<Ordered_Book> booksOrdered, LocalDate date, User user) {
        this.name = name;
        this.date = date;
        this.user = user;
        this.booksOrdered=booksOrdered;

    }
    public BookOrder(String name, ArrayList<Ordered_Book> booksOrdered, LocalDate date, User user,int i,int j) {
        this.name = name;
        this.date = date;
        this.user = user;
        this.booksOrdered=booksOrdered;
        this.i=i;
        this.j=j;
    }

    public ArrayList<Ordered_Book> getBooksOrdered() {
        return booksOrdered;
    }

    public void setBooksOrdered(ArrayList<Ordered_Book> booksOrdered) {
        this.booksOrdered = booksOrdered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<BookOrder> getOrders() {
        if (orders.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    BookOrder temp = (BookOrder) inputStream.readObject();
                    if (temp != null)
                        orders.add(temp);
                    else
                        break;
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of book file reached!");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return orders;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    static int lenght;
    public static Parent newOrder(){

        final TableView<Book> tableView = new TableView<>();
        final TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
        final TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        final TableColumn<Book, Float> purchasedPriceCol = new TableColumn<>("Purchased Price");
        final TableColumn<Book, Float> sellingPriceCol = new TableColumn<>("Selling Price");
        final TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        final TableColumn<Book, Integer> qcol = new TableColumn<>("Quantity");
//        final TableColumn<Book, LocalDate> dateCol = new TableColumn<>("Date");
        final TableColumn<Book, String> categoryCol = new TableColumn<>("Category");
        tableView.setItems(FXCollections.observableArrayList(Book.getBooks()));
        isbnCol.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );

        titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );

        purchasedPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("purchasedPrice")
        );

        sellingPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );

        authorCol.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );

        qcol.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );

//        dateCol.setCellValueFactory(
//                new PropertyValueFactory<>("date")
//        );
//        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

        tableView.getColumns().addAll(isbnCol, titleCol, purchasedPriceCol, sellingPriceCol, authorCol,qcol);
        tableView.setPrefSize(1000,300);


        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Ordered_Book> orderedBooks = new ArrayList<>();

        final TableView<Book> tableView_ = new TableView<>();
        final TableColumn<Book, String> isbnCol_ = new TableColumn<>("ISBN");
        final TableColumn<Book, String> titleCol_ = new TableColumn<>("Title");
        final TableColumn<Book, Float> purchasedPriceCol_ = new TableColumn<>("Purchased Price");
        final TableColumn<Book, Float> sellingPriceCol_ = new TableColumn<>("Selling Price");
        final TableColumn<Book, String> authorCol_ = new TableColumn<>("Author");
        final TableColumn<Book, Integer> qcol_ = new TableColumn<>("Quantity");
//        final TableColumn<Book, LocalDate> dateCol_ = new TableColumn<>("Date");
        final TableColumn<Book, String> categoryCol_ = new TableColumn<>("Category");
        tableView_.setItems(FXCollections.observableArrayList(books));
        isbnCol_.setCellValueFactory(
                new PropertyValueFactory<>("isbn")
        );

        titleCol_.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );

        purchasedPriceCol_.setCellValueFactory(
                new PropertyValueFactory<>("purchasedPrice")
        );

        sellingPriceCol_.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice")
        );

        authorCol_.setCellValueFactory(
                new PropertyValueFactory<>("author")
        );

        qcol_.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );

//        dateCol_.setCellValueFactory(
//                new PropertyValueFactory<>("date")
//        );
//        dateCol_.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));


        tableView_.getColumns().addAll(isbnCol_, titleCol_, purchasedPriceCol_, sellingPriceCol_, authorCol_,qcol_);
        tableView_.setPrefSize(1000,250);


        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView_.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        VBox pane = new VBox();


        TextField quantityField = new TextField();
        Label quatitylabel= new Label("Quantity",quantityField);
        quatitylabel.setContentDisplay(ContentDisplay.TOP);
        Button anotherBook = new Button("Add");
        Button remove = new Button("Remove");
        Button update = new Button("Refresh list");
        Button next = new Button("Save");
        update.setOnAction(event -> {
            tableView_.getItems().clear();
            tableView.getItems().clear();
            tableView.getItems().addAll(Book.getBooks2());
            orderedBooks.clear();

        });
        HBox paneHB = new HBox(quatitylabel, anotherBook, remove,update);
        paneHB.setPadding(new Insets(0, 0, 0, 70));
        paneHB.setSpacing(20);
        pane.setSpacing(30);
        pane.getChildren().addAll(tableView, paneHB, tableView_);

        HBox buttonsHB=  new HBox();
        buttonsHB.setSpacing(20);
        pane.getChildren().add(buttonsHB);

        TextField namefield = new TextField();
        Label nameLabel = new Label("FullName :" , namefield);
        nameLabel.setContentDisplay(ContentDisplay.TOP);

        DatePicker dateField = new DatePicker();
        Label dateLabel = new Label("Date of order :",dateField);
        dateLabel.setContentDisplay(ContentDisplay.TOP);

        HBox newhb = new HBox(nameLabel,dateLabel,next);
        newhb.setPadding(new Insets(0, 0, 0, 70));
        newhb.setSpacing(20);
        buttonsHB.getChildren().add(newhb);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);

        remove.setOnAction(event -> {
            Book bookInOrder = tableView_.getSelectionModel().getSelectedItem();
            tableView_.getItems().remove(bookInOrder);
            books.remove(bookInOrder);

            Book book = Book.getBookByTitle2(bookInOrder.getTitle());
            book.setQuantity(book.getQuantity()+bookInOrder.getQuantity());
            tableView.getItems().add(book);
        });

        anotherBook.setOnAction(event -> {
//        System.out.println(titles.getValue().toString()+" "+Integer.parseInt(quantityField.getText()));

            Book book=tableView.getSelectionModel().getSelectedItem();
            Book bookInOrder = new Book(book.getTitle(), Integer.parseInt(quantityField.getText()));
            bookInOrder.setAuthor(book.getAuthor());
            bookInOrder.setIsbn(book.getIsbn());
            bookInOrder.setCategory(book.getCategory());
            bookInOrder.setPurchasedPrice(book.getPurchasedPrice());
            bookInOrder.setSellingPrice(book.getSellingPrice());
            if(Integer.parseInt(quantityField.getText())<0){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Pleas enter a positive number for quantity");
                a.show();
            }
            else{
                Ordered_Book orderedBook= new Ordered_Book(bookInOrder.getTitle(),bookInOrder.getSellingPrice(),bookInOrder.getQuantity());


                if(bookInOrder.getQuantity()<=book.getQuantity() ){
                    orderedBooks.add(orderedBook);
                    booksOrderedCopy.add(orderedBook);

                    books.add(bookInOrder);

                    tableView_.getItems().add(bookInOrder);
                    tableView.getItems().remove(book);
                    book.setQuantity(book.getQuantity() - bookInOrder.getQuantity());

                    quantityField.setText("");
                }
                else{
                    Alert aa = new Alert(Alert.AlertType.ERROR);
                    aa.show();
                    stage.close();
                }

                lenght=tableView_.getItems().size();
            }


        });



        next.setOnAction(event -> {
            if(tableView_.getItems().size()!=0){
                int  l=tableView.getSelectionModel().getSelectedItems().size();
                int k=booksOrderedCopy.size();
                getOrders();
                BookOrder order = new BookOrder(namefield.getText(),booksOrderedCopy,dateField.getValue(),View.getCurrentUser(),lenght,k);
                orders.add(order);
                saveToFile();

                Book.saveToNewFile2();
                Book.saveToNewFile();

                tableView.getItems().removeAll(tableView.getItems());
                for(Book book : Book.getBooks2()){
                    tableView.getItems().add(book);

                }
                namefield.setText("");

                tableView_.getItems().removeAll(tableView_.getItems());
                stage.setScene(new Scene(printBill(order)));
                System.out.println(getOrders());
                orderedBooks.clear();
                stage.show();

            }



        });

        return pane;
    }

    public static void saveToFile(){ //
        try {
            ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            for(BookOrder order :getOrders()){
                object.writeObject(order);
            }
            object.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VBox  printAllBills(){ //
        System.out.println(getOrders());
        VBox ALLORDER= new VBox();
        ALLORDER.setAlignment(Pos.CENTER);
        ALLORDER.setSpacing(20);
        for(BookOrder order: getOrders()){
            ALLORDER.getChildren().add(printBill(order));
        }
        return ALLORDER;
    }


    public static VBox printBill(BookOrder bookOrder){

        int l= bookOrder.j- bookOrder.i;
        try {

            PrintWriter printWriter = new PrintWriter(  String.valueOf(LocalDateTime.now().toLocalTime().toSecondOfDay())+".txt");
            printWriter.println("NEW ORDER");
            printWriter.println("Name of customer:  "+bookOrder.getName());
            printWriter.println("Date :"+bookOrder.getDate());
            printWriter.println("Different types of books bought " + String.valueOf(bookOrder.i));
            for(int k= l;k< bookOrder.j;k++){
                printWriter.println(bookOrder.getBooksOrdered().get(k).toString());
            }
            int amount=0;
            for(int k=l;k< bookOrder.j;k++){
                amount+=bookOrder.getBooksOrdered().get(k).getTotamount();
            }

            printWriter.println("Total Amount to be paid "+amount);
            printWriter.println("Bill created by user:   "+bookOrder.getUser().getUsername());
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(l+ " " +" "+ bookOrder.i+" "+ bookOrder.j);
        Label intro = new Label("NEW BILL");
        intro.setFont(Font.font("ALGERIAN",24));
        intro.setTextFill(Color.DARKCYAN);
        Label nameLabelDisplay = new Label("Name "+bookOrder.getName());

        nameLabelDisplay.setContentDisplay(ContentDisplay.RIGHT);
        Label dateOfOrder = new Label("Date :"+bookOrder.getDate());

        Label totalNrOfBooks= new Label("Different types of books bought " + String.valueOf(bookOrder.i));
        VBox billdisplay = new VBox();
        billdisplay.setSpacing(20);
        billdisplay.getChildren().addAll(intro,nameLabelDisplay,dateOfOrder,totalNrOfBooks);


        for(int k= l;k< bookOrder.j;k++){
            HBox bookindex= new HBox();
            bookindex.setSpacing(20);
            bookindex.getChildren().addAll(new Label(bookOrder.getBooksOrdered().get(k).toString()));
            billdisplay.getChildren().add(bookindex);
        }
        int amount=0;
        for(int k=l;k< bookOrder.j;k++){
            amount+=bookOrder.getBooksOrdered().get(k).getTotamount();
        }
        System.out.println(amount);
        System.out.println("--------------------");

        billdisplay.getChildren().add(new HBox(new Label("Total Amount to be paid "+amount)));
        billdisplay.getChildren().add(new HBox(new Label("Bill created by "+bookOrder.getUser().getUsername())));
        return billdisplay;
    }
   /* public  void RefreshView(){
        tableView.getItems().removeAll(tableView.getItems());
        for(Book book:Book.getBooks2()){
            tableView.getItems().add(book);
        }
    }*/




    public static void piechartOfBooks() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//        pieChartData.get(1).getName()
//        pieChartData.get(1).getPieValue()

        for(BookOrder order: getOrders()){
            int totam= 0;
            for(Ordered_Book bookOrd: order.getBooksOrdered()){
                totam+=bookOrd.getTotamount();
            }
            boolean found=false;
            for (PieChart.Data d : pieChartData){
                if (d.getName().matches(order.getUser().getUsername())){
                    d.setPieValue(d.getPieValue()+totam);
                    found=true;
                    break;
                }
            }
            if (!found){
                pieChartData.add(new PieChart.Data(order.user.getUsername(),totam));
            }
            System.out.println(totam);
            System.out.println(" ------");
        }


        ObservableList<PieChart.Data> pieChartDataofBooks = FXCollections.observableArrayList();
        for(BookOrder order: orders){
            boolean found = false;
            for(Ordered_Book book : order.getBooksOrdered()){
                for(PieChart.Data d : pieChartDataofBooks){
                    if(d.getName().matches(book.getTitle())){
                        d.setPieValue(d.getPieValue()+book.getTotamount());
                        found=true;
                        break;
                    }
                }
                if(!found){
                    pieChartDataofBooks.add(new PieChart.Data(book.getTitle(), book.getTotamount()));
                }
            }
        }
        /*final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistics based on users");*/
        final PieChart chartBook = new PieChart(pieChartDataofBooks);
        chartBook.setTitle("Statistics Based on books");

        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        ((Group) scene.getRoot()).getChildren().addAll(chartBook);
        stage.setScene(scene);
        stage.show();
    }


    public static void piechartOfUsers() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(BookOrder order: getOrders()){
            int totam= 0;
            for(Ordered_Book bookOrd: order.getBooksOrdered()){
                totam+=bookOrd.getTotamount();
            }
            boolean found=false;
            for (PieChart.Data d : pieChartData){
                if (d.getName().matches(order.getUser().getUsername())){
                    d.setPieValue(d.getPieValue()+totam);
                    found=true;
                    break;
                }
            }
            if (!found){
                pieChartData.add(new PieChart.Data(order.user.getUsername(),totam));
            }

        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistics based on users");
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        ((Group) scene.getRoot()).getChildren().addAll(chart);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean findTitle(ArrayList<Ordered_Book> books,String booktitle){
        for(Ordered_Book book : books){
            if(book.getTitle().matches(booktitle)){
                return true;
            }
        }
        return false;
    }

    public static void billstats(){
        VBox vb = new VBox();
        vb.setSpacing(20);
        vb.setPrefSize(400,200);
        vb.setAlignment(Pos.TOP_LEFT);
        for(BookOrder order: getOrders()){
            vb.getChildren().add(new Label("\n   User: "+order.getUser().getUsername()+".   " +
                    "Total Amount: "+order.getBooksOrdered().get(0).getTotamount()));
        }
        Scene scene = new Scene(vb);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
