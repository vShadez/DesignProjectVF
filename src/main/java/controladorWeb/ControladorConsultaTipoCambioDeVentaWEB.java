/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serviciosExternos.TipoCambioBCCR;

/**
 *
 * @author 
 */
@WebServlet(name = "ControladorConsultaTipoCambioDeVentaWEB", urlPatterns = {"/vistaWeb/ConsultaTipoCambioDeVenta"})
public class ControladorConsultaTipoCambioDeVentaWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoVenta = tc.obtenerValorVenta();
        request.setAttribute("tipoDeCambioVenta", tipoVenta);
        request.getRequestDispatcher("ConsultaTipoCambioDeVenta.jsp").forward(request, response);
    }
}
