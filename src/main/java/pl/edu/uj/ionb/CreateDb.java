package pl.edu.uj.ionb;

import java.sql.*;

/**
 * Created by mostafil on 13.01.2017.
 */
public class CreateDb {
    public static void main(String args[]){
        try (Connection conn
                     = DriverManager.getConnection("jdbc:derby:myderby;create=true")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
