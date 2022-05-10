/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.time.LocalDate;
import serviciosExternos.TipoCambioBCCR;

/**
 *
 * @author Jairo Calderón
 */
public class TipoDeCambioDeCompraCLI {
    public TipoDeCambioDeCompraCLI() {
        mostrarTipoDeCambioDeCompra();
    }
    
    private void mostrarTipoDeCambioDeCompra() {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeCompra = tipoDeCambioDeDolar.obtenerValorCompra();
        String mensaje = "";
        mensaje += "El tipo de cambio del dólar de compra para hoy: " + LocalDate.now().toString() + " es: ";
        mensaje += tipoDeCambioDeCompra;
        System.out.println(mensaje);
    }
}
