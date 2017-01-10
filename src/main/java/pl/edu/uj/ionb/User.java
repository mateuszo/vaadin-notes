package pl.edu.uj.ionb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mostafil on 08.01.2017.
 */
public class User {
    private int id;
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public boolean login(){
        if(User.allUsers().contains(this)){
            return true;
        } else{
            return false;
        }
    }

    public static List<User> allUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("Jan","Kowalski"));
        users.add(new User("Adam","Nowak"));
        return users;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof User){
            User user = (User) o;
            if(this.login.equals(user.login) && this.password.equals(user.password)){
                return true;
            }
        }
        return false;
    }
}
