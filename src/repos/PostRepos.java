package repos;

import db.DBManager;
import modals.Post;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PostRepos {

    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }

    public static void addPost(Post post){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO posts (ID, posted_by, title, text,data) " +
                    "VALUES (NULL, ?, ?, ?,?)"
            );

            statement.setLong(1, post.getPostedBy().getId());
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getText());
            statement.setString(4, post.getDate());
            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removePost(Post post){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE posts SET active = ? WHERE ID = ?"
            );
            statement.setBoolean(1, false);

            statement.setLong(2, post.getId());

            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updatePost(Post post,String text){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE posts SET text = ? WHERE ID = ?"
            );
            statement.setString(1, text);

            statement.setLong(2, post.getId());

            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Post> getAllPosts(User user){

        ArrayList<Post> postList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE posted_by =? AND active = ? ");
            statement.setLong(1, user.getId());
            statement.setBoolean(2, true);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("ID");
                Long posted_by = resultSet.getLong("posted_by");
                String title  = resultSet.getString("title");
                String text = resultSet.getString("text");
                String date = resultSet.getString("data");





                postList.add(new Post(id, UserRepos.findbyId(posted_by),title,text,date));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return postList;

    }
    public static   User getUserofPost(Post post){
        User user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE ID=? ");
            statement.setLong(1, post.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long posted_by = resultSet.getLong("posted_by");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");

                String date = resultSet.getString("data");
                for(User u:(new UserRepos()).getAllUsers()){
                    if(u.getId()==posted_by){
                        user = u;
                    }
                }
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;

    }
    public static Post findbyId(Long id){

        Post post = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM posts WHERE ID = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long ID = resultSet.getLong("ID");
                Long posted_by = resultSet.getLong("posted_by");
                String title  = resultSet.getString("title");
                String text = resultSet.getString("text");
                String date = resultSet.getString("data");
                post = new Post(ID,UserRepos.findbyId(posted_by), title,text,date);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return post;
    }

}
