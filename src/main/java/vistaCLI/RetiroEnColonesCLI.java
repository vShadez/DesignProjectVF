/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.PalabraSecreta;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import serviciosExternos.EnvioCorreoElectronico;
import serviciosExternos.EnvioMensajeDeTexto;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class RetiroEnColonesCLI {
    private int cantidadDeIntentosRealizados = 0;
    private final int cantidadMaximaDeIntentosPermitida = 2;
    
    public RetiroEnColonesCLI() throws Exception {
        recibirDatos();
    }
    
    private void recibirDatos() throws Exception {
        try {
            System.out.println("Ingrese su número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese el pin de su cuenta");
            String pin = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosIngresadosSonValidos = this.validarDatos(numeroDeCuenta, pin);
            if(datosIngresadosSonValidos) {
                this.enviarMensajeDeTexto(numeroDeCuenta);
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
                System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(pNumeroDeCuenta, pPin));
                return false;
            }
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void enviarMensajeDeTexto(String pNumeroDeCuenta) throws Exception {
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje());
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroDeCuenta);
        int numeroDeTelefonoDeDuenoDeLaCuenta = clienteAsociadoACuenta.numeroTelefono;
        EnvioMensajeDeTexto mensajeDeTexto = new EnvioMensajeDeTexto();
        String mensajeSecreto = PalabraSecreta.generarPalabraSecreta();
        String mensaje = "Estimado usuario de la cuenta: " + pNumeroDeCuenta + " su palabra secreta es: \n";
        mensaje += mensajeSecreto + "\n";
        mensaje += "Ingrese esta palabra correctamente para proceder con su retiro";
        mensajeDeTexto.enviarMensaje(String.valueOf(numeroDeTelefonoDeDuenoDeLaCuenta), mensaje);
        this.recibirMensajeDeTexto(pNumeroDeCuenta, mensajeSecreto);
    }
    
    private void recibirMensajeDeTexto(String pNumeroDeCuenta, String pMensajeDeTextoEnviado) throws Exception {
        try {
            String mensajeIngresado = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            this.cantidadDeIntentosRealizados++;
            if(pMensajeDeTextoEnviado.equals(mensajeIngresado)) {
                this.recibirMontoDeRetiro(pNumeroDeCuenta);
            }
            else {
                if(this.cantidadDeIntentosRealizados == this.cantidadMaximaDeIntentosPermitida) {
                    this.inactivarCuenta(pNumeroDeCuenta);
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                    if(usuarioDeseaVolverAMenuPrincipal) {
                        MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                    }
                    else {
                        this.enviarMensajeDeTexto(pNumeroDeCuenta);
                        MensajeEnConsolaCuenta.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
                    }
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
                this.enviarMensajeDeTexto(pNumeroDeCuenta);
                this.cantidadDeIntentosRealizados = this.cantidadDeIntentosRealizados - 1;
            }
        }
    }
    
    private void inactivarCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(pNumeroDeCuenta, "Inactiva");
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeAlertaDeInactivacionDeCuenta());
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroDeCuenta);
        String correoDestinatario = clienteAsociadoACuenta.correoElectronico;
        String mensajeDeCorreo = "";
        mensajeDeCorreo += "Estimado cliente: su cuenta " + pNumeroDeCuenta + " ha sido desactividada \n";
        mensajeDeCorreo += "La inactivación se debe a que realizó muchos intentos de validación de retiro/transferencia \n";
        String asuntoDeCorreo = "Inactivación de cuenta " + pNumeroDeCuenta;
        EnvioCorreoElectronico.enviarCorreo(correoDestinatario, asuntoDeCorreo, mensajeDeCorreo);
    }
    
    private void recibirMontoDeRetiro(String pNumeroDeCuenta) throws Exception {
        try {
            System.out.println("Ingrese el monto de su retiro en colones");
            String montoDeRetiro = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean montoDeRetiroEsValido = this.validarMontoDeRetiro(pNumeroDeCuenta, montoDeRetiro);
            if(montoDeRetiroEsValido) {
                double montoDeRetiroEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoDeRetiro);
                this.efectuarRetiro(pNumeroDeCuenta, montoDeRetiroEnFormatoDecimal);
            }
            else {
                boolean usuarioDeseaVolverAMenuPrincipal = TextoIngresadoPorElUsuario.regresarAMenuPrincipal();
                if(usuarioDeseaVolverAMenuPrincipal) {
                    MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
                }
                else {
                    this.recibirMontoDeRetiro(pNumeroDeCuenta);
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
                this.recibirMontoDeRetiro(pNumeroDeCuenta);
            }
        }   
    }
    
    private boolean validarMontoDeRetiro(String pNumeroDeCuenta, String pMontoDeRetiro) {
        boolean montoDeRetiroEsValido = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(pMontoDeRetiro);
        if(montoDeRetiroEsValido) {
            double montoDeRetiro = Conversion.convertirStringEnDecimal(pMontoDeRetiro);
            boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(pNumeroDeCuenta, montoDeRetiro);
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
    
    private double calcularMontoComision(String pNumeroDeCuenta, double pMontoPorRetirar) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    private void efectuarRetiro(String numeroDeCuenta, double montoDeRetiro) throws Exception {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.retirar(montoDeRetiro);
        double montoComision = this.calcularMontoComision(numeroDeCuenta, montoDeRetiro);
        System.out.println(MensajeEnConsolaCuenta.imprimirMensajeRetiroEnColonesExitoso(montoDeRetiro, montoComision));
        MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
    }
}
