/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import vistaGUI.CambioDePinSegundaEtapa;
import vistaGUI.CambioDePinTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinSegundaEtapa implements ActionListener{
    private String numeroDeCuenta;
    public CambioDePinSegundaEtapa vistaGUI;
    
    public ControladorCambioDePinSegundaEtapa(String pNumeroDeCuenta, CambioDePinSegundaEtapa pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equalsIgnoreCase("Aceptar")) {
            IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
            String pin = this.vistaGUI.txtPinCuenta.getText();
            boolean pinCorrespondeACuenta = daoCuenta.verificarPinCorrespondeACuenta(this.numeroDeCuenta, pin);
            if(pinCorrespondeACuenta) {
                CambioDePinTerceraEtapa vistaTerceraEtapa = new CambioDePinTerceraEtapa();
                ControladorCambioDePinTerceraEtapa controladorTerceraEtapa = new ControladorCambioDePinTerceraEtapa(this.numeroDeCuenta, vistaTerceraEtapa);
                controladorTerceraEtapa.vistaGUI.setVisible(true);
                controladorTerceraEtapa.vistaGUI.setLocationRelativeTo(null);
                this.vistaGUI.setVisible(false);
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
            }
        }
    }
}
