package controladorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeNegocios.Bitacora;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorBitacoraXMLConsultaDeTodasLasAccionesWEB", urlPatterns = {"/vistaWeb/BitacoraXMLConsultaDeTodasLasAcciones"})
public class ControladorBitacoraXMLConsultaDeTodasLasAccionesWEB extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             Bitacora bitacoraXML1 = new BitacoraXML(null);             
             String bitacoraXml;
             bitacoraXml = bitacoraXML1.consultarTodosLosRegistros();
             bitacoraXml = bitacoraXml.replace("<","");
             bitacoraXml = bitacoraXml.replace(">","\n");
             bitacoraXml = bitacoraXml.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraXml);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraXMLConsultaDeTodasLasAccionesWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraXMLConsultaDeTodasLasAcciones.jsp").forward(request, response);
         
    }
   
}
