package Modelo;

import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Grupo E
 */
public class ConsultasCliente extends Conexion {
    
    //METODO REGISTRAR CLIENTE
    public boolean registrar(Cliente c) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO CLIENTE (id, Cedula , Nombres, Apellidos, Telefono, Direccion, Email) "
                + "VALUES(?,?,?,?,?,?,?)";//Insertando datos en la tabla CLIENTE

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getCedula());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getApellidos());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getDireccion());
            ps.setString(7, c.getEmail());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
    
    //METODO MODIFICAR CLIENTE
     public boolean modificar(Cliente c) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE CLIENTE SET Cedula = ?, Nombres=?, Apellidos=?, Telefono=?, Direccion=?, Email=? WHERE Id= ?";
   
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getEmail());
            ps.setInt(7, c.getIdCliente());

            //Envia la sentencia de Actualizar
            ps.executeUpdate();
            con.close();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
       
    
    //METODO LISTAR CLIENTES
    public static ResultSet ListarTabla(String consulta){
        Statement sql;
        ResultSet rs=null;
        Connection con = getConnection();
        try {
            sql=con.createStatement();
            rs=sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e);
           
        }
        return rs;
    }
    
    //METODO ELIMINAR CLIENTE
    public static boolean Eliminar(String id) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "delete from CLIENTE where Id="+id;

        try {
            ps = con.prepareStatement(sql);
            
            
            ps.execute();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            return false;
       
        }
    }
}
