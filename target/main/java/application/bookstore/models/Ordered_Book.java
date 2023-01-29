package application.bookstore.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import java.io.Serializable;

public class Ordered_Book implements Serializable {
    private String title;
    private float price;
    private int quantity;
    private float totalamount;

    public Ordered_Book(String title, float price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        totalamount= this.price * this.quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotamount() {
        return totalamount;
    }

    @Override
    public String toString() {
        return "Title :" + this.title + "    Price :  " + this.price + " Nr of copies : " + this.quantity + "  Total Amount for this book   " + this.totalamount;
    }
}

