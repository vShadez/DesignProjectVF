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
import vistaGUI.DepositoConTipoDeCambio;
import serviciosExternos.TipoCambioBCCR;
import java.time.LocalDate;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDepositoConTipoDeCambio implements ActionListener{
    public DepositoConTipoDeCambio vistaGUI;
    
    public ControladorDepositoConTipoDeCambio(DepositoConTipoDeCambio pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnAceptar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Aceptar")) {
            String numeroDeCuenta = this.vistaGUI.txtMontoDolares.getText();
            String montoDeDepositoEnDolares = this.vistaGUI.txtNumeroCuentaDepositarDolares.getText();
            boolean formatoDeMontoDeDepositoEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeDepositoEnDolares);
            if(formatoDeMontoDeDepositoEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                    if(cuentaEstaActiva) {
                        this.efectuarDeposito(numeroDeCuenta, montoDeDepositoEnDolares);
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
    
    public void efectuarDeposito(String pNumeroDeCuenta, String pMontoDeDepositoEnDolares) {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeDolarCompra = tipoDeCambioDeDolar.obtenerValorCompra();
        double montoDeDepositoEnColonesEnFormatoDecimal = (Conversion.convertirStringEnDecimal(pMontoDeDepositoEnDolares)) * tipoDeCambioDeDolarCompra;
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuenta);
        cuenta.depositar(montoDeDepositoEnColonesEnFormatoDecimal);
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += montoDeDepositoEnColonesEnFormatoDecimal * 0.02;
        }
        MensajeEnPantallaCuenta.imprimirMensajeDepositoEnDolaresExitoso(pNumeroDeCuenta, Conversion.convertirStringEnDecimal(pMontoDeDepositoEnDolares), montoDeDepositoEnColonesEnFormatoDecimal, tipoDeCambioDeDolarCompra, montoComision, LocalDate.now());
    }
}
