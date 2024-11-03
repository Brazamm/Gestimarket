package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class ConexionMysql {
    private Connection cn;

    // Método para establecer la conexión
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/PROYECTO", "root", "");
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
        return cn;
    }
}


 


   

    
    


