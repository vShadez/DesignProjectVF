/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import serviciosExternos.EnvioCorreoElectronico;
import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
import logicaDeNegocios.Cuenta;
import serviciosExternos.TipoCambioBCCR;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;
import clasesUtilitarias.NumeroAleatorio;
import clasesUtilitarias.PalabraSecreta;
import controlador.ControladorRegistroClientes;
import controlador.ControladorRegistroCuentas;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeNegocios.ICliente;
import vistaGUI.RegistroCuentasVista;
import vistaGUI.RegistroClientes;
import vistaGUI.ListaClientes;
import controlador.ControladorListaClientes;
import controlador.ControladorListaCuentas;
import vistaGUI.ConsultaDeEstatusCuenta;
import vistaGUI.ListaCuentas;
import controlador.ControladorConsultaDeEstatusCuenta;
import vistaGUI.ConsultaDeSaldoActual;
import controlador.ControladorConsultaDeSaldoActual;
import vistaGUI.ConsultaEstadoDeCuenta;
import controlador.ControladorConsultaEstadoDeCuenta;
import controlador.ControladorDepositoEnColones;
import vistaGUI.ConsultaGananciasCobroComisionesDelBanco;
import vistaGUI.DepositoEnColones;
import controlador.ControladorConsultaGananciasCobroComisionesDelBanco;
import controlador.ControladorConsultaGananciasCobroComisionesPorCadaCuenta;
import controlador.ControladorConsultaTipoCambioDeCompra;
import controlador.ControladorConsultaTipoCambioDeVenta;
import vistaGUI.ConsultaGananciasCobroComisionesPorCadaCuenta;
import vistaGUI.ConsultaTipoCambioDeCompra;
import vistaGUI.ConsultaTipoCambioDeVenta;
import controlador.ControladorConsultaTipoCambioDeVenta;
/**
 *
 * @author calde
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        RegistroCuentas r1 = new RegistroCuentas();
        r1.registrarCuenta("algo", 2000);
        r1.registrarCuenta("algo", 5000);
        r1.registrarCuenta("algo", 10000);
        r1.registrarCuenta("algo", 8000);
        Conversion c1 = new Conversion();
        Cuenta[] ca = c1.convertirListaCuentaEnArreglo(r1.listarCuentas(), 4);
        Ordenamiento o1 = new Ordenamiento();
        o1.ordenarDescendentemente(ca);
        for(int i=0;i<4;i++) {
            System.out.println(ca[i].consultarSaldoActual());
        }
        */
        //TipoCambioBCCR tc = new TipoCambioBCCR();
        //System.out.println(tc.obtenerValorCompra());
        //System.out.println(tc.obtenerValorVenta());
        //ExpresionRegular er = new ExpresionRegular();
        //System.out.println(er.verificarFormatoCorreoElectronicoEsValido("jairocs@estudiantec.cr"));
        //ValidacionTipoDeDato vtd = new ValidacionTipoDeDato();
        //System.out.println(vtd.verificarFechaValida(28, 02, 2020));
        //System.out.println(EnvioCorreoElectronico.enviarCorreo("calderonjairo88@gmail.com", "Prueba de envio de correo", "hola"));
        /*
        int valor = 97;

        char convertedChar = (char)valor;
        System.out.println(convertedChar);
        */
        /*
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(1, 2 + 1);
        System.out.println("Random Number: "+randomNum);
        */
        //System.out.println(PalabraSecreta.generarPalabraSecreta());
        
        /*
        RegistroClientes vista = new RegistroClientes();
        

        ControladorRegistroClientes controladorSala = new ControladorRegistroClientes(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        RegistroCuentasVista vista = new RegistroCuentasVista();
        

        ControladorRegistroCuentas controladorSala = new ControladorRegistroCuentas(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ListaClientes vista = new ListaClientes();
        ControladorListaClientes controladorSala = new ControladorListaClientes(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ConsultaDeEstatusCuenta vista = new ConsultaDeEstatusCuenta();
        ControladorConsultaDeEstatusCuenta controladorSala = new ControladorConsultaDeEstatusCuenta(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ConsultaDeSaldoActual vista = new ConsultaDeSaldoActual();
        ControladorConsultaDeSaldoActual controladorSala = new ControladorConsultaDeSaldoActual(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ConsultaEstadoDeCuenta vista = new ConsultaEstadoDeCuenta();
        ControladorConsultaEstadoDeCuenta controladorSala = new ControladorConsultaEstadoDeCuenta(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ConsultaGananciasCobroComisionesDelBanco vista = new ConsultaGananciasCobroComisionesDelBanco();
        ControladorConsultaGananciasCobroComisionesDelBanco controladorSala = new ControladorConsultaGananciasCobroComisionesDelBanco(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        /*
        ConsultaGananciasCobroComisionesPorCadaCuenta vista = new ConsultaGananciasCobroComisionesPorCadaCuenta();
        ControladorConsultaGananciasCobroComisionesPorCadaCuenta controladorSala = new ControladorConsultaGananciasCobroComisionesPorCadaCuenta(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        /*
        ConsultaTipoCambioDeCompra vista = new ConsultaTipoCambioDeCompra();
        ControladorConsultaTipoCambioDeCompra controladorSala = new ControladorConsultaTipoCambioDeCompra(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        ConsultaTipoCambioDeVenta vista = new ConsultaTipoCambioDeVenta();
        ControladorConsultaTipoCambioDeVenta controladorSala = new ControladorConsultaTipoCambioDeVenta(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        /*
        DepositoEnColones vista = new DepositoEnColones();
        ControladorDepositoEnColones controladorSala = new ControladorDepositoEnColones(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
        
        
        
        /*
        ListaCuentas vista = new ListaCuentas();
        ControladorListaCuentas controladorSala = new ControladorListaCuentas(vista);
        controladorSala.vistaGUI.setVisible(true);
        controladorSala.vistaGUI.setLocationRelativeTo(null);
        */
               
        
        /*
        IDAOCliente c = new DAOCliente();
        c.consultarCliente(1);
*/
        /*
        IDAOCatalogoDeClientes c = new DAOCatalogoDeClientes();
        Lista<ICliente> lc = c.consultarListaDeClientes();
*/
    }
    
}
