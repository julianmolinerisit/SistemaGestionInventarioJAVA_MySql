package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {
    
    Connection con;
    String bd = "inventario";
    String url = "jdbc:mysql://127.0.0.1:3306/" + bd + "?useUnicode=true&characterEncoding=UTF-8";
    String user = "root";
    String pass = ""; // Si el usuario root tiene una contraseña, ponla aquí
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
