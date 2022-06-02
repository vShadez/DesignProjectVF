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
@WebServlet(name = "ControladorBitacoraXMLWEB", urlPatterns = {"/vistaWeb/BitacoraXML"})
public class ControladorBitacoraXMLWEB extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             //response.setContentType("text/html;charset=UTF-8");
             RegistroDeBitacora registro1 = new RegistroDeBitacora(LocalDate.now(), "Consulta", "Web");
             Bitacora bitacoraXML1 = new BitacoraXML(registro1);
             registro1.agregarBitacora(bitacoraXML1);
             registro1.registrarEnBitacoras();
             
             String bitacoraXml;
         
             bitacoraXml = bitacoraXML1.consultarRegistrosDelDia();
             bitacoraXml = bitacoraXml.replace("<","");
             bitacoraXml = bitacoraXml.replace(">","\n");
             bitacoraXml = bitacoraXml.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraXml);
             request.getRequestDispatcher("BitacoraXML.jsp").forward(request, response);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraXMLWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
   
}
