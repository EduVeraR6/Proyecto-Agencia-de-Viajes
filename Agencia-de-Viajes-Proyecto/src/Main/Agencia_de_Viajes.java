
package Main;

import Controlador.*;
import Modelo.Cliente;
import Modelo.ConsultasCliente;
import Vista.frmCliente;

/**
 *
 * @author Pc
 */
public class Agencia_de_Viajes {

    public static void main(String[] args) {
        Cliente mod = new Cliente();
        ConsultasCliente modC =new ConsultasCliente();
        frmCliente frm=new frmCliente();
        
        CtrlCliente ctrlc = new CtrlCliente(mod,modC,frm);
        ctrlc.iniciar();
        frm.setVisible(true);
    }
}
