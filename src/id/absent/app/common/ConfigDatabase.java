package id.absent.app.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigDatabase {

    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String username = "root";
    private final String password = "a";
    private final String dbName = "Absent";

    public Connection getConnection() throws Exception {
        String urlConnection = "jdbc:mysql://"+this.serverName+":"+this.portNumber+"/"+dbName;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(urlConnection,this.username,this.password);
    }
}
