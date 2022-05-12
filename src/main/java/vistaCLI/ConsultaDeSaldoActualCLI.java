/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import controlador.MensajeEnPantallaCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeSaldoActualCLI {
    public ConsultaDeSaldoActualCLI() {
        recibirDatos();
    }
    
    private void recibirDatos() {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el pin de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarDatos(numeroDeCuenta, pin);
            if(datosIngresadosSonValidos) {
                this.mostrarSaldoDeCuenta(numeroDeCuenta);
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
    
    private boolean validarDatos(String pNumeroDeCuenta, String pPin) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        if(existeCuenta) {
            boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(pNumeroDeCuenta, pPin);
            if(pinCorrespondeACuenta) {
                return true;
            }
            else {
                System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorFormatoDePinInvalido());
                return false;
            }
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void mostrarSaldoDeCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual cuentaAconsultarColones = new DAOCuentaIndividual();
        double saldoActualColones = cuentaAconsultarColones.consultarSaldoActual(pNumeroDeCuenta);
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeSaldoCuentaActualColones(""+saldoActualColones));
    }
}
