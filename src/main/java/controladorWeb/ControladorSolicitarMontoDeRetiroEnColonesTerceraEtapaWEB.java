/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import validacion.ValidacionCuenta;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapaWEB", urlPatterns = {"/vistaWeb/SolicitarMontoDeRetiroEnColonesTerceraEtapa"})
public class ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapaWEB extends HttpServlet {
    private String numeroDeCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        numeroDeCuenta = request.getParameter("numeroCuenta");
        request.setAttribute("numeroDeCuenta", numeroDeCuenta);
        
        request.getRequestDispatcher("SolicitarMontoDeRetiroEnColonesTerceraEtapa.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroDeCuentaOrigen = request.getParameter("numeroDeCuenta");
        String montoDeRetiro = request.getParameter("montoRetirado");
        boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoDeRetiro);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuentaOrigen);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuentaOrigen);
                    if(cuentaEstaActiva) {
                        double montoDeRetiroEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoDeRetiro);
                        double montoComision = calcularMontoComision(montoDeRetiroEnFormatoDecimal, numeroDeCuentaOrigen);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(numeroDeCuentaOrigen, montoDeRetiroEnFormatoDecimal + montoComision);
                        if(hayFondosSuficientes) {
                            efectuarRetiro(numeroDeCuentaOrigen, montoDeRetiroEnFormatoDecimal);
                            response.sendRedirect("../index.html");
                        }
                        else {
                            MensajeEnPantallaCuenta.imprimirMensajeDeErrorFondosInsuficientes();
                            request.getRequestDispatcher("CambioDePinTerceraEtapa.jsp?numeroCuenta=" + numeroDeCuentaOrigen).forward(request, response);
                        }
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                        response.sendRedirect("../index.html");
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuentaOrigen);
                    response.sendRedirect("../index.html");
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
                request.getRequestDispatcher("CambioDePinTerceraEtapa.jsp?numeroCuenta=" + numeroDeCuentaOrigen).forward(request, response);
            }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar, String numeroDeCuenta) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(numeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    private void efectuarRetiro(String numeroDeCuenta, double montoDeRetiro) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(numeroDeCuenta);
        cuenta.retirar(montoDeRetiro);
        double montoComision = calcularMontoComision(montoDeRetiro, numeroDeCuenta);
        MensajeEnPantallaCuenta.imprimirMensajeRetiroEnColonesExitoso(montoDeRetiro, montoComision);
    }
}