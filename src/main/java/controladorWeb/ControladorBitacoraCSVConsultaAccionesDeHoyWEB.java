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
import logicaDeNegocios.BitacoraCSV;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorBitacoraCSVConsultaAccionesDeHoyWEB", urlPatterns = {"/vistaWeb/BitacoraCSVConsultaDeAccionesDeHoy"})
public class ControladorBitacoraCSVConsultaAccionesDeHoyWEB extends HttpServlet {

     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
             Bitacora bitacoraCSV1 = new BitacoraCSV(null);             
             String bitacoraCsv;
             
             bitacoraCsv = bitacoraCSV1.consultarRegistrosDelDia();
             //bitacoraCsv = bitacoraCsv.replace("<","");
             //bitacoraCsv = bitacoraCsv.replace(">","\n");
             bitacoraCsv = bitacoraCsv.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraCsv);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraCSVConsultaAccionesDeHoyWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraCSVConsultaDeAccionesDeHoy.jsp").forward(request, response);
         
    }
   
}
