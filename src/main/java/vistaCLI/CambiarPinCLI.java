/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class CambiarPinCLI {
    public CambiarPinCLI() {
        recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean numeroDeCuentaEsValido = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(numeroDeCuentaEsValido) {
                this.recibirPinActual(numeroDeCuenta);
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
    
    private void recibirPinActual(String pNumeroDeCuenta) {
        try {
            System.out.println("Ingrese el pin actual de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean pinIngresadoEsCorrecto = this.validarPinCorrespondeACuenta(pNumeroDeCuenta, pin);
            if(pinIngresadoEsCorrecto) {
                this.recibirNuevoPin(pNumeroDeCuenta);
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
        }
    }
    
    private boolean validarPinCorrespondeACuenta(String pNumeroDeCuenta, String pPin) {
        boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(pNumeroDeCuenta, pPin);
        if(pinCorrespondeACuenta) {
            return true;
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(pNumeroDeCuenta, pPin));
            return false;
        }
    }
    
    private void recibirNuevoPin(String pNumeroDeCuenta) {
        try {
            System.out.println("Ingrese el nuevo pin de su cuenta");
            String nuevoPin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean formatoDePinEsCorrecto = this.validarFormatoDePin(nuevoPin);
            if(formatoDePinEsCorrecto) {
                IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
                daoCuenta.cambiarPin(pNumeroDeCuenta, nuevoPin);
                MensajeEnConsolaCuenta.imprimirMensajeCambioDePinExitoso(pNumeroDeCuenta);
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
        }
    }
    
    private boolean validarFormatoDePin(String pPin) {
        boolean formatoDePinEsCorrecto = ValidacionCuenta.validarFormatoDePin(pPin);
        if(formatoDePinEsCorrecto) {
            return true;
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorFormatoDePinInvalido());
            return false;
        }
    }
}