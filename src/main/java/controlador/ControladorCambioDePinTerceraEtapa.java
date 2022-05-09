/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import validacion.ExpresionRegular;
import vistaGUI.CambioDePinTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorCambioDePinTerceraEtapa implements ActionListener{
    private String numeroDeCuenta;
    public CambioDePinTerceraEtapa vistaGUI;
    
    public ControladorCambioDePinTerceraEtapa(String pNumeroDeCuenta, CambioDePinTerceraEtapa pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equalsIgnoreCase("Aceptar")) {
            String nuevoPin = this.vistaGUI.txtPinNuevo.getText();
            if(this.validarFormatoDePinCorrecto(nuevoPin)) {
                IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
                daoCuenta.cambiarPin(this.numeroDeCuenta, nuevoPin);
                MensajeEnPantallaCuenta.imprimirMensajeCambioDePinExitoso(this.numeroDeCuenta);
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, nuevoPin);
            }
        }
    }
    
    public boolean validarFormatoDePinCorrecto(String pPin) {
        boolean contieneSeisCaracteres = ExpresionRegular.verificarContieneSeisCaracteresSinEspacios(pPin);
        boolean contieneLetraMayuscula = ExpresionRegular.verificarContieneLetraMayuscula(pPin);
        boolean contieneNumero = ExpresionRegular.verificarContieneNumero(pPin);
        boolean contieneCaracterEspecial = ExpresionRegular.verificarContieneCaracterEspecial(pPin);
        if(contieneSeisCaracteres && contieneLetraMayuscula && contieneNumero && contieneCaracterEspecial) {
            return true;
        }
        else {
            return false;
        }
    }
}
