package skyblock.database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class SQLite {

    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        connection = null;

        try {
            File file = new File("skyblock.db");

            if (!file.exists()) {
                file.createNewFile();
            }

            String url = "jdbc:sqlite:" +  file.getPath();
            connection = DriverManager.getConnection(url);

            System.out.println("Connected to SQLite database");

            statement = connection.createStatement();


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected from SQLite database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void onUpdate(String sql) throws SQLException {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ResultSet onQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
