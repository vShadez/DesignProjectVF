/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import validacion.ValidacionCliente;
import validacion.ValidacionTipoDeDato;
import listaDinamica.Nodo;
import logicaDeNegocios.Cuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeDatosDeUnClienteCLI {
    public ConsultaDeDatosDeUnClienteCLI() throws Exception {
        cargarClientesRegistrados();
    }
    
    private void cargarClientesRegistrados() throws Exception {
        Cliente[] arregloClientesOrdenados;
        IDAOCatalogoDeClientes daoCatalogoDeClientes = new DAOCatalogoDeClientes();
        int cantidadDeClientes = daoCatalogoDeClientes.consultarCantidadDeClientes();
        Lista<ICliente> consultarListaCliente = daoCatalogoDeClientes.consultarListaDeClientes();
        arregloClientesOrdenados = Conversion.convertirListaClienteEnArreglo(consultarListaCliente, cantidadDeClientes);
        Cliente cliente[] = arregloClientesOrdenados;
        System.out.println("Lista de clientes registrados en el sistema:");
        for (int i = 0; i < cantidadDeClientes; i++) {
            String primerApellido = cliente[i].primerApellido;
            String segundoApellido = cliente[i].segundoApellido;
            String nombre = cliente[i].nombre;
            int identificacion = cliente[i].identificacion;
            System.out.println("\nCliente n°" + i+1 + ":");
            System.out.println("Nombre completo: " + nombre + primerApellido + segundoApellido);
            System.out.println("Identificación: " + identificacion);
        }
        System.out.println("\nDigite la identificación del cliente sobre el cual desea conocer los detalles:");
        this.recibirIdentificacionDeCliente();
    }
    
    private void recibirIdentificacionDeCliente() throws Exception {
        String identificacion = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
        boolean identificacionIngresadaEsCorrecta = validarDatos(identificacion);
        if(identificacionIngresadaEsCorrecta) {
            this.mostrarDetallesDeCliente(identificacion);
        }
    }
    
    private boolean validarDatos(String pIdentificacion) {
        boolean formatoDeIdentificacionEsCorrecto = ValidacionTipoDeDato.verificarEsEntero(pIdentificacion);
        if(formatoDeIdentificacionEsCorrecto) {
            boolean existeCliente = ValidacionCliente.existeCliente(Conversion.convertirStringEnEntero(pIdentificacion));
            if(existeCliente) {
                return true;
            }
            else {
                System.out.println(MensajeEnConsolaCliente.imprimirErrorIdentificacionInexistente());
                return false;
            }
        }
        else {
            System.out.println(MensajeEnConsolaCliente.imprimirErrorIdentificacionInvalida());
            return false;
        }
    }
    
    private void mostrarDetallesDeCliente(String pIdentificacionDeCliente) {
        int identificacionDeCliente = Conversion.convertirStringEnEntero(pIdentificacionDeCliente);
        IDAOCliente daoCliente = new DAOCliente();
        Cliente cliente = (Cliente) daoCliente.consultarCliente(identificacionDeCliente);
        System.out.println("Información del cliente: ");
        System.out.println("Código: " + cliente.getCodigo());
        System.out.println("Identificación: " + cliente.identificacion);
        System.out.println("Nombre completo: " + cliente.nombre + " " + cliente.primerApellido + " " + cliente.segundoApellido);
        System.out.println("Fecha de nacimiento: " + cliente.getFechaNacimiento());
        System.out.println("Correo electrónico: " + cliente.correoElectronico);
        System.out.println("Número de teléfono: " + cliente.numeroTelefono);
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Lista<ICuenta> listaDeCuentasDeCliente = daoClienteCuenta.consultarCuentasDeCliente(identificacionDeCliente);
        Nodo puntero = listaDeCuentasDeCliente.inicio;
        System.out.println("Cuentas asignadas al cliente: \n");
        while(puntero != null) {
            Cuenta cuenta = (Cuenta) puntero.objeto;
            System.out.println("Número de cuenta" + cuenta.numeroCuenta + ":");
        }
    }
}
