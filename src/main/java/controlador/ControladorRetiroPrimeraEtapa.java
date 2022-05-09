/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.RetiroPrimeraEtapa;
import validacion.ValidacionCuenta;
import vistaGUI.VerificacionMensajeDeTexto;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorRetiroPrimeraEtapa implements ActionListener{
    public RetiroPrimeraEtapa vistaGUI;
    
    public ControladorRetiroPrimeraEtapa(RetiroPrimeraEtapa pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnContinuar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Continuar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaRetiro.getText();
            String pin = this.vistaGUI.txtPinRetiro.getText();
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            if(existeCuenta) {
                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    VerificacionMensajeDeTexto vistaVerificacionMensajeDeTexto = new VerificacionMensajeDeTexto();
                    ControladorVerificacionMensajeDeTexto controladorVerificacionMensajeDeTexto = new ControladorVerificacionMensajeDeTexto(vistaVerificacionMensajeDeTexto, numeroDeCuenta, "Retiro");
                    controladorVerificacionMensajeDeTexto.vistaGUI.setVisible(true);
                    controladorVerificacionMensajeDeTexto.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
                    MensajeEnPantallaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje();
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
        }
    }
}
