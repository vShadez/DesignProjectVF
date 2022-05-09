/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.event.ActionEvent;
import vistaGUI.ConsultaEstadoDeCuenta;

import java.awt.event.ActionListener;
import validacion.ValidacionCuenta;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaColones;
import vistaGUI.SeleccionDeConsultaEstadoDeCuenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaEstadoDeCuenta implements ActionListener{
    public ConsultaEstadoDeCuenta vistaGUI;
    
    
    public ControladorConsultaEstadoDeCuenta(ConsultaEstadoDeCuenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnConsultarEstadoCuenta.addActionListener(this);
        this.vistaGUI.btnCancelarEstadoCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaEstadoCuenta.getText();
            String pin = this.vistaGUI.txtPinEstadoCuenta.getText();
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            if(existeCuenta) {
               boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    SeleccionDeConsultaEstadoDeCuenta vistaSeccionConsultaEstadoCuenta = new SeleccionDeConsultaEstadoDeCuenta();
                    ControladorSeccionDeConsultaEstadoDeCuenta controladorSeccionConsultaEstadoCuenta = new ControladorSeccionDeConsultaEstadoDeCuenta(vistaSeccionConsultaEstadoCuenta, numeroDeCuenta);
                    controladorSeccionConsultaEstadoCuenta.vistaGUI.setVisible(true);
                    controladorSeccionConsultaEstadoCuenta.vistaGUI.setLocationRelativeTo(null);
                    vistaGUI.setVisible(false);
                }
            }
        }
    }

}
