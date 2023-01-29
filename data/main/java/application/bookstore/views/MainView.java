package application.bookstore.views;

import application.bookstore.Main;
import application.bookstore.controllers.AuthorController;
import application.bookstore.controllers.BookController;
import application.bookstore.models.Role;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView extends View {

    @Override
    public Parent getView() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab adminTab = new Tab("Admin");
        adminTab.setContent(getAdminMenu());
        Tab librarianTab = new Tab("Librarian");
        librarianTab.setContent(getLibrarianMenu());
        Tab managerTab = new Tab("Manager");
        managerTab.setContent(getManagerMenu());
        Role currentRole = (getCurrentUser() != null ? getCurrentUser().getRole() : null);

        if (currentRole != null) {
            if (currentRole == Role.ADMIN)
                tabPane.getTabs().addAll(adminTab);
            if (currentRole == Role.MANAGER || currentRole == Role.ADMIN)
                tabPane.getTabs().add(managerTab);

            tabPane.getTabs().add(librarianTab);
        }
        Button logout = new Button("Logout");
        Text text1 = new Text(getCurrentUser().getUsername() + ", welcome to our bookstore");
        Text spacing1 = new Text("           ");
        Text spacing2 = new Text("           ");
        borderPane.setTop(tabPane);
        borderPane.setBottom(new FlowPane(spacing1, text1, spacing2, logout));

        logout.setOnAction(event -> {
            Main.quit();
            Main.logout(new Stage());
        });

        return borderPane;
    }


    public VBox getLibrarianMenu() {
        VBox userButtons = new VBox();
        userButtons.setPadding(new Insets(10, 100, 10, 100));
        userButtons.setSpacing(10);
        userButtons.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        userButtons.setAlignment(Pos.CENTER);

        Button writeBill = new Button("Create a bill");
        Button changePass = new Button("Change Password");
        Button viewAllBooks = new Button("View All Books");
        Button printAuthors = new Button("Print all authors");

        userButtons.getChildren().addAll(writeBill, viewAllBooks, printAuthors, changePass);
        VBox.setMargin(writeBill, new Insets(20,0,0,0));
        VBox.setMargin(changePass, new Insets(0,0,20,0));

        viewAllBooks.setOnAction(event -> {
            BookView b = new BookView(false);
            b.getTableView().setEditable(false);
            b.getView();
        });

        writeBill.setOnAction(event -> {
//            BookOrder b1 = new BookOrder();
//            BookOrder.newOrder();
            Pane pane = new Pane();
            pane.getChildren().addAll(BookOrder.newOrder());
            pane.setPrefSize(1000, 750);

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        printAuthors.setOnAction(event -> {
            new AuthorView();
            AuthorController.displayAuthor();
        });

        changePass.setOnAction(event -> {
            UserView.ChangePassword();

        });
        return userButtons;
    }

    public VBox getManagerMenu() {

        VBox managerButtons = new VBox();
        managerButtons.setPadding(new Insets(10, 100, 10, 100));
        managerButtons.setSpacing(10);
        managerButtons.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        managerButtons.setAlignment(Pos.CENTER);

        Button addBook = new Button("Add a book");
        addBook.setOnAction(event -> {
            new BookView(true).getView();
        });
        
        Button changePass2 = new Button("Change Password");
        changePass2.setOnAction(event -> {
            UserView.ChangePassword();
        });

//        Button billStats = new Button("Bill stats");
//        billStats.setOnAction(event -> {
//            BookOrder.billstats();
//        });

        Button pieChartUser = new Button("Pie Chart");
        pieChartUser.setOnAction(event -> {
            BookOrder.piechartOfUsers();                        //  ------------------ PIE CHART ---------------------
        });



//        Button viewTotBill = new Button("View Total Bills sold by librarian");
//        viewTotBill.setOnAction(event -> {
////            BookOrder.printOrders();
//        });

        Button deleteAbook = new Button("Delete an existing book");
        deleteAbook.setOnAction(event -> {
            BookController.deleteBook();
        });

        Button addAnewAuthor = new Button("Add a new author");
        addAnewAuthor.setOnAction(event -> {
            new AuthorView().getView();
        });
        addBook.setOnAction(event -> {
            new BookView(true).getView();
        });

        managerButtons.getChildren().addAll(addBook, addAnewAuthor, deleteAbook, changePass2, pieChartUser);
        VBox.setMargin(pieChartUser, new Insets(0,0,12,0));
        VBox.setMargin(addBook, new Insets(12,0,0,0));


        return  managerButtons;
    }

    public VBox getAdminMenu() {

//        Menu manager = new Menu("Admin Menu");
        VBox adminButtons = new VBox();
        adminButtons.setPadding(new Insets(10, 100, 10, 100));
        adminButtons.setSpacing(10);
        adminButtons.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        adminButtons.setAlignment(Pos.CENTER);
//        adminButtons.getStyleClass().add("bluebox");
        Button ManageUser = new Button("Manage a user");
        ManageUser.setOnAction(event -> {
            new UserView().addUser();
        });

        Button displayUser = new Button("Display User Connected");
        Button showAllWorkers = new Button("Show all employees");

        displayUser.setOnAction(event -> {
            Pane pane = new Pane();
            Label label = new Label(" Username   " + getCurrentUser().getUsername() + "\n Password   " + getCurrentUser().getPassword() +
                    "\n Role   " + getCurrentUser().getRole());
            System.out.println(getCurrentUser().getUsername() + " " + getCurrentUser().getPassword());
            pane.getChildren().add(label);
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });

        Button billStats = new Button("Bill stats");
        billStats.setOnAction(event -> {
            BookOrder.billstats();
        });

        showAllWorkers.setOnAction(event -> {
            new UserView().PrintUsers();
        });

        adminButtons.getChildren().addAll(ManageUser, displayUser, showAllWorkers, billStats);

        return adminButtons;
    }
}
