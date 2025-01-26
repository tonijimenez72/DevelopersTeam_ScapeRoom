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

    private final String URL;
    private final String USER;
    private final String PASSWORD;



    private DatabaseConnection() {

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {

            properties.load(fis);
            this.URL = properties.getProperty("db.url");
            this.USER = properties.getProperty("db.user");
            this.PASSWORD = properties.getProperty("db.password");
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

            connection= DriverManager.getConnection(this.URL,this.USER,this.PASSWORD);


        } catch (ClassNotFoundException | SQLException e) {

            if (connection ==null) {

                System.out.println("La conexión es nula");
            }

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
