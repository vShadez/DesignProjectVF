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
@WebServlet(name = "ControladorBitacoraXMLConsultaAccionesDeHoyWEB", urlPatterns = {"/vistaWeb/BitacoraXMLConsultaDeAccionesDeHoy"})
public class ControladorBitacoraXMLConsultaAccionesDeHoyWEB extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             Bitacora bitacoraXML1 = new BitacoraXML(null);
             //registro1.agregarBitacora(bitacoraXML1);
             //registro1.registrarEnBitacoras();
             
             String bitacoraXml;
             
             bitacoraXml = bitacoraXML1.consultarRegistrosDelDia();
             bitacoraXml = bitacoraXml.replace("<","");
             bitacoraXml = bitacoraXml.replace(">","\n");
             bitacoraXml = bitacoraXml.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraXml);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraXMLConsultaAccionesDeHoyWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraXMLConsultaDeAccionesDeHoy.jsp").forward(request, response);
         
    }
   
}
