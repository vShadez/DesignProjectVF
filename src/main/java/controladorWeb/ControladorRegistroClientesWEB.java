/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWEB;

import clasesUtilitarias.Conversion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.ControladorRegistroClientes;


//
/**
 *
 * @author calde
 */
@WebServlet(name = "ControladorRegistroClientesWEB", urlPatterns = {"/ControladorRegistroClientesWEB"})
public class ControladorRegistroClientesWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String primerApellido = request.getParameter("primerApellido");
        String segundoApellido = request.getParameter("segundoApellido");
        String identificacion = request.getParameter("identificacion");
        String fechaDeNacimiento = request.getParameter("fechaNacimiento");
        String numeroDeTelefono = request.getParameter("numeroTelefono");
        String correoElectronico = request.getParameter("correoElectronico");
        
        String diaDeFechaDeNacimiento = fechaDeNacimiento.substring(8, 10);
        String mesDeFechaDeNacimiento = fechaDeNacimiento.substring(5, 7);
        String anoDeFechaDeNacimiento = fechaDeNacimiento.substring(0, 4);
        response.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println("Nombre del cliente"+ nombre);
            out.println("Apellido"+ primerApellido);
            out.println("Dia"+ diaDeFechaDeNacimiento);
            out.println("Mes"+ mesDeFechaDeNacimiento);
            out.println("Ano"+ anoDeFechaDeNacimiento);
            
            //imprimirDatos(nombre, primerApellido, segundoApellido, identificacion, diaDeFechaDeNacimiento, mesDeFechaDeNacimiento, anoDeFechaDeNacimiento, numeroDeTelefono, correoElectronico);
            ControladorRegistroClientes.pasarDatos(nombre, primerApellido, segundoApellido, identificacion, diaDeFechaDeNacimiento, mesDeFechaDeNacimiento, anoDeFechaDeNacimiento, numeroDeTelefono, correoElectronico);
        }
    }
    
    /*
    public void imprimirDatos(String nombre, String primerApellido, String segundoApellido, String identificacion, String diaDeFechaDeNacimiento, String mesDeFechaDeNacimiento, String anoDeFechaDeNacimiento, String numeroDeTelefono, String correoElectronico){
        System.out.println("Nombre del cliente: "+ nombre);
        System.out.println("1 Apellido: "+ primerApellido);
        System.out.println("2 Apellido: "+ segundoApellido);
        
        int identificacionEnFormatoEntero = Conversion.convertirStringEnEntero(identificacion);
        System.out.println("Identificacion: "+ identificacionEnFormatoEntero);
        
        int dia = Conversion.convertirStringEnEntero(diaDeFechaDeNacimiento);
        int mes = Conversion.convertirStringEnEntero(mesDeFechaDeNacimiento);
        int ano = Conversion.convertirStringEnEntero(anoDeFechaDeNacimiento);
        
        System.out.println("Dia: "+ dia);
        System.out.println("Mes: "+ mes);
        System.out.println("Ano: "+ ano);
        
        
        int numeroTelefonicoEnFormatoEntero = Conversion.convertirStringEnEntero(numeroDeTelefono);
        
        System.out.println("Numero telefono: "+ numeroTelefonicoEnFormatoEntero);
        System.out.println("Correo: "+ correoElectronico);
        
    }*/
}