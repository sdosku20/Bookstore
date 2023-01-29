package application.bookstore.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class UserStatistics extends BaseModel implements Serializable {
    private int amount;
    private User user;
    private static ArrayList<UserStatistics> listOFUsers = new ArrayList<>();
    private static final String FILE_PATH = "data/userStats.ser";
    @Serial
    private static final long serialVersionUID = 1234567L;

    public UserStatistics(int amount, User user) {
        this.amount = amount;
        this.user = user;
    }


    public static ArrayList<UserStatistics> getListOFUsers() {
        if (listOFUsers.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    UserStatistics temp = (UserStatistics) inputStream.readObject();
                    if (temp != null)
                        listOFUsers.add(temp);
                    else
                        break;
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of book file reached!");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return listOFUsers;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static int returnUserIndex(User user) {
        System.out.println(user.getUsername());
        for (int i = 0; i < getListOFUsers().size(); i++) {
            System.out.println(getListOFUsers().get(i).getUser().getUsername().equals(user.getUsername()));
            if (getListOFUsers().get(i).getUser().getUsername().equals(user.getUsername())) {
                return i;
            }
        }
        return -1;
    }
    public static void prova(User user) {
        for (int i = 0; i < listOFUsers.size(); i++) {
            System.out.println(listOFUsers.get(i).getUser().getUsername().equals(user.getUsername()));
            if (listOFUsers.get(i).getUser().getUsername().equals(user.getUsername())) {

            }
        }

    }

    @Override
    public boolean saveInFile() {
        return false;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean deleteFromFile() {
        return false;
    }

    public static void saveToNewFile() {

        try {
            ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            for (UserStatistics user : listOFUsers) {
                object.writeObject(user);
            }
            object.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






/*
    public static void piechart() {


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        for(UserStatistics userstat: UserStatistics.getListOFUsers()){
            pieChartData.add(new PieChart.Data(userstat.getUser().getUsername(),userstat.amount));
        }


        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Statistics");
            Scene scene = new Scene(new Group());
            Stage stage = new Stage();
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }*/
}

