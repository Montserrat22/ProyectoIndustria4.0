package jsf.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectionController {

    Connection cn = null;
    String pass = "root";
    String user = "postgres";

    public ConectionController() {

    }

    public void conectarse() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("conectarse");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/industria", user, pass);
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet validateUser(String username, String password) {
        conectarse();
        ResultSet resultSet = null;
        Statement p = null;
        try {

            String query = "select * from usuario where usuario = '" + username + "' and contraseña = '" + password + "';";
            
            p = cn.createStatement();
            resultSet = p.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Tiene");
                return resultSet;
            } else {
                System.out.println("No tiene");
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

}
