package application.bookstore.views;

import application.bookstore.models.Ordered_Book;
import application.bookstore.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/*public class PieChartView {

    private static final String FILE_PATH = "data/orders.ser";

    private User user;
    private static ArrayList<BookOrder> orders = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
//        final PieChart chart = new PieChart(pieChartData);
//        chart.setTitle("Statistics based on users");
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
}*/
