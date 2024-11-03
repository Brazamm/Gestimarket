
package Main;

import ControladorRegistro.ControladorRegistroProducto;
import Modelo.ListaDeProductos;
import Vista.RegistroProducto;


public class Principal {

 public static RegistroProducto FormularioRP;
 public static ControladorRegistro.ControladorRegistroProducto ControladorFormularioRP;
 public static ListaDeProductos LP;
    public static void main(String[] args) {
      
        
        FormularioRP = new RegistroProducto ();
        FormularioRP.setVisible(true);
        FormularioRP.setLocationRelativeTo(null);
        
        
        ControladorFormularioRP = new ControladorRegistroProducto(FormularioRP);
        LP = new ListaDeProductos();
        LP.MostrarTabla(FormularioRP.TablaProductos);
        
        
        
        FormularioRP.BotonEditar.setEnabled(false);
        FormularioRP.BotonCancelar.setEnabled(false);
    }
}
