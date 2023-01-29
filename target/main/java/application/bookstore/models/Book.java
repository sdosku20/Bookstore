package application.bookstore.models;

import java.io.*;
import java.util.ArrayList;

public class Book extends BaseModel implements Serializable{

    private String isbn;
    private String title;
    private float purchasedPrice;
    private float sellingPrice;
    private Category category;
    private Author author;
    private int quantity;
    private static final String FILE_PATH = "data/books.ser";
    private static final String FILE_PATH2 = "data/books2.ser";
    private static final File DATA_FILE = new File(FILE_PATH);
    private static final File DATA_FILE2 = new File(FILE_PATH2);

    @Serial
    private static final long serialVersionUID = 1234567L;
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final ArrayList<Book> books2 = new ArrayList<>();

//    public Book(){}

    public Book (String title,int quantity){
        this.title=title;
        this.quantity=quantity;
    }

    public Book(String isbn, String title, float purchasedPrice, float sellingPrice, Author author, Category category, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.category = category;
        this.author = author;
        this.quantity = quantity;
    }

    public Book(String isbn, String title, float purchasedPrice, float sellingPrice, Author author, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.author = author;
        this.quantity=quantity;
    }

    public static ArrayList<Book> getBooks2(){
        if (books2.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH2));
                while (true) {
                    Book temp = (Book) inputStream.readObject();
                    if (temp != null)
                        books2.add(temp);
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
        return books2;
    }
    public Category getCategory() {
        return category;
    }

    public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean saveInFile() {
        boolean saved = super.save(DATA_FILE);
        if (saved)
            books.add(this);
        return saved;
    }
    public boolean saveInFile2() {
        boolean saved = super.save(DATA_FILE2);
        if (saved)
            books2.add(this);
        return saved;
    }
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean deleteFromFile() {
        return false;
    }

    public static ArrayList<Book> getBooks() {
        return getBooks2();
//        if (books.size() == 0) {
//            try {
//                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
//                while (true) {
//                    Book temp = (Book) inputStream.readObject();
//                    if (temp != null)
//                        books.add(temp);
//                    else
//                        break;
//                }
//                inputStream.close();
//            } catch (EOFException eofException) {
//                System.out.println("End of book file reached!");
//            }
//            catch (IOException | ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return books;
    }

    public static  void saveToNewFile(){

        saveToNewFile2();
    }
    public static  void saveToNewFile2(){

        try {
            ObjectOutputStream object = new ObjectOutputStream(new FileOutputStream(FILE_PATH2));
            for(int i=0;i<getBooks2().size();i++){
                object.writeObject(getBooks2().get(i));
            }
            object.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", purchasedPrice=" + purchasedPrice +
                ", sellingPrice=" + sellingPrice +
                ", author=" + author +
                ", quantity=" + quantity +
                '}';
    }
    public static Book getBookByTitle2(String title){
        for(Book book: getBooks2()){
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }
    public static Book getBookByTitle1(String title){
        for(Book book: books){
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }
    public static int getBookByISBN(String isbn){

        for(int i=0 ;i<books2.size();i++){
            System.out.println(books2.get(i).getIsbn().equals(isbn));
            if(books2.get(i).getIsbn().equals(isbn)){
                return i;
            }
        }
        return -1;
    }
}
