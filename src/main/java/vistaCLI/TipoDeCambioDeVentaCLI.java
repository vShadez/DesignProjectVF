/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import serviciosExternos.TipoCambioBCCR;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSinglenton;

/**
 *
 * @author Jairo Calderón
 */
public class TipoDeCambioDeVentaCLI {
    public TipoDeCambioDeVentaCLI() throws Exception {
        mostrarTipoDeCambioDeVenta();
    }
    
    private void mostrarTipoDeCambioDeVenta() throws Exception {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeVenta = tipoDeCambioDeDolar.obtenerValorVenta();
        String mensaje = "";
        mensaje += "El tipo de cambio del dólar de venta para hoy: " + LocalDate.now().toString() + " es: ";
        mensaje += tipoDeCambioDeVenta;
        System.out.println(mensaje);
        RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSinglenton.instanciar();
        accion.registrarEnBitacoras(LocalDate.now(), "Tipo de cambio de venta", "CLI");
        
        MenuPrincipalCLI volverAMenuPrincipal = new MenuPrincipalCLI();
    }
}
