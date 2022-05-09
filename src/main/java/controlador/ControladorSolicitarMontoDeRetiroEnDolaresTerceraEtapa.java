/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validacion.ValidacionCuenta;
import vistaGUI.SolicitarMontoDeRetiroEnDolaresTerceraEtapa;
import serviciosExternos.TipoCambioBCCR;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapa implements ActionListener{
    public SolicitarMontoDeRetiroEnDolaresTerceraEtapa vistaGUI;
    private String numeroDeCuenta;
    
    public ControladorSolicitarMontoDeRetiroEnDolaresTerceraEtapa(SolicitarMontoDeRetiroEnDolaresTerceraEtapa pVistaGUI, String pNumeroDeCuenta) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI.btnRetirarDolares.addActionListener(this);
        this.vistaGUI.btnCancelarRetiroDolares.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Retirar")) {
            String montoDeRetiroEnDolares = this.vistaGUI.txtMontoRetirarEnDolares.getText();
            boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiroEnDolares);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(this.numeroDeCuenta);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                    if(cuentaEstaActiva) {
                        double montoDeRetiroEnDolaresEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoDeRetiroEnDolares);
                        TipoCambioBCCR tipoDeCambioDolar = new TipoCambioBCCR();
                        double tipoDeCambioDeDolarVenta = tipoDeCambioDolar.obtenerValorVenta();
                        double montoDeRetiroEnColones = montoDeRetiroEnDolaresEnFormatoDecimal * tipoDeCambioDeDolarVenta;
                        double montoComision = this.calcularMontoComision(montoDeRetiroEnColones);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSufientes(this.numeroDeCuenta, montoDeRetiroEnColones + montoComision);
                        if(hayFondosSuficientes) {
                            this.efectuarRetiro(montoDeRetiroEnColones, montoDeRetiroEnDolaresEnFormatoDecimal, tipoDeCambioDeDolarVenta);
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
    
    private void efectuarRetiro(double pMontoRetiradoColones, double pMontoRetiradoDolares, double pTipoCambio) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.retirar(pMontoRetiradoColones);
        double montoComision = this.calcularMontoComision(pMontoRetiradoColones);
        MensajeEnPantallaCuenta.imprimirMensajeRetiroEnDolaresExitoso(pMontoRetiradoColones, pMontoRetiradoDolares, pTipoCambio, montoComision);
    }
}
