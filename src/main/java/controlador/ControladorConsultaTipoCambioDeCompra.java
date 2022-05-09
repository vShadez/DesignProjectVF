/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import serviciosExternos.TipoCambioBCCR;
import vistaGUI.ConsultaTipoCambioDeCompra;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeCompra {
    public ConsultaTipoCambioDeCompra vistaGUI;
    
    public ControladorConsultaTipoCambioDeCompra(ConsultaTipoCambioDeCompra pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        consultarTipoDeCambioCompra();
    }
    
    public void consultarTipoDeCambioCompra(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        
        vistaGUI.txtCambioCompra.setText(""+tipoCompra+" ₡");
    }
}
