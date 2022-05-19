/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.text.DecimalFormat;
import java.time.LocalDate;
import serviciosExternos.TipoCambioBCCR;

/**
 *
 * @author Jairo Calderón
 */
public class TipoDeCambioDeCompraCLI {
    public TipoDeCambioDeCompraCLI() throws Exception {
        mostrarTipoDeCambioDeCompra();
    }
    
    private void mostrarTipoDeCambioDeCompra() throws Exception {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeCompra = tipoDeCambioDeDolar.obtenerValorCompra();
        String mensaje = "";
        mensaje += "El tipo de cambio del dólar de compra para hoy: " + LocalDate.now().toString() + " es: ";
        DecimalFormat formatoDeNumeroDecimal = new DecimalFormat("#.00");
        mensaje += formatoDeNumeroDecimal.format(tipoDeCambioDeCompra);
        System.out.println(mensaje);
        MenuPrincipalCLI volverAMenuPrincipal = new MenuPrincipalCLI();
    }
}
