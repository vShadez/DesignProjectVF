/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorWeb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;
import vistaGUI.RegistroClientes;
import clasesUtilitarias.Conversion;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jairo Calderón
 */

public class ControladorRegistroClientes extends HttpServlet{
   @Override
   protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
   // ... code to build your values
   
   // Set the values
   System.out.println("nombre");
   System.out.println(request.getAttribute("nombre"));
   request.getAttribute("primerApellido");
   request.getAttribute("segundoApellido");
   //request.gettAttribute("identificacion");
   //request.gettAttribute("numeroTelefono");
   //System.out.println(request.gettAttribute("identificacionEnFormatoEntero"));
   //request.gettAttribute("numeroTelefonicoEnFormatoEntero");

   // Ask the view to take care of the values
   request.getRequestDispatcher("RegistroClientes.jsp").forward(request, response);
   
}
    /*
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Registrar")) {
            
            
            String formatoFecha = "dd/MM/YYYY";
            Date fecha = this.vistaGUI.fechaNacimientoCliente.getDatoFecha();
            SimpleDateFormat formateador = new SimpleDateFormat(formatoFecha);
            String fechaDeNacimiento = formateador.format(fecha);
            
            String[] split = fechaDeNacimiento.split("/");
            int dia = Conversion.convertirStringEnEntero(split[0]);
            int mes = Conversion.convertirStringEnEntero(split[1]);
            int ano = Conversion.convertirStringEnEntero(split[2]);
            
            if(validarTipoDeDatos(identificacion) && validarFormatoDeDatos(numeroDeTelefono, correoElectronico) && validarExistenciaCliente(identificacionEnFormatoEntero)) {
                IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
                String codigo = "CIF-" + cantidadDeClientes.consultarCantidadDeClientes();
                ICliente nuevoCliente = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroTelefonicoEnFormatoEntero, correoElectronico);
                
                MensajeEnPantallaCliente.imprimirMensajeCreadoExitoso(codigo, nombre, identificacion, fechaDeNacimiento, numeroDeTelefono);
            }
        }
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
    */
}
