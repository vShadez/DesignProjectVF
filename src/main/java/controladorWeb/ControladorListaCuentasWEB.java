/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorListaCuentasWEB", urlPatterns = {"/vistaWeb/ListaCuentas"})
public class ControladorListaCuentasWEB extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentasDesordenada = daoCatalogoDeCuentas.consultarListaDeCuentas();
        
        int tamanoListaCuenta = daoCatalogoDeCuentas.consultarCantidadCuentas();
        
        Cuenta[] listaDeCuentasOrdenada = Conversion.convertirListaCuentaEnArreglo(listaDeCuentasDesordenada, tamanoListaCuenta);
        
        Ordenamiento.ordenarDescendentemente(listaDeCuentasOrdenada);
        List<CuentaDto> dtos =  new LinkedList<CuentaDto>();
        
        for(int i = 0; i < tamanoListaCuenta; i++) {
            Cuenta cuenta = listaDeCuentasOrdenada[i];
            Cliente duenoDeCuenta = (Cliente) cuenta.propietario;
            String nombreCompletoDePropietarioDeCuenta = duenoDeCuenta.nombre +" "+ duenoDeCuenta.primerApellido +" "+ duenoDeCuenta.segundoApellido;
            int identificacionDePropietarioDeCuenta = duenoDeCuenta.identificacion;
          dtos.add(new CuentaDto(cuenta.numeroCuenta, cuenta.getSaldo(),cuenta.estatus, nombreCompletoDePropietarioDeCuenta, identificacionDePropietarioDeCuenta));
        }
        request.setAttribute("dtos", dtos);
        
        request.getRequestDispatcher("ListaCuentas.jsp").forward(request, response);
    }
    
    
    
    public class CuentaDto {
        private String numeroCuenta;        
        private double saldo;
        private String estatus;
        private String propietario;
        private int identificacion;

        public CuentaDto(String pNumeroCuenta, double pSaldo, String pEstatus, String pNombreCompleto, int pIdentificacion) {
            this.numeroCuenta = pNumeroCuenta;
            this.saldo = pSaldo;
            this.estatus = pEstatus;
            this.propietario = pNombreCompleto;
            this.identificacion = pIdentificacion;
        }

        public String getNumeroCuenta() {
            return numeroCuenta;
        }

        public double getSaldo() {
            return saldo;
        }

        public String getEstatus() {
            return estatus;
        }
        public String getNombreCompleto() {
            return propietario;
        }

        public int getIdentificacion() {
            return identificacion;
        }
    }
    }

