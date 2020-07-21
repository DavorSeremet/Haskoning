package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements IConnectionFactory {

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        Connection connection = null;
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("connectionString"), properties.getProperty("user"), properties.getProperty("password"));
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Could not connect to database");
        }

        return connection;
    }
}
