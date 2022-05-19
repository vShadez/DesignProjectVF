/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import static controlador.ControladorConsultaDeEstatusCuenta.consultarEstatusCuenta;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorConsultaDeEstatusCuentaWEB", urlPatterns = {"/vistaWeb/ConsultaDeEstatusCuenta"})
public class ControladorConsultaDeEstatusCuentaWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("ConsultaDeEstatusCuenta.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        
        if (consultarEstatusCuenta(numeroDeCuenta) == true){
            response.sendRedirect("../index.html");
        }else{
            request.setAttribute("error", "La cuenta no existe");
            request.getRequestDispatcher("ConsultaDeEstatusCuenta.jsp").forward(request, response);
        }
    }
}
