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
public class MostrarTipoDeCambioDeVentaCLI {
    public MostrarTipoDeCambioDeVentaCLI() {
        mostrarTipoDeCambioDeVenta();
    }
    
    private void mostrarTipoDeCambioDeVenta() {
        TipoCambioBCCR tipoDeCambioDeDolar = new TipoCambioBCCR();
        double tipoDeCambioDeVenta = tipoDeCambioDeDolar.obtenerValorVenta();
        String mensaje = "";
        mensaje += "El tipo de cambio del dólar de venta para hoy: " + LocalDate.now().toString() + " es: ";
        mensaje += tipoDeCambioDeVenta;
        System.out.println(mensaje);
    }
}
