/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import controlador.ControladorSeccionDeConsultaEstadoDeCuenta;
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
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import validacion.ValidacionCuenta;
import vistaGUI.SeleccionDeConsultaEstadoDeCuenta;

/**
 *
 * @author 
 */
@WebServlet(name = "ControladorConsultaEstadoDeCuentaWEB", urlPatterns = {"/ControladorConsultaEstadoDeCuentaWEB"})
public class ControladorConsultaEstadoDeCuentaWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
//        this.cantidadDeIntentos++;
//        String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaEstadoCuenta.getText();
//        String pin = this.vistaGUI.txtPinEstadoCuenta.getText();
//        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
//        if(existeCuenta) {
//            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
//            if(cuentaEstaActiva) {
//                boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
//                if(pinCorrespondeACuenta) {
//                    SeleccionDeConsultaEstadoDeCuenta vistaSeccionConsultaEstadoCuenta = new SeleccionDeConsultaEstadoDeCuenta();
//                    ControladorSeccionDeConsultaEstadoDeCuenta controladorSeccionConsultaEstadoCuenta = new ControladorSeccionDeConsultaEstadoDeCuenta(vistaSeccionConsultaEstadoCuenta, numeroDeCuenta);
//                    controladorSeccionConsultaEstadoCuenta.vistaGUI.setVisible(true);
//                    controladorSeccionConsultaEstadoCuenta.vistaGUI.setLocationRelativeTo(null);
//                    vistaGUI.setVisible(false);
//                } else {
//                if(this.cantidadDeIntentos == 1) {
//                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
//                }
//                else {
//                    validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
//                }
//              }
//
//            }else{
//                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
//            }
//        }else{
//            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
//        }
//        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    }
}
