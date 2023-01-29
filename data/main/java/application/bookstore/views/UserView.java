package application.bookstore.views;

import application.bookstore.controllers.AuthorController;

import application.bookstore.models.Author;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserView extends View{
    private final BorderPane borderPane = new BorderPane();
    private final TableView<User> tableView = new TableView<>();
    private final HBox formPane = new HBox();
    private final TextField UserName = new TextField();
    private final ComboBox<Role> role = new ComboBox<Role>();
    private final TextField Password = new TextField();
    private final Button saveBtn = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Label resultLabel = new Label("");

    public ComboBox<Role> getRole() {
        return role;
    }


    public Label getResultLabel() {
        return resultLabel;
    }

    public BorderPane getBorderPane() {
        tableView.setItems(FXCollections.observableArrayList(User.getUsers()));
        TableColumn<User, String> usernameCol = new TableColumn<>("UserName");
        usernameCol.setCellValueFactory(
                new PropertyValueFactory<>("username")
        );
        TableColumn<User, String> pascolomn = new TableColumn<>("Password");
        pascolomn.setCellValueFactory(
                new PropertyValueFactory<>("Password")
        );
        TableColumn<User, Role> RoleColumn = new TableColumn<>("Role");
        RoleColumn.setCellValueFactory(
                new PropertyValueFactory<>("Role")
        );
        tableView.getColumns().addAll(usernameCol, pascolomn,RoleColumn);
        borderPane.setCenter(tableView);
        formPane.setPadding(new Insets(20));
        formPane.setSpacing(20);
        formPane.setAlignment(Pos.CENTER);
        Label username = new Label("Username: ", UserName);
        username.setContentDisplay(ContentDisplay.TOP);
        Label password = new Label("Password : ", Password);
        role.getItems().addAll(Role.MANAGER,Role.LIBRARIAN,Role.ADMIN);
        password.setContentDisplay(ContentDisplay.TOP);
        formPane.getChildren().addAll(username, password, role,saveBtn,delete);
        borderPane.setCenter(tableView);
        borderPane.setBottom(formPane);
        return borderPane;
    }

    public TableView<User> getTableView() {
        return tableView;
    }

    public TextField getFirstNameField() {
        return UserName;
    }

    public TextField getLastNameField() {
        return Password;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }



    Stage stage = new Stage();

    public  void deleteUser(){



    }
    public  void addUser(){
        delete.setOnAction(event -> {
            User user = getTableView().getSelectionModel().getSelectedItem();
            getTableView().getItems().remove(user);
            User.getUsers().remove(user);
            User.SaveInFile();

        });

        saveBtn.setOnAction(e -> {
            System.out.println("Save btn");
            String username1 = getFirstNameField().getText();
            String password1 = getLastNameField().getText();
            String role = getRole().getAccessibleText();
            Role userRole=getRole().getValue();
            User user = new User(username1, password1,userRole);
            String regeex= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

            /*if(User.validateUsername(user)==null && user.getRole()!=null){
                User.getUsers().add(user);
                User.SaveInFile();
                tableView.getItems().add(user);
                UserName.clear();
                Password.clear();
            }
            else if(user.getRole()==null){
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please Enter a role for user");
                a.show();
            }
            else{
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("User Already Exists");
                a.show();
            }
        });*/
            if(User.validateUsername(user)!=null){
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("User Already Exists");
                a.show();
            }
            if(User.validateUsername(user)==null && user.getRole()!=null && password1!="" && username1!="" && password1.matches(regeex)){
                User.getUsers().add(user);
                User.SaveInFile();
                tableView.getItems().add(user);
                UserName.clear();
                Password.clear();
            }
            if(user.getRole()==null){
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please Enter a role for user");
                a.show();
            }
            if(!password1.matches(regeex)){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please enter a vallid password that contains :\n" +
                        "At least 8 chars\n" +
                        "\n" +
                        "Contains at least one digit\n" +
                        "\n" +
                        "Contains at least one lower alpha char and one upper alpha char\n" +
                        "\n" +
                        "Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                        "\n" +
                        "Does not contain space, tab, etc.");
                a.show();
            }

            if(password1==""){
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please Enter a password for user");
                a.show();
            }
            if(username1==""){
                Alert a= new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please Enter a Username for user");
                a.show();
            }

        });


        Scene scene = new Scene(getBorderPane());
        stage.setScene(scene);
        stage.show();
    }

    public void PrintUsers(){
        Stage stage1 = new Stage();
        VBox boxx= new VBox();
        boxx.setSpacing(25);
        for(User user : User.getUsers()){
            boxx.getChildren().add(new HBox(new Label(user.getUsername() + " "+ user.getPassword()+ " "+user.getRole())));
        }
        Scene scene = new Scene(boxx);
        stage1.setScene(scene);
        stage1.show();
    }


    public static void ChangePassword(){
        PasswordField oldpassField= new PasswordField();
        PasswordField newPassField = new PasswordField();
        PasswordField confirmPassField= new PasswordField();
        Label oldpassLabel = new Label("Old Password",oldpassField);
        oldpassLabel.setContentDisplay(ContentDisplay.RIGHT);
        Label newPassLabel = new Label("New Password",newPassField);
        newPassLabel.setContentDisplay(ContentDisplay.RIGHT);
        Label confirmPassLabel = new Label("Confirm new Password",confirmPassField);
        confirmPassLabel.setContentDisplay(ContentDisplay.RIGHT);
        Button saveChanges = new Button("Save Changes");
        VBox vb = new VBox();
        vb.setSpacing(25);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(oldpassLabel,newPassLabel,confirmPassLabel,saveChanges);
        Scene scene = new Scene(vb);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        saveChanges.setOnAction(event1 -> {
            String oldpass= oldpassField.getText();
            String newpass= newPassField.getText();
            String confirmPass = confirmPassField.getText();
            if(oldpass.equals(UserView.getCurrentUser().getPassword())){
                if(newpass.equals(confirmPass)){
                    UserView.getCurrentUser().setPassword(newpass);
                    User.SaveInFile();
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Password Changed");
                    a.show();
                    stage.close();
                }
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Password can not be changed");
                a.show();
                stage.close();
            }
        });
    }

    @Override
    public Parent getView() {
        return null;
    }
}
