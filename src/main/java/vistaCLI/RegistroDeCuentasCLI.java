/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCuenta;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import validacion.ValidacionCliente;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class RegistroDeCuentasCLI {
    public RegistroDeCuentasCLI() {
        solicitarDatos();
    }
    
    private void solicitarDatos() {
        try {
            System.out.println("Ingrese el pin de la cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el monto del depósito inicial de la cuenta");
            String depositoInicial = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su identificacion");
            String identificacionCliente = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonCorrectos = validarDatos(pin, depositoInicial, identificacionCliente);
            if(datosIngresadosSonCorrectos) {
                try {
                    double montoInicialConvetidoDouble = Conversion.convertirStringEnDecimal(depositoInicial);
                    IDAOCliente DAOCliente = new DAOCliente();
                    ICliente clienteAsociadoConCuenta = DAOCliente.consultarCliente(Conversion.convertirStringEnEntero(identificacionCliente));
                    Cliente cliente = (Cliente) clienteAsociadoConCuenta;
                    String nombreCliente = cliente.nombre;
                    String primerApellido = cliente.primerApellido;
                    String segundoApellido = cliente.segundoApellido;
                    int telefonoCliente = cliente.numeroTelefono;
                    String correoElectronicoCliente = cliente.correoElectronico;
                    IDAOCatalogoDeCuentas cantidadCuentas = new DAOCatalogoDeCuentas();
                    String estatusCuenta = "Activa";
                    String numeroCuenta = "CU-" + cantidadCuentas.consultarCantidadCuentas();
                    ICuenta nuevaCuenta = null;
                    nuevaCuenta = new Cuenta(numeroCuenta, montoInicialConvetidoDouble, estatusCuenta, pin);
                    nuevaCuenta.asignarPropietario(clienteAsociadoConCuenta);
                    nuevaCuenta.depositar(montoInicialConvetidoDouble);
                    clienteAsociadoConCuenta.asignarCuenta(nuevaCuenta);
                    MensajeEnPantallaCuenta.imprimirMensajeRegistroExitoso(numeroCuenta, estatusCuenta, depositoInicial, nombreCliente, primerApellido, segundoApellido, telefonoCliente, correoElectronicoCliente);
                } 
                catch (Exception ex) {
                    
                }
            }
        } catch (Exception ex) {
            System.out.println("Ocurrio un error al ingresar los datos");
        }
    }
    
    private boolean validarDatos(String pPin, String pDepositoInicial, String pIdentificacionCliente) {
        boolean formatoDePinEsCorrecto = ValidacionCuenta.validarFormatoDePin(pPin);
        if(formatoDePinEsCorrecto) {
            boolean formatoDeDepositoInicialEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pDepositoInicial);
            if(formatoDeDepositoInicialEsCorrecto) {
                boolean existeCliente = ValidacionCliente.existeCliente(Conversion.convertirStringEnEntero(pIdentificacionCliente));
                if(existeCliente) {
                    return true;
                }
                else {
                    MensajeEnConsolaCuenta.imprimirMensajeDeErrorClienteAsociadoNoExiste();
                    return false;
                }
            }
            else {
                MensajeEnConsolaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroODepositoIncorrecto();
                return false;
            }
        }
        else {
            MensajeEnConsolaCuenta.imprimirMensajeDeErrorFormatoDePinInvalido();
            return false;
        }
    }
}
