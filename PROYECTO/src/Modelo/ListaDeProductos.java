
package Modelo;
import Conexion.ConexionMysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaDeProductos {
    ConexionMysql con = new ConexionMysql ();
    Connection cn = con.conectar();
    
    public void MostrarTabla(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO");
        modelo.addColumn("TOTAL");
        String consultasql = "SELECT*FROM Productos";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultasql);
            while (rs.next() ) {
                Object  [ ] lista ={rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5)  };
                modelo.addRow(lista);
                
            }
            tabla.setModel(modelo);
           
        } catch (Exception e) {
            System.out.println("Error al listar los datos"+e);
            
        }
 
        
    }
}
