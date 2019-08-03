package repos;

import db.DBManager;
import modals.Post;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LikesRepos {
    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }


    public  static void addLikedBy(Post post, User user){

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO liked_by (post_id, user_id) " +
                    "VALUES (?, ?)"
            );

            statement.setLong(1, post.getId());
            statement.setLong(2, user.getId());






            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Long> getLikedBy(Post post){
        ArrayList<Long> userList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM liked_by WHERE post_id =? ");
            statement.setLong(1, post.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("user_id");

                userList.add(id);

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return userList;

    }

    public static void removeLikedBy(Post post,User user){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM liked_by WHERE post_id=? AND user_id =?"

            );

            statement.setLong(1, post.getId());
            statement.setLong(2, user.getId());






            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
