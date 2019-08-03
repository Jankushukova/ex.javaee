package repos;

import db.DBManager;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificationsRepos {

    private static Connection connection;

    static {
        connection = DBManager.getConnection();
    }


    public static void addNotification(User user, User friend){

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO notifications (ID,from_whom, to_whom) " +
                    "VALUES (null,?, ?)"
            );

            statement.setLong(1, user.getId());
            statement.setLong(2, friend.getId());






            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getNotifications(User user){

        ArrayList<User> friendList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM notifications WHERE to_whom =? ");
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("from_whom");
                User friend = null;
                for(User u: (new UserRepos()).getAllUsers()){
                    if(u.getId()==id){
                        friend = u;
                    }
                }
                friendList.add(friend);

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return friendList;

    }

    public static void removeNotification(User user,User friend){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM notifications WHERE to_whom=? AND from_whom = ?"

            );

            statement.setLong(1, user.getId());
            statement.setLong(2, friend.getId());






            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
