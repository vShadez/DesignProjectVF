/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.Operacion;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeEstadoDeCuentaCLI {
    public ConsultaDeEstadoDeCuentaCLI() {
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
                this.mostrarEstadoDeCuenta(numeroDeCuenta);
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
    
    private void mostrarEstadoDeCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaConsultada = (Cuenta) cuenta.consultarCuenta(pNumeroDeCuenta);
        System.out.println("Información de la cuenta: ");
        System.out.println("Número de cuenta: " + cuentaConsultada.numeroCuenta);
        System.out.println("Saldo: " + cuentaConsultada.getSaldo());
        System.out.println("\nInformación del cliente asociado a la cuenta: ");
        Cliente clientePropietario = (Cliente) cuentaConsultada.propietario;
        this.mostrarDetallesDeClienteAsociado(clientePropietario);
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(pNumeroDeCuenta);
        System.out.println("\nInformación de las operaciones realizadas por la cuenta: \n");
        this.mostrarDetallesDeOperacionesRealizadas(operaciones);
        
    }
    
    private void mostrarDetallesDeClienteAsociado(Cliente pClienteAsociado) {
        System.out.println("Nombre completo: " + pClienteAsociado.nombre + pClienteAsociado.primerApellido + pClienteAsociado.segundoApellido);
        System.out.println("Correo electrónico: " + pClienteAsociado.correoElectronico);
        System.out.println("Número de teléfono: " + pClienteAsociado.numeroTelefono);
    }
    
    private void mostrarDetallesDeOperacionesRealizadas(Lista<Operacion> pOperaciones) {
        Nodo puntero = pOperaciones.inicio;
        while(puntero != null) {
            Operacion operacion = (Operacion)puntero.objeto;
            System.out.println("Tipo de operación: " + operacion.tipoOperacion);
            System.out.println("Fecha: " + operacion.fechaOperacion.toString());
            String aplicaComision;
            if (operacion.seAplicoComision == true){
                aplicaComision = "Sí";
            }else{
                aplicaComision = "No";
            }
            System.out.println("¿Se aplicó comisión? " + aplicaComision);
            System.out.println("Monto de comisión: " + operacion.montoComision);
            puntero = puntero.siguiente;
        }
    }
}
