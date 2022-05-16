/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import static controlador.ControladorConsultaDeEstatusCuenta.consultarEstatusCuenta;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import validacion.ValidacionCuenta;

/**
 *
 * @author erick
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
