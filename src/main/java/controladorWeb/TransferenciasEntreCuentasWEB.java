/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import controlador.ControladorVerificacionMensajeDeTexto;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validacion.ValidacionCuenta;
import vistaGUI.VerificacionMensajeDeTexto;

/**
 *
 * @author estadm
 */
@WebServlet(name = "TransferenciasEntreCuentasWEB", urlPatterns = {"/vistaWeb/TransferenciasEntreCuentas"})
public class TransferenciasEntreCuentasWEB extends HttpServlet {
    private int cantidadDeIntentos = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("TransferenciasEntreCuentas.jsp").forward(request, response);
    }
    
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.cantidadDeIntentos++;
            String numeroDeCuenta = request.getParameter("numeroCuenta");
            String pin = request.getParameter("pin");
            boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(existeCuenta) {
                if(cuentaEstaActiva) {
                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                if(pinCorrespondeACuenta) {
                    //VerificacionMensajeDeTexto vistaVerificacionMensajeDeTexto = new VerificacionMensajeDeTexto();
                    System.out.println("LLEGA");
                    String transferencia = "Transferencia";
                    System.out.println(numeroDeCuenta);
                    System.out.println(pin);
                    response.sendRedirect("VerificacionMensajeDeTexto?numeroCuenta=" + numeroDeCuenta);
                    System.out.println("LLEGA");
                    /*
                    ControladorVerificacionMensajeDeTexto controladorVerificacionMensajeDeTexto = new ControladorVerificacionMensajeDeTexto(vistaVerificacionMensajeDeTexto, numeroDeCuenta, "Transferencia");
                    controladorVerificacionMensajeDeTexto.vistaGUI.setVisible(true);
                    controladorVerificacionMensajeDeTexto.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
*/
                    MensajeEnPantallaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje();
                }
                else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    }
                    else {
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    }
                }
                } else{
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            }
        }
                
        }
    
