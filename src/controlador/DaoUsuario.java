package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.conexion;
import modelo.usuarios;

public class DaoUsuario {

    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;

    public usuarios login(String usuario, String password) {
        usuarios us = new usuarios();
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = AES_ENCRYPT(?, 'clave')";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setIdusuario(rs.getInt("idUsuario"));
                us.setNombre(rs.getString("nombre"));
                us.setApellido(rs.getString("apellido"));
                us.setDocumento(rs.getString("documento"));
                us.setDireccion(rs.getString("direccion"));
                us.setTelefono(rs.getString("telefono"));
                us.setCorreo(rs.getString("correo"));
                us.setTipoUsuario(rs.getString("tipoUsuario"));
                us.setUsaurio(rs.getString("usuario"));
                us.setPassword(rs.getString("password"));
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return us;
    }
}
