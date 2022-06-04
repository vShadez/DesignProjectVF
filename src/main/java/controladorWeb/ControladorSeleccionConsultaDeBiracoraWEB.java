/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorSeleccionConsultaDeBitacoraWEB", urlPatterns = {"/vistaWeb/SeleccionConsultaDeBitacora"})
public class ControladorSeleccionConsultaDeBiracoraWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("SeleccionConsultaDeBitacora.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        String formato = request.getParameter("formatos");
    
        if(event.equals("Consulta por acciones de hoy")) {
            if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAccionesDeHoy");
            }
            if(formato.equals("CSV")){
                
            }
            if(formato.equals("TXT")){
                
            }
        }
           
        if(event.equals("Consulta por acciones de la vista CLI")) {
           response.sendRedirect("BitacoraXMLConsultaDeAccionesPorVista?formato=CLI");
        }
        if(event.equals("Consulta por acciones de la vista GUI")) {
            response.sendRedirect("BitacoraXMLConsultaDeAccionesPorVista?formato=GUI");
        }
        if(event.equals("Consulta por acciones de la vista WEB")) {
            response.sendRedirect("BitacoraXMLConsultaDeAccionesPorVista?formato=WEB");
        }
        if(event.equals("Consulta de todos las acciones")) {
            
        }
        
    }
}
