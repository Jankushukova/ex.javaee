package repos;

import db.DBManager;
import javafx.geometry.Pos;
import modals.Post;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FriendRepos {
    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }


    public static void addFriends(User user, User friend){

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO friends (user_id, friend_id) " +
                    "VALUES (?, ?)"
            );

            statement.setLong(1, user.getId());
            statement.setLong(2, friend.getId());






            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }






    public static ArrayList<User> getFriends(User user){

        ArrayList<User> friendList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friends WHERE user_id =? ");
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("friend_id");
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

    public  static boolean neFriend(User user,User friend){
        System.out.println(friend);
        boolean nefriend = true;
        if(friend==null){
            return false;
        }
        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friends WHERE user_id =? ");
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
//            System.out.println(friend.getId()+1000000000);

            while(resultSet.next()){
                Long id = resultSet.getLong("friend_id");
//                System.out.println(id);
                if((friend.getId()==user.getId()) || (id==friend.getId())){
                   return false;
                }






            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return nefriend;

    }
    public static ArrayList<Post> getPostsofAllFriends(User user){

        ArrayList<Post> all_friends_posts = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT posts.ID FROM posts INNER JOIN friends ON posts.posted_by=friends.friend_id WHERE friends.user_id =?");
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("friend_id");


            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return all_friends_posts;

    }



}
