package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    protected Connection connection;

    private String URL;
    private String USER;
    private String PASSWORD;

    Properties properties = new Properties();

    private DatabaseConnection() {

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Error finding the properties file");
            throw new RuntimeException("Error al cargar la configuración de la base de datos.");
        }
    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }




    public Connection getConnection()  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection= DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Error while attempting connection to the database");
        }

        return connection;
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }



}
