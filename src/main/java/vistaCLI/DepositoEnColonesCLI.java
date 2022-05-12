/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class DepositoEnColonesCLI {
    public DepositoEnColonesCLI() {
        recibirDatos();
    }
    
    private void recibirDatos() {
        try {
            System.out.println("Ingrese el número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el monto del depósito");
            String montoDeDeposito = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarNumeroDeCuenta(numeroDeCuenta) && this.validarMontoDeDeposito(numeroDeCuenta, montoDeDeposito);
            if(datosIngresadosSonValidos) {
                this.efectuarDeposito(numeroDeCuenta, montoDeDeposito);
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirDatos();
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
            boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
            if(usuarioDeseaVolverAMenuPrincipal) {
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
            else {
                this.recibirDatos();
            }
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
            double montoDeDeposito = Conversion.convertirStringEnDecimal(pMontoDeDeposito);
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
    
    private void efectuarDeposito(String numeroDeCuenta, String montoDeDeposito) {
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
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDepositoEnColonesExitoso(numeroDeCuenta, montoDeDepositoEnFormatoEntero, montoComision));
    }
}
