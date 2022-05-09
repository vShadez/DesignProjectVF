/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ValidacionCliente;
import validacion.ValidacionTipoDeDato;

/**
 *
 * @author Jairo Calderón
 */
public class RegistroClientesCLI {
    
    public RegistroClientesCLI() {
        solicitarDatos();
    }
    
    private void solicitarDatos() {
        try {
            System.out.println("Ingrese su nombre");
            String nombre = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su primer apellido");
            String primerApellido = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su segundo apellido");
            String segundoApellido = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su identificacion");
            String identificacion = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su fecha de nacimiento en formato dd/mm/aaaa");
            String fechaDeNacimiento = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su número de teléfono");
            String numeroDeTelefono = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            System.out.println("Ingrese su correo electrónico");
            String correoElectronico = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean datosFueronIngresadosCorrectamente = validarDatos(identificacion, fechaDeNacimiento, numeroDeTelefono, correoElectronico);
            if(datosFueronIngresadosCorrectamente) {
                int identificacionEnFormatoEntero = Conversion.convertirStringEnEntero(identificacion);
                int numeroDeTelefonoEnFormatoEntero = Conversion.convertirStringEnEntero(numeroDeTelefono);
                String[] partesDeString = fechaDeNacimiento.split("/");
                int dia = Conversion.convertirStringEnEntero(partesDeString[0]);
                int mes = Conversion.convertirStringEnEntero(partesDeString[1]);
                int ano = Conversion.convertirStringEnEntero(partesDeString[2]);
                this.registrarCliente(nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroDeTelefonoEnFormatoEntero, correoElectronico);
                MenuPrincipalCLI menuPrincipal = new MenuPrincipalCLI();
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al ingresar el texto");
        }
    }
    
    private boolean validarDatos(String pIdentificacion, String pFechaDeNacimiento, String pNumeroDeTelefono, String pCorreoElectronico) {
        boolean identificacionEsDeTipoEntero = ValidacionTipoDeDato.verificarEsEntero(pFechaDeNacimiento);
        if(identificacionEsDeTipoEntero) {
            boolean fechaDeNacimientoEsValida = ValidacionCliente.validarFechaNacimiento(pFechaDeNacimiento);
            if(fechaDeNacimientoEsValida) {
                boolean numeroDeTelefonoEsValido = ValidacionCliente.verificarFormatoDeNumeroDeTelefono(pNumeroDeTelefono);
                if(numeroDeTelefonoEsValido) {
                    boolean correoEsValido = ValidacionCliente.verificarFormatoDeCorreoElectronico(pCorreoElectronico);
                    if(correoEsValido) {
                        return true;
                    }
                    else {
                        System.out.println(MensajeEnConsolaCliente.imprimirErrorFormatoDeCorreoIncorrecto());
                        return false;
                    }
                }
                else {
                    System.out.println(MensajeEnConsolaCliente.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto());
                    return false;
                }
            }
            else {
                System.out.println(MensajeEnConsolaCliente.imprimirErrorFechaDeNacimientoIncorrecta());
                return false;
            }
        }
        else {
            System.out.println(MensajeEnConsolaCliente.imprimirErrorIdentificacionInvalida());
            return false;
        }
    }
    
    private void registrarCliente(String pNombre, String pPrimerApellido, String pSegundoApellido, int pIdentificacionEnFormatoEntero, int pDia, int pMes, int pAno, int pNumeroTelefonicoEnFormatoEntero, String pCorreoElectronico) {
        IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
        String codigo = "CIF-" + cantidadDeClientes.consultarCantidadDeClientes();
        String fecha = pDia + "/" + pMes + "/" + pAno;
        ICliente nuevoCliente = new Cliente(codigo, pNombre, pPrimerApellido, pSegundoApellido, pIdentificacionEnFormatoEntero, pDia, pMes, pAno, pNumeroTelefonicoEnFormatoEntero, pCorreoElectronico);
        System.out.println(MensajeEnConsolaCliente.imprimirMensajeCreadoExitoso(codigo, pNombre, String.valueOf(pIdentificacionEnFormatoEntero), fecha, String.valueOf(pNumeroTelefonicoEnFormatoEntero)));
    }
}
