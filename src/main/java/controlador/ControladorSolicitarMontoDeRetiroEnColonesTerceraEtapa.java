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
import vistaGUI.SolicitarMontoDeRetiroEnColonesTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa implements ActionListener{
    public SolicitarMontoDeRetiroEnColonesTerceraEtapa vistaGUI;
    private String numeroDeCuenta;
    
    public ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa(SolicitarMontoDeRetiroEnColonesTerceraEtapa pVistaGUI, String pNumeroDeCuenta) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI.btnRetirar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Retirar")) {
            String montoDeRetiro = this.vistaGUI.txtMontoRetiroColones.getText();
            boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiro);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(this.numeroDeCuenta);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                    if(cuentaEstaActiva) {
                        double montoDeRetiroEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoDeRetiro);
                        double montoComision = this.calcularMontoComision(montoDeRetiroEnFormatoDecimal);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSufientes(this.numeroDeCuenta, montoDeRetiroEnFormatoDecimal + montoComision);
                        if(hayFondosSuficientes) {
                            this.efectuarRetiro(numeroDeCuenta, montoDeRetiroEnFormatoDecimal);
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
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
            }
        }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    private void efectuarRetiro(String numeroDeCuenta, double montoDeRetiro) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.retirar(montoDeRetiro);
        double montoComision = this.calcularMontoComision(montoDeRetiro);
        MensajeEnPantallaCuenta.imprimirMensajeRetiroEnColonesExitoso(montoDeRetiro, montoComision);
    }
}
