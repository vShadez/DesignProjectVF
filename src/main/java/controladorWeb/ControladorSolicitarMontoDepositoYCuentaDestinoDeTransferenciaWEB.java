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
 * @author estadm
 */
@WebServlet(name = "ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferenciaWEB", urlPatterns = {"/vistaWeb/SolicitarMontoDepositoYCuentaDestinoDeTransferencia"})
public class ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferenciaWEB extends HttpServlet {
    private String numeroDeCuentaOrigen;
    private String numeroCuentaDestino;
    private String montoEnviado;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            numeroDeCuentaOrigen = request.getParameter("numeroCuentaOrigen");
            numeroCuentaDestino =  request.getParameter("numeroCuentaDestino");
            montoEnviado = request.getParameter("montoEnviado");
            
            System.out.println(numeroDeCuentaOrigen);
            System.out.println(numeroCuentaDestino);
            System.out.println(montoEnviado);
            request.getRequestDispatcher("SolicitarMontoDepositoYCuentaDestinoDeTransferencia.jsp").forward(request, response);
        }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean formatoDeMontoDeRetiroEsCorrecto = ValidacionCuenta.validarFormatoDeMontoDeRetiroODeposito(montoEnviado);
            if(formatoDeMontoDeRetiroEsCorrecto) {
                boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroCuentaDestino);
                if(existeCuenta) {
                    boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroCuentaDestino);
                    if(cuentaEstaActiva) {
                        double montoDeRetiroEnFormatoDecimal = Conversion.convertirStringEnDecimal(montoEnviado);
                        double montoComision = this.calcularMontoComision(montoDeRetiroEnFormatoDecimal);
                        boolean hayFondosSuficientes = ValidacionCuenta.validarHayFondosSuficientes(this.numeroDeCuentaOrigen, montoDeRetiroEnFormatoDecimal + montoComision);
                        if(hayFondosSuficientes) {
                            efectuarTransferencia(numeroCuentaDestino, montoDeRetiroEnFormatoDecimal);
                        }
                        else {
                            MensajeEnPantallaCuenta.imprimirMensajeDeErrorFondosInsuficientes();
                            request.getRequestDispatcher("SolicitarMontoDepositoYCuentaDestinoDeTransferencia.jsp").forward(request, response);
                        }
                    }
                    else {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                        request.getRequestDispatcher("SolicitarMontoDepositoYCuentaDestinoDeTransferencia.jsp").forward(request, response);
                    }
                }
                else {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroCuentaDestino);
                    request.getRequestDispatcher("SolicitarMontoDepositoYCuentaDestinoDeTransferencia.jsp").forward(request, response);
                }
            }
            else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto();
                request.getRequestDispatcher("SolicitarMontoDepositoYCuentaDestinoDeTransferencia.jsp").forward(request, response);
            }
    }
    
    private double calcularMontoComision(double pMontoPorRetirar) {
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(this.numeroDeCuentaOrigen);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoPorRetirar * 0.02;
        }
        return montoComision;
    }
    
    public void efectuarTransferencia(String pNumeroDeCuentaDestino, double pMontoTransferido) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuentaDeOrigen = (Cuenta) daoCuenta.consultarCuenta(this.numeroDeCuentaOrigen);
        Cuenta cuentaDeDestino = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuentaDestino);
        double montoComision = cuentaDeOrigen.calcularMontoComision(pMontoTransferido);
        cuentaDeOrigen.transferir(cuentaDeDestino, pMontoTransferido);
        cuentaDeOrigen.retirar(pMontoTransferido);
        MensajeEnPantallaCuenta.imprimirMensajeTransferenciaExitosa(pMontoTransferido, montoComision);
    }
   
}