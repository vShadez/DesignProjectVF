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
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentasWEB", urlPatterns = {"/vistaWeb/ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas"})
public class ControladorConsultaGananciasCobroComisionesTotalesPorTodasLasCuentasWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("Entrando 1");
            Lista<ICuenta> listaCuentas;
            Cuenta[] arregloCuentasOrdenadas;
            
            IDAOCatalogoDeCuentas cuentas = new DAOCatalogoDeCuentas();
            int cantidadCuentas = cuentas.consultarCantidadCuentas();
            listaCuentas = cuentas.consultarListaDeCuentas();
            
            arregloCuentasOrdenadas = Conversion.convertirListaCuentaEnArreglo(listaCuentas, cantidadCuentas);
            List<OperacionDto> operacionesAMostrar =  new LinkedList<>();
            for (int i = 0; i < cantidadCuentas; i++) {
                System.out.println("Entrando 2");
                String numeroDeCuenta = arregloCuentasOrdenadas[i].numeroCuenta;
                IDAOOperacionCuenta operacionesCenta = new DAOOperacionCuenta();
                double cantidadDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorDepositos(numeroDeCuenta);
                double cantidadRetirosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetiros(numeroDeCuenta);
                double cantidadRetirosDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroDeCuenta);
                
                operacionesAMostrar.add(new OperacionDto(numeroDeCuenta, String.format("%.2f",cantidadDepositosRealizados),String.format("%.2f",cantidadRetirosRealizados), String.format("%.2f",cantidadRetirosDepositosRealizados)));
         
                request.setAttribute("operacionesAsociadas", operacionesAMostrar);
        
                request.getRequestDispatcher("ConsultaGananciasCobroComisionesTotalesPorTodasLasCuentas.jsp").forward(request, response);
               
            }
        }
    public class OperacionDto {
        private String numeroCuenta;
        private String depositos;
        private String retiros;
        private String DepositosRetiros;

        public OperacionDto(String pNumeroCuenta, String cantDepositos, String cantRetiros, String cantDepositosRetiros) {
            this.numeroCuenta = pNumeroCuenta;
            this.depositos = cantDepositos;
            this.retiros = cantRetiros;
            this.DepositosRetiros = cantDepositosRetiros;
        }
        public String getNumeroCuenta() {
            return numeroCuenta;
        }
        
        public String getDepositos() {
            return depositos;
        }

        public String getRetiros() {
            return retiros;
        }

        public String getDepositoRetiro() {
            return DepositosRetiros;
        }

        
    }
}
