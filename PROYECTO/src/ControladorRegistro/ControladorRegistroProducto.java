package ControladorRegistro;

import Modelo.ListaDeProductos;
import Modelo.Producto;
import Modelo.Registro;
import Vista.RegistroProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class ControladorRegistroProducto implements ActionListener, ListSelectionListener {

    RegistroProducto FormularioRP;

    public ControladorRegistroProducto(RegistroProducto FormularioRP) {
        this.FormularioRP = FormularioRP;
        this.FormularioRP.BotonGuardar.addActionListener(this);
        this.FormularioRP.TablaProductos.getSelectionModel().addListSelectionListener(this);
        this.FormularioRP.BotonEditar.addActionListener(this);
        this.FormularioRP.BotonCancelar.addActionListener(this);
        this.FormularioRP.BotonEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == FormularioRP.BotonGuardar) {
            String nombre = FormularioRP.txtNombre.getText();
            int cantidad = Integer.parseInt(FormularioRP.txtCantidad.getText());
            Double precio = Double.parseDouble(FormularioRP.txtPrecio.getText());
            Producto P = new Producto(nombre, cantidad, precio);
            Registro R = new Registro();
            R.registrarBD(P);
            ListaDeProductos LP = new ListaDeProductos();
            LP.MostrarTabla(FormularioRP.TablaProductos);
            limpiarentradas();

        }
        if (e.getSource() == FormularioRP.BotonEditar) {
            int id = Integer.parseInt(FormularioRP.txtID.getText());
            String nombre = FormularioRP.txtNombre.getText();
            int cantidad = Integer.parseInt(FormularioRP.txtCantidad.getText());
            Double precio = Double.parseDouble(FormularioRP.txtPrecio.getText());
            Producto P = new Producto(nombre, cantidad, precio);
            Registro R = new Registro();
            R.editarBD(P, id);
            ListaDeProductos LP = new ListaDeProductos();
            LP.MostrarTabla(FormularioRP.TablaProductos);
            limpiarentradas();

        }

        if (e.getSource() == FormularioRP.BotonCancelar) {

            FormularioRP.BotonEditar.setEnabled(false);
            FormularioRP.BotonCancelar.setEnabled(false);
            FormularioRP.BotonGuardar.setEnabled(true);
            limpiarentradas();
        }

        if (e.getSource() == FormularioRP.BotonEliminar) {
            int FilaObtenida = FormularioRP.TablaProductos.getSelectedRow();
            TableModel modelo = FormularioRP.TablaProductos.getModel();
            Object ID = modelo.getValueAt(FilaObtenida, 0);

            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto?", "Eliminar producto", JOptionPane.YES_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                Registro R = new Registro();
                R.eliminarBD((int) ID);
                
                ListaDeProductos LP = new ListaDeProductos();
                LP.MostrarTabla(FormularioRP.TablaProductos);
                limpiarentradas();
                
                JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO");

            } else {
                System.out.println("no selecciono una opcion");
            }

        }
    }

    private void limpiarentradas() {
        FormularioRP.txtID.setText("");
        FormularioRP.txtNombre.setText("");
        FormularioRP.txtCantidad.setText("");
        FormularioRP.txtPrecio.setText("");
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            if (e.getSource() == FormularioRP.TablaProductos.getSelectionModel()) {
                int FilaObtenida = FormularioRP.TablaProductos.getSelectedRow();

                if (FilaObtenida >= 0) {

                    TableModel modelo = FormularioRP.TablaProductos.getModel();

                    Object ID = modelo.getValueAt(FilaObtenida, 0);
                    Object Nombre = modelo.getValueAt(FilaObtenida, 1);
                    Object Cantidad = modelo.getValueAt(FilaObtenida, 2);
                    Object Precio = modelo.getValueAt(FilaObtenida, 3);

                    FormularioRP.txtID.setText(ID.toString());
                    FormularioRP.txtNombre.setText(Nombre.toString());
                    FormularioRP.txtCantidad.setText(Cantidad.toString());
                    FormularioRP.txtPrecio.setText(Precio.toString());

                    FormularioRP.BotonGuardar.setEnabled(false);
                    FormularioRP.BotonEditar.setEnabled(true);
                    FormularioRP.BotonCancelar.setEnabled(true);

                }
               

            }

        }
    }

}
