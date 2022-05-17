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
public class ConsultaDeEstatusDeCuentaCLI {
    public ConsultaDeEstatusDeCuentaCLI() throws Exception {
        recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(datosIngresadosSonValidos) {
                this.mostrarEstatusDeCuenta(numeroDeCuenta);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirNumeroDeCuenta();
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
                this.recibirNumeroDeCuenta();
            }
        }  
    }
    
    private boolean validarNumeroDeCuenta(String pNumeroDeCuentaDeDestino) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuentaDeDestino);
        if(existeCuenta) {
            return true;
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuentaDeDestino));
            return false;
        }
    }
    
    private void mostrarEstatusDeCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        String estatusCuentaActual = daoCuenta.consultarEstatusCuenta(pNumeroDeCuenta);
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeEstatusDeCuenta(pNumeroDeCuenta, estatusCuentaActual));
    }
}
