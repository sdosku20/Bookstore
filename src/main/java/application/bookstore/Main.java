package application.bookstore;

import application.bookstore.controllers.LoginController;
import application.bookstore.models.Author;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import application.bookstore.views.LoginView;
import application.bookstore.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import static application.bookstore.models.Author.getAuthors;

public class Main extends Application {
    static Stage primaryStage;

    public static void main(String[] args) {
//        seedData();
        launch(args);
    }

    private static void seedData() {
        User admin = new User("admin", "admin", Role.ADMIN);
        User manager = new User("manager", "manager", Role.MANAGER);
        User librarian = new User("librarian", "librarian", Role.LIBRARIAN);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(User.FILE_PATH));
            outputStream.writeObject(admin);
            outputStream.writeObject(manager);
            outputStream.writeObject(librarian);
            System.out.println("Wrote users to the file users.dat successfully");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Author.FILE_PATH))) {
            outputStream.writeObject(new Author("Dritero", "Agolli"));
            outputStream.writeObject(new Author("Faik", "Konica"));
            outputStream.writeObject(new Author("Naim", "Frasheri"));
            System.out.println("Wrote authors to the file users.dat successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    LOGOUT BUTTON
    public static void logout(Stage stage) {
//        Main.primaryStage.close();
        LoginView loginView = new LoginView(stage);
        LoginController controller = new LoginController(loginView, new MainView(), stage);
        Scene scene = new Scene(loginView.getView(), 320, 240);
        stage.setTitle("Bookstore");
        stage.setScene(scene);
        stage.show();
        Main.primaryStage = stage;

    }

    public static void quit() {
        Main.primaryStage.close();
    }

    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);
        LoginController controller = new LoginController(loginView, new MainView(), primaryStage);
        Scene scene = new Scene(loginView.getView(), 320, 240);
        primaryStage.setTitle("Bookstore");
        primaryStage.setScene(scene);
        primaryStage.show();
        Main.primaryStage = primaryStage;

//        System.out.println(Arrays.toString(new ArrayList[]{getAuthors()}));
    }
}
