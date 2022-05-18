/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.PalabraSecreta;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import serviciosExternos.EnvioMensajeDeTexto;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorVerificacionMensajeDeTextoWEB", urlPatterns = {"/vistaWeb/VerificacionMensajeDeTexto"})
public class ControladorVerificacionMensajeDeTextoWEB extends HttpServlet {
    private int cantidadDeIntentos = 0;
    private String numeroDeCuenta;
    private int numeroDeTelefono;
    private String mensajeSecreto;
    private String transaccionAsociada;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String numeroDeCuenta = request.getParameter("numeroCuenta");
            
            request.setAttribute("numeroDeCuenta", numeroDeCuenta);
            IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
            Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(numeroDeCuenta);
            this.numeroDeTelefono = clienteAsociadoACuenta.numeroTelefono;
            enviarMensajeDeTexto();
            request.getRequestDispatcher("VerificacionMensajeDeTexto.jsp").forward(request, response);
            
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("Entre Segunda parte");
            String numeroDeCuenta = request.getParameter("numeroDeCuenta");
            String mensajeSecreto = request.getParameter("mensajeTexto");
            //String numeroCuentaOrigen = set;
            this.cantidadDeIntentos++;
            //request.setAttribute("mensajeTexto", mensajeSecreto);
            
            //this.numeroDeCuenta = numeroDeCuenta;
            //this.transaccionAsociada = pTransaccionAsociada;
            
            
            if(this.mensajeSecreto.equals(mensajeSecreto)) {
                response.sendRedirect("SolicitarMontoDepositoYCuentaDestinoDeTransferencia?numeroCuentaOrigen=" + numeroDeCuenta);
            }else{
                if(this.cantidadDeIntentos == 1) {
                    //request.setAttribute("numeroDeCuenta", numeroDeCuenta);
                    //request.setAttribute("numeroDeCuenta", numeroDeCuenta);
                    MensajeEnPantallaCuenta.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
                    enviarMensajeDeTexto();
                    request.getRequestDispatcher("VerificacionMensajeDeTexto.jsp?numeroCuenta=" + numeroDeCuenta).forward(request, response);
                }
                else {
                    // inactivar cuenta
                    this.inactivarCuenta();
                }
                
            }
            //request.getRequestDispatcher("VerificacionMensajeDeTexto.jsp").forward(request, response);
        }
    
    
    private void enviarMensajeDeTexto() {
        EnvioMensajeDeTexto mensajeDeTexto = new EnvioMensajeDeTexto();
        this.mensajeSecreto = PalabraSecreta.generarPalabraSecreta();
        String mensaje = "Estimado usuario de la cuenta: " + this.numeroDeCuenta + " su palabra secreta es: \n";
        mensaje += this.mensajeSecreto + "\n";
        mensaje += "Ingrese esta palabra correctamente para proceder con su retiro";
        
        mensajeDeTexto.enviarMensaje(String.valueOf(this.numeroDeTelefono), mensaje);
        MensajeEnPantallaCuenta.imprimirMensajeNotificacionDeEnvioDeMensaje();
        System.out.println(this.numeroDeTelefono);
        System.out.println(mensaje);
    }
    private void inactivarCuenta() {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(this.numeroDeCuenta, "Inactiva");
        MensajeEnPantallaCuenta.imprimirMensajeAlertaDeInactivacionDeCuenta();
    }
}
