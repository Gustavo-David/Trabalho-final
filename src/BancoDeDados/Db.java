package BancoDeDados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
    private static Connection conn = null;

    public static Connection getConnection() throws Exception{
        if(conn == null){
            try{
                Properties properties = loadProperties(); 
                String url = properties.getProperty("dburl");
                DriverManager.getConnection(url, properties);
            }catch(SQLException e){
                throw new Exception(e.getMessage());
            }
        }
        return conn;
    }

    public static Properties loadProperties() throws Exception {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fs);
            return properties;

        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }


    public static void closeConnection() throws Exception{
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                throw new Exception(e.getMessage());
            }
        }

    }

}