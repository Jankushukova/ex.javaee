package repos;

import db.DBManager;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserRepos {
    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }
    public static void addUser(User user){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (ID, Full_Name, UserName, user_password,email_address,phone_number,address,birthday) " +
                    "VALUES (NULL, ?, ?, ?,?,?,?,?)"
            );

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmailaddress());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getDate());


            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public   static ArrayList<User> getAllUsers(){

        ArrayList<User> userList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("ID");
                String fullname = resultSet.getString("Full_Name");
                String username = resultSet.getString("UserName");
                String password = resultSet.getString("user_password");
                String email = resultSet.getString("email_address");
                String phone = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String birthday = resultSet.getString("birthday");

                userList.add(new User(id, fullname,username,password,email,phone,address,birthday));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return userList;

    }

//    public boolean containsUser(User user){
//
//        boolean contains = false;
//
//        try{
//
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE ID = ?");
//            statement.setLong(1, user.getId());
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while(resultSet.next()){
//                Long id = resultSet.getLong("ID");
//                if(id!=null){
//                    contains = true;
//                }
//
//
//            }
//
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return  false;
//        }
//
//        return contains;
//
//    }

    public static User findbyId(Long id){
        User user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE ID = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long idd = resultSet.getLong("ID");
                String fullname = resultSet.getString("Full_Name");
                String username = resultSet.getString("UserName");
                String password = resultSet.getString("user_password");
                String email = resultSet.getString("email_address");
                String phone = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String birthday = resultSet.getString("birthday");
                user = new User(idd,fullname,username,password,email,phone,address,birthday);

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }

    public static User findbyName(String name){
        User user = null;
        for(User u: getAllUsers()){
            if(u.getFullName().equals(name)){
                user = u;
                break;
            }
        }

        return user;

    }


}
