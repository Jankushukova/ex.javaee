package db;

import modals.User;

import java.util.ArrayList;

public class Dbu {
    private static ArrayList<User> users = new ArrayList<>();
    public Dbu() {
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public static void addUsers(User user) {
        users.add(user);
    }
    public static User findUsers(String username,String password) {
        for(User u:users){
            if( (!username.equals(null))&& (!password.equals(null)) && (username.equals(u.getUsername()) && password.equals(u.getPassword())) ){
                return u;
            }
        }
        return null;
    }

    public static User findbyUsername(String id){
        for(User user:users){
            if(user.getFullName().equals(id)){
                return user;
            }
        }
        return null;
    }


}
