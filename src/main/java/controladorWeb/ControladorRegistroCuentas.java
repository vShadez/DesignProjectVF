/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.ManejoFechas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICliente;
import logicaDeNegocios.ICuenta;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;
import vistaGUI.RegistroCuentasVista;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCliente;
import clasesUtilitarias.Conversion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;

/**
 *
 * @author estadm
 */
@WebServlet(
  name = "ControladorRegistroCuentas", 
  urlPatterns = "/RegistroCuentas")
public class ControladorRegistroCuentas extends HttpServlet{
    private ControladorRegistroCuentas controladorRegistroCuentas = new ControladorRegistroCuentas();

     private void processRequest(
       HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException {

         String montoInicial = request.getParameter("montoInicial");
         if (montoInicial != null) {
             int monto = 555;
             //controladorRegistroCuentas.getMontoInicial(monto)
             "HOLA"
               .ifPresent(s -> request.setAttribute("RegistroCuentas", s));
         }

         RequestDispatcher dispatcher = request.getRequestDispatcher(
           "/RegistroCuentas.jsp");
         dispatcher.forward(request, response);
     }
   String pin = "LMAO";
   request.setAttribute("pin", pin);
   
   request.getAttribute("primerApellido");


   // Ask the view to take care of the values
   request.getRequestDispatcher("RegistroCuentas.jsp").forward(request, response);
}

/*

public class ControladorRegistroCuentas implements ActionListener{
    public RegistroCuentasVista vistaGUI;
    
    public ControladorRegistroCuentas(RegistroCuentasVista pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnRegistrar.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Registrar")){
            String pin = this.vistaGUI.txtPinDatosCuenta.getText();
            String montoInicial = this.vistaGUI.txtMontoInicial.getText();
            String identificacionCliente = this.vistaGUI.txtIdentificacionDatodCuenta.getText();
            if(validacion.ExpresionRegular.verificarContieneCaracterEspecial(pin) && validacion.ExpresionRegular.verificarContieneLetraMayuscula(pin) && validacion.ExpresionRegular.verificarContieneNumero(pin) && validacion.ExpresionRegular.verificarContieneSeisCaracteresSinEspacios(pin) && validacion.ExpresionRegular.verificarEsNumero(montoInicial)){
                try {
                    double montoInicialConvetidoDouble = Conversion.convertirStringEnDecimal(montoInicial);
                    IDAOCliente DAOCliente = new DAOCliente();
                    ICliente clienteAsociadoConCuenta = DAOCliente.consultarCliente(Conversion.convertirStringEnEntero(identificacionCliente));
                    Cliente cliente = (Cliente) clienteAsociadoConCuenta;
                    String nombreCliente = cliente.nombre;
                    String primerApellido = cliente.primerApellido;
                    String segundoApellido = cliente.segundoApellido;
                    int telefonoCliente = cliente.numeroTelefono;
                    String correoElectronicoCliente = cliente.correoElectronico;
                    IDAOCatalogoDeCuentas cantidadCuentas = new DAOCatalogoDeCuentas();
                    String estatusCuenta = "Activa";
                    String numeroCuenta = "CU-" + cantidadCuentas.consultarCantidadCuentas();
                    ICuenta nuevaCuenta = null;
                    nuevaCuenta = new Cuenta(numeroCuenta, montoInicialConvetidoDouble, estatusCuenta, pin);
                    nuevaCuenta.asignarPropietario(clienteAsociadoConCuenta);
                    nuevaCuenta.depositar(montoInicialConvetidoDouble);
                    clienteAsociadoConCuenta.asignarCuenta(nuevaCuenta);
                    MensajeEnPantallaCuenta.imprimirMensajeRegistroExitoso(numeroCuenta, estatusCuenta, montoInicial, nombreCliente, primerApellido, segundoApellido, telefonoCliente, correoElectronicoCliente);
                } 
                catch (Exception ex) {
                    
                }
            }
            
        }
    }
   
}
*/
