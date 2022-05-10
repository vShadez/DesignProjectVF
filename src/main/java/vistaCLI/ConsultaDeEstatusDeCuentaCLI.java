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
    public ConsultaDeEstatusDeCuentaCLI() {
        recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(datosIngresadosSonValidos) {
                
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
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
