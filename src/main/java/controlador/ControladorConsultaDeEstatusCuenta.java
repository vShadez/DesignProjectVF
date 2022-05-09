
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import vistaGUI.ConsultaDeEstatusCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
/**
 *
 * @author estadm
 */
public class ControladorConsultaDeEstatusCuenta implements ActionListener{
    public ConsultaDeEstatusCuenta  vistaGUI;
    
    public ControladorConsultaDeEstatusCuenta(ConsultaDeEstatusCuenta pVistaGUI){
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarEstatusCuenta.addActionListener(this);
        this.vistaGUI.btnConsultarEstatusCuenta.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
        String numeroDecuenta = this.vistaGUI.txtNumeroCuentaConsultarEstatus.getText();
        
        IDAOCuentaIndividual estadoCuenta = new DAOCuentaIndividual();
        String estatusCuentaActual = estadoCuenta.consultarEstatusCuenta(numeroDecuenta);
        
        if(validarCuentaExiste(numeroDecuenta)){
            MensajeEnPantallaCuenta.imprimirMensajeEstatusDeCuenta(numeroDecuenta, estatusCuentaActual);
        }
        }
    }
    
    public boolean validarCuentaExiste(String pNumeroCuenta){
        IDAOCatalogoDeCuentas cuenta = new DAOCatalogoDeCuentas();
        if(cuenta.consultarSiExisteCuenta(pNumeroCuenta)){
            return true;
        }else{
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroCuenta);
            return false;
        }
    }
}
