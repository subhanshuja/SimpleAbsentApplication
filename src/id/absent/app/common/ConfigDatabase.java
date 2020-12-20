package id.absent.app.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigDatabase {

    /* change with your setting*/
    private final String username = "root";
    private final String password = "";

    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String dbName = "absent";

    public Connection getConnection() throws Exception {
        String urlConnection = "jdbc:mariadb://"+this.serverName+":"+this.portNumber+"/"+dbName;
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(urlConnection, this.username,this.password);
    }
}
