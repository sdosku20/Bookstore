package application.bookstore.models;

import java.io.*;
import java.util.ArrayList;

public class User extends BaseModel implements Serializable {
    private static final ArrayList<User> users = new ArrayList<>();
    private String username;
    private String password;
    private Role role;
    public static final String FILE_PATH = "data/users.ser";
    private static final File DATA_FILE = new File(FILE_PATH);
    @Serial
    private static final long serialVersionUID = 1234567L;
    public User() {}

    public User(String username, String password, Role role) {
        this(username, password);
        this.role = role;
    }

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username=" + getUsername() +
                ", password=" + getPassword() +
                ", role=" + getRole() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return other.getUsername().equals(getUsername()) && other.getPassword().equals(getPassword());
        }
        return false;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static User getIfExists(User potentialUser) {
        for (User user : getUsers()){
            if (user.equals(potentialUser))
                return user;
        }
        return null;
    }
    public static String validateUsername(User potentialUser) {
        for (User user : getUsers()){
            if (user.getUsername().equals(potentialUser.getUsername()))
                return user.getUsername();
        }
        return null;
    }
    public static ArrayList<User> getUsers () {
        if (users.size() == 0){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    User temp = (User) inputStream.readObject();
                    if (temp == null)
                        break;
                    users.add(temp);
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of file reached!");
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
    public static void SaveInFile(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            for(int i=0;i<users.size();i++){
                output.writeObject(users.get(i));
            }
            output.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean saveInFile() {
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            for(int i=0;i<users.size();i++){
                output.writeObject(users.get(i));
            }
            output.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean saved = super.save(User.DATA_FILE);
        return saved;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean deleteFromFile() {
        return false;
    }
}
