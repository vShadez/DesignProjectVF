/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCuenta;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import serviciosExternos.TipoCambioBCCR;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class DepositoEnDolares {
    private void recibirDatos() {
        try {
            System.out.println("Ingrese el número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el monto del depósito en dólares");
            String montoDeDeposito = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarNumeroDeCuenta(numeroDeCuenta) && this.validarMontoDeDeposito(numeroDeCuenta, montoDeDeposito);
            if(datosIngresadosSonValidos) {
                TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
                double tipoDeCambioDeCompra = tipoDeCambio.obtenerValorCompra();
                this.efectuarDeposito(numeroDeCuenta, montoDeDeposito, tipoDeCambioDeCompra);
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
        }
    }
    
    private boolean validarNumeroDeCuenta(String pNumeroDeCuenta) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        if(existeCuenta) {
            return true;
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private boolean validarMontoDeDeposito(String pNumeroDeCuenta, String pMontoDeDeposito) {
        boolean montoDeDepositoEsValido = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pMontoDeDeposito);
        if(montoDeDepositoEsValido) {
            TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
            double tipoDeCambioDeCompra = tipoDeCambio.obtenerValorCompra();
            double montoDeDeposito = Conversion.convertirStringEnDecimal(pMontoDeDeposito) * tipoDeCambioDeCompra;
            boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(pNumeroDeCuenta, montoDeDeposito);
            if(hayFondosSuficientes) {
                return true;
            }
            else {
                System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorFondosInsuficientes());
                return false;
            }
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroODepositoIncorrecto());
            return false;
        }
    }
    
    private void efectuarDeposito(String numeroDeCuenta, String montoDeDeposito, double pTipoDeCambio) {
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
        MensajeEnConsolaCuenta.imprimirMensajeDepositoEnDolaresExitoso(numeroDeCuenta, (montoDeDepositoEnFormatoEntero / pTipoDeCambio), montoDeDepositoEnFormatoEntero, pTipoDeCambio, montoComision, LocalDate.now());
    }
}
