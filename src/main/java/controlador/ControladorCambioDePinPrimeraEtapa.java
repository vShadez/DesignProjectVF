/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cuenta;
import vistaGUI.CambioDePinPrimeraEtapa;
import vistaGUI.CambioDePinSegundaEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinPrimeraEtapa implements ActionListener{
    public CambioDePinPrimeraEtapa vistaGUI;
    
    public ControladorCambioDePinPrimeraEtapa(CambioDePinPrimeraEtapa pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equalsIgnoreCase("Aceptar")) {
            IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuenta.getText();
            Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
            if(cuenta != null) {
                CambioDePinSegundaEtapa vistaSegundaEtapa = new CambioDePinSegundaEtapa();
                ControladorCambioDePinSegundaEtapa controladorSegundaEtapa = new ControladorCambioDePinSegundaEtapa(cuenta.numeroCuenta, vistaSegundaEtapa);
                controladorSegundaEtapa.vistaGUI.setVisible(true);
                controladorSegundaEtapa.vistaGUI.setLocationRelativeTo(null);
                this.vistaGUI.setVisible(false);
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
        }
    }
}
