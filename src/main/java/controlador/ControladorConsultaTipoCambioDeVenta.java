/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import serviciosExternos.TipoCambioBCCR;
import vistaGUI.ConsultaTipoCambioDeVenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeVenta {
    public ConsultaTipoCambioDeVenta vistaGUI;
    
    public ControladorConsultaTipoCambioDeVenta(ConsultaTipoCambioDeVenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        consultarTipoDeCambioVenta();
    }
    
    public void consultarTipoDeCambioVenta(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoVenta = tc.obtenerValorVenta();
        
        vistaGUI.txtCambioVenta.setText(""+tipoVenta+" ₡");
    }
}
