package Modelo;

import Conexion.ConexionMysql;
import Vista.RegistroProducto;
import com.mysql.jdbc.Connection;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class Registro {

    ConexionMysql con = new ConexionMysql();
    Connection cn = con.conectar();

    public void registrarBD(Producto p) {
        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO Productos(nombre,cantidad,precio,total)VALUES(?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidad());
            ps.setDouble(3, p.getPrecio());
            ps.setDouble(4, p.Total());
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR DATOS" + e);
        }
    }

 public void editarBD(Producto p, int id) {
    try {
        String consulta = "UPDATE Productos SET nombre=?, cantidad=?, precio=?, total=? WHERE id=?";
        PreparedStatement ps = cn.prepareStatement(consulta);
        ps.setString(1, p.getNombre());
        ps.setInt(2, p.getCantidad());
        ps.setDouble(3, p.getPrecio());
        ps.setDouble(4, p.Total());
        ps.setInt(5, id); 
        ps.executeUpdate();
         JOptionPane.showMessageDialog(null, "EXITO AL EDITAR DATOS");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR AL EDITAR DATOS" + e);
    }
}

 public void eliminarBD (int id){
     try{
         String consulta = "DELETE FROM Productos WHERE  id=" + id + ""; 
         PreparedStatement ps = cn.prepareStatement(consulta);
         ps.execute();
         
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR" + e);
     }
 }
 
}