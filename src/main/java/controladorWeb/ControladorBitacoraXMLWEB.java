/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
         response.setContentType("text/html;charset=UTF-8");
         RegistroDeBitacora registro1 = new RegistroDeBitacora(LocalDate.now(), "Consulta", "Web");
         Bitacora bitacoraXML1 = new BitacoraXML(registro1);
         
         try {
             ServletContext sc = this.getServletContext();
             String pp = bitacoraXML1.consultarRegistrosDelDia();
             String path = sc.getRealPath("C:\\Users\\estadm\\OneDrive - Estudiantes ITCR\\TEC\\Semestres\\I semestre 2022\\Diseño de software\\I proyecto\\apache-tomcat-9.0.62\\bin\\src\\main\\java\\almacenamientoXML\\VisualizacionDeBitacora.xml");
             //path = path.replace('\\','/');
             
             System.out.println(path);
             request.setAttribute("bitacora", path);
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraXMLWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
          request.getRequestDispatcher("BitacoraXML.jsp").forward(request, response);
     }
   
}
