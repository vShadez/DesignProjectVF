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
import vistaGUI.SolicitarMontoDepositoYCuentaDestinoDeTransferencia;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia implements ActionListener{
    public SolicitarMontoDepositoYCuentaDestinoDeTransferencia vistaGUI;
    private String numeroDeCuentaDeOrigen;
    
    public ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia(SolicitarMontoDepositoYCuentaDestinoDeTransferencia pVistaGUI, String pNumeroDeCuentaDeOrigen) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuentaDeOrigen = pNumeroDeCuentaDeOrigen;
        this.vistaGUI.btnEnviarDineroTransferencia.addActionListener(this);
        this.vistaGUI.btnCancelarTransferencia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Enviar")) {
            String montoDeRetiro = this.vistaGUI.txtMontoAEnviarTransferencia.getText();
            String numeroDeCuentaDeDestino = this.vistaGUI.txtNumeroCuentaDeTransferencia.getText();
            boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiro);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuentaDeDestino);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuentaDeDestino);
                    if(cuentaEstaActiva) {
                        double montoDeRetiroEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoDeRetiro);
                        double montoComision = this.calcularMontoComision(montoDeRetiroEnFormatoDecimal);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSufientes(this.numeroDeCuentaDeOrigen, montoDeRetiroEnFormatoDecimal + montoComision);
                        if(hayFondosSuficientes) {
                            
                        }
                        else {
                            MensajeEnPantallaCuenta.imprimirMensajeDeErrorFondosInsuficientes();
                        }
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuentaDeDestino);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            }
        }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(this.numeroDeCuentaDeOrigen);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    public void efectuarTransferencia(String pNumeroDeCuentaDestino, double pMontoTransferido) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuentaDeOrigen = (Cuenta) daoCuenta.consultarCuenta(this.numeroDeCuentaDeOrigen);
        Cuenta cuentaDeDestino = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuentaDestino);
        cuentaDeOrigen.transferir(cuentaDeDestino, pMontoTransferido);
        double montoComision = this.calcularMontoComision(pMontoTransferido);
        MensajeEnPantallaCuenta.imprimirMensajeTransferenciaExitosa(pMontoTransferido, montoComision);
    }
}
