
package controladorWeb;

import clasesUtilitarias.Conversion;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.Operacion;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorInformacionPorConsultaDeEstadoCuentaColonesWEB", urlPatterns = {"/vistaWeb/InformacionPorConsultaDeEstadoCuentaColones"})
public class ControladorInformacionPorConsultaDeEstadoCuentaColonesWEB extends HttpServlet {
    private String numeroCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        System.out.println(numeroCuenta);
        
        Operacion[] operacionesTotales;
        IDAOCuentaIndividual cuenta = new DAOCuentaIndividual();
        Cuenta cuentaRecibida = (Cuenta) cuenta.consultarCuenta(numeroCuenta);
        
        Cliente clientePropietario = (Cliente) cuentaRecibida.propietario;
        IDAOOperacionCuenta operacion = new DAOOperacionCuenta();
        Lista<Operacion> operaciones = operacion.consultarOperacionesCuenta(numeroCuenta);
        int cantidadOperaciones = operacion.consultarCantidadDeDepositosYRetirosRealizados(numeroCuenta);
        
        request.setAttribute("numeroCuenta", cuentaRecibida.numeroCuenta);
        request.setAttribute("saldo", cuentaRecibida.getSaldo());
        request.setAttribute("propietario", clientePropietario.nombre +""+ clientePropietario.primerApellido +""+ clientePropietario.segundoApellido);
        request.setAttribute("correoElectronico", clientePropietario.correoElectronico);
        request.setAttribute("numeroTelefono", clientePropietario.numeroTelefono);
        
        operacionesTotales = Conversion.convertirListaOperacionEnArreglo(operaciones, cantidadOperaciones);
        
        Operacion operacione[] = (operacionesTotales);
        
        List<OperacionDto> operacionesAMostrar =  new LinkedList<>();
        for (int i = 0; i < cantidadOperaciones; i++) {
            operacionesAMostrar.add(new OperacionDto(operacione[i].tipoOperacion, operacione[i].fechaOperacion,operacione[i].seAplicoComision, operacione[i].montoComision));
        }
        
        request.setAttribute("operacionesAsociadas", operacionesAMostrar);
        
        request.getRequestDispatcher("InformacionPorConsultaDeEstadoCuentaColones.jsp").forward(request, response);
        }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        }
    
    public class OperacionDto {
        private String tipoOperacion;
        private String fecha;
        private String monto;
        private String comision;

        public OperacionDto(String pTipoOperacion, LocalDate pFecha, String pMonto, String pComision) {
            this.tipoOperacion = pTipoOperacion;
            this.fecha = pFecha;
            this.monto = pMonto;
            this.comision = pComision;
        }

        public String getTipoOperacion() {
            return tipoOperacion;
        }

        public String getFecha() {
            return fecha;
        }

        public String getMont() {
            return monto;
        }
        public String getComision() {
            return comision;
        }

        
    }
}
