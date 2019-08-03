package repos;

import db.DBManager;
import modals.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LastAccessTimeRepos {
    private static Connection connection;
    static {
        connection = DBManager.getConnection();
    }
    public static boolean isOnline(User user){
        boolean isonline = false;
//        System.out.println("in is online");
        try{


            PreparedStatement statement = connection.prepareStatement("SELECT * FROM lastaccesstime WHERE user_id = ?");
            statement.setLong(1, user.getId());

            ResultSet resultSet = statement.executeQuery();
            Long min = new Long(1000000000);
//            System.out.println(System.currentTimeMillis());
//            System.out.println(user.getFullName());

            while(resultSet.next()){
                Long time = resultSet.getLong("last_access_time_in_millis");
//                System.out.println(time);
                if(  ((System.currentTimeMillis()-time)/1000) < min){
                    min = (System.currentTimeMillis()-time)/1000;
//                    System.out.println("min is "  + min);
                }
            }
            if(min < 300){
                isonline = true;
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return isonline;

    }
    public static void addAccess(User user){
//        System.out.println("in add access");
//        User u = (new UserRepos()).findbyName(user.getFullName());

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO lastaccesstime (ID, user_id, last_access_date, last_access_time_in_millis) " +
                    "VALUES (NULL, ?, ?, ?)"
            );

            statement.setLong(1, user.getId());
            String dateInString =new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            statement.setString(2, dateInString);
            statement.setLong(3, System.currentTimeMillis());



            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void setLastAccess(User user){
//        System.out.println("in set last access");


        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE lastaccesstime SET last_access_date=?,last_access_time_in_millis=? WHERE user_id = ? "

            );
            String dateInString =new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            statement.setString(1, dateInString);
            statement.setLong(2, System.currentTimeMillis());

            statement.setLong(3, user.getId());



            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
