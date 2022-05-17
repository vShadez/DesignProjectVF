/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import controlador.ControladorDepositoEnColones;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 */
@WebServlet(name = "ControladorRegistroClientesWEB", urlPatterns = {"/vistaWeb/RegistroClientes"})
public class ControladorRegistroClientesWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("RegistroClientes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String cuenta = request.getParameter("nombre");
        String montoDeposito = request.getParameter("primerApellido");
        
        response.setContentType("text/html; charset=UTF-8");
        
        if (ControladorDepositoEnColones.depositarColonesACuenta(cuenta, montoDeposito) == true){
            response.sendRedirect("../index.html");
        } else {
            request.getRequestDispatcher("RegistroClientes.jsp").forward(request, response);
        }
    }
}