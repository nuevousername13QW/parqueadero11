
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author delga
 */
public class DatabaseConnection {
    public static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/parqueadero";
    private static final String USER = "root";
    private static final String PASSWORD = "Alejandro134456QW_";

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public Connection conectar() throws SQLException{

    con = null;

    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/parqueadero","root","Alejandro134456QW_");
    if(con!=null){
    }
    return con;
    }
}
