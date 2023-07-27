package Controlador;

import Modelo.Cliente;
import java.sql.*;
import Modelo.*;
import static Modelo.Conexion.getConnection;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pc
 */
public class CtrlCliente implements ActionListener {

    private Cliente mod;
    private ConsultasCliente modC;
    private frmCliente frm;

    public CtrlCliente(Cliente mod, ConsultasCliente modC, frmCliente frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnListar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Clientes");
        frm.setLocationRelativeTo(null);
    }
    
    
    //LISTAR CLIENTE CONTROLADOR
    public void Listar() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ConsultasCliente.ListarTabla("select * from CLIENTE");
        md.setColumnIdentifiers(new Object[]{"id", "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email"});

        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("id"), rs.getString("Cedula"), rs.getString("Nombres"), rs.getString("Apellidos"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Email")});
                frm.tblCliente.setModel(md);
            }

        } catch (Exception e) {
            System.out.println(e);

        }

    }
    
    //MODIFICAR CLIENTE CONTROLADOR
    
    public void Modificar(){
        int fila = frm.tblCliente.getSelectedRow();

        Cliente cl = new Cliente();

        cl.setIdCliente(Integer.parseInt(frm.tblCliente.getValueAt(fila, 0).toString()));
        cl.setCedula(frm.tblCliente.getValueAt(fila, 1).toString());
        cl.setNombres(frm.tblCliente.getValueAt(fila, 2).toString());
        cl.setApellidos(frm.tblCliente.getValueAt(fila, 3).toString());
        cl.setTelefono(frm.tblCliente.getValueAt(fila, 4).toString());
        cl.setDireccion(frm.tblCliente.getValueAt(fila, 5).toString());
        cl.setEmail(frm.tblCliente.getValueAt(fila, 6).toString());

        if (modC.modificar(cl)) {
            JOptionPane.showMessageDialog(null, "Cliente Modificado");
        }
    }
    
    
    //ELIMINAR CLIENTE CONTROLADOR
    public void Eliminar(){
        int fila =frm.tblCliente.getSelectedRowCount();
        //si no selecciona ningun registro
        if(fila<1){
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro de la tabla ","AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{//caso contrario eliminar registro
            if(modC.Eliminar(frm.tblCliente.getValueAt(frm.tblCliente.getSelectedRow(), 0).toString())){
            JOptionPane.showMessageDialog(null, "Registro Eliminado!"); 
            }
        
        }
    
    }

    
    
//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnGuardar) {
            mod.setIdCliente(Integer.parseInt(frm.txtIdCliente.getText()));
            mod.setCedula(frm.txtCedula.getText());
            mod.setNombres(frm.txtNombres.getText());
            mod.setApellidos(frm.txtApellidos.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setDireccion(frm.txtDireccion.getText());
            mod.setEmail(frm.txtEmail.getText());

            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnModificar) {
            Modificar();
        }
        
        if (e.getSource() == frm.btnEliminar) {
            Eliminar();
        }

        
         if (e.getSource() == frm.btnListar) {
            Listar();
        }
        
    }

    public void limpiar() {
        frm.txtIdCliente.setText(null);
        frm.txtCedula.setText(null);
        frm.txtNombres.setText(null);
        frm.txtApellidos.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtDireccion.setText(null);
        frm.txtEmail.setText(null);
    }
}
