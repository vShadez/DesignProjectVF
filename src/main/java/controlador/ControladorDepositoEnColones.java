/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import validacion.ValidacionCuenta;
import vistaGUI.DepositoEnColones;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDepositoEnColones implements ActionListener{
    public DepositoEnColones vistaGUI;
    
    public ControladorDepositoEnColones(DepositoEnColones pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.jButton1.addActionListener(this);
        this.vistaGUI.jButton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaDeposito.getText();
            String montoDeDeposito = this.vistaGUI.txtMontoDeposito.getText();
            boolean formatoDeMontoDeDepositoEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeDeposito);
            if(formatoDeMontoDeDepositoEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                    if(cuentaEstaActiva) {
                        this.efectuarDeposito(numeroDeCuenta, montoDeDeposito);
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            }
        }
    }
    
    public void efectuarDeposito(String numeroDeCuenta, String montoDeDeposito) {
        int montoDeDepositoEnFormatoEntero = Conversion.convertirStringEnEntero(montoDeDeposito);
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.depositar(montoDeDepositoEnFormatoEntero);
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += montoDeDepositoEnFormatoEntero * 0.02;
        }
        MensajeEnPantallaCuenta.imprimirMensajeDepositoEnColonesExitoso(numeroDeCuenta, montoDeDepositoEnFormatoEntero, montoComision);
    }
}
