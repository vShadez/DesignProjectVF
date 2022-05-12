/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWEB;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;

//
/**
 *
 * @author calde
 */
@WebServlet(name = "ControladorRegistroClientesWEB", urlPatterns = {"/ControladorRegistroClientesWEB"})
public class ControladorRegistroClientesWEB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        String nombre = solicitud.getParameter("nombre");
        String primerApellido = solicitud.getParameter("primerApellido");
        String segundoApellido = solicitud.getParameter("segundoApellido");
        String identificacion = solicitud.getParameter("identificacion");
        String fechaDeNacimiento = solicitud.getParameter("fechaNacimiento");
        String numeroDeTelefono = solicitud.getParameter("numeroTelefono");
        String correoElectronico = solicitud.getParameter("correoElectronico");
        
        //String diaDeFechaDeNacimiento = fechaDeNacimiento.substring(0, 2);
        //String mesDeFechaDeNacimiento = fechaDeNacimiento.substring(3, 5);
        //String anoDeFechaDeNacimiento = fechaDeNacimiento.substring(6, 10);
        //int diaDeFechaDeNacimientoEnFormatoEntero = Conversion.convertirStringEnEntero(diaDeFechaDeNacimiento);
        //int mesDeFechaDeNacimientoEnFormatoEntero = Conversion.convertirStringEnEntero(mesDeFechaDeNacimiento);
        //int anoDeFechaDeNacimientoEnFormatoEntero = Conversion.convertirStringEnEntero(anoDeFechaDeNacimiento);
        
        int identificacionEnFormatoEntero = Conversion.convertirStringEnEntero(identificacion);
        int numeroDeTelefonoEnFormatoEntero = Conversion.convertirStringEnEntero(numeroDeTelefono);
        
        if(validarTipoDeDatos(identificacion) && validarFormatoDeDatos(numeroDeTelefono, correoElectronico) && validarExistenciaCliente(identificacionEnFormatoEntero)) {
            IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
            String codigo = "CIF-" + cantidadDeClientes.consultarCantidadDeClientes();
            ICliente nuevoCliente = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, 23, 05, 2001, numeroDeTelefonoEnFormatoEntero, correoElectronico);
        }
        
        solicitud.getRequestDispatcher("/RegistroClientes.jsp").forward(solicitud, respuesta);
    }
    
    public boolean validarTipoDeDatos(String pIdentificacion) {
         if(ValidacionTipoDeDato.verificarEsEntero(pIdentificacion) == false) {
             MensajeEnPantallaCliente.imprimirErrorIdentificacionInvalida();
             return false;
         }
         else {
             return true;
         }
     }

     public boolean validarFormatoDeDatos(String pNumeroDeTelefono, String pCorreoElectronico) {
         if(ExpresionRegular.verificarFormatoCorreoElectronicoEsValido(pCorreoElectronico) == false) {
           MensajeEnPantallaCliente.imprimirErrorFormatoDeCorreoIncorrecto();
           return false;
         }
         else if(ExpresionRegular.verificarFormatoNumeroTelefonicoEsValido(pNumeroDeTelefono) == false) {
             MensajeEnPantallaCliente.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto();
             return false;
         }
         else {
             return true;
         }
     }

     public boolean validarExistenciaCliente(int pIdentificacion){
         IDAOCatalogoDeClientes prueb = new DAOCatalogoDeClientes();
         if(prueb.consultarSiExisteCliente(pIdentificacion) == false) {
             MensajeEnPantallaCliente.imprimirErrorIdentificacionExistente();
             return false;
         }
         else {
             return true;
         }
     }
}