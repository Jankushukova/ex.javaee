package repos;

import db.DBManager;
import javafx.geometry.Pos;
import modals.Comment;
import modals.Post;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentsRepos {
    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }


    public  static void addComment(Comment comment){

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (ID,post_id, posted_by,description) " +
                    "VALUES (NULL,?, ?, ?)"
            );

            statement.setLong(1, comment.getpost().getId());
            statement.setLong(2, comment.getOwner().getId());
            statement.setString(3, comment.getDescription());

            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static void removeComment(Long comment){

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE comments SET active=? WHERE ID = ?");

            statement.setBoolean(1, false);
            statement.setLong(2, comment);

            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void changeComment(Comment comment,String text){

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE comments SET description=? WHERE ID = ?");

            statement.setString(1, text);
            statement.setLong(2, comment.getId());

            int rows = statement.executeUpdate();

            statement.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static ArrayList<Comment> getAllComments(Post post){

        ArrayList<Comment> CommentsList = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE post_id = ? AND active = ?");
            statement.setLong(1, post.getId());
            statement.setBoolean(2, true);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("ID");
                Long post_id = resultSet.getLong("post_id");
                Long posted_by = resultSet.getLong("posted_by");
                String description = resultSet.getString("description");

                UserRepos usrep = new UserRepos();
                User user = usrep.findbyId(posted_by);
                CommentsList.add(new Comment(id, post,user,description ));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return CommentsList;

    }

    public static Comment findbyId(Long id){
        Comment comment = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comments WHERE ID=? AND active = ?");
            statement.setLong(1, id);
            statement.setBoolean(2, true);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long idd = resultSet.getLong("ID");
                Long post_id = resultSet.getLong("post_id");
                Long posted_by = resultSet.getLong("posted_by");
                String description = resultSet.getString("description");

                comment = new Comment(idd, PostRepos.findbyId(post_id), UserRepos.findbyId(posted_by),description);

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return comment;
    }



}
