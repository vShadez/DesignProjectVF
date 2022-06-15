/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import logicaDeNegocios.ObjetosTipoBitacora;
import serviciosExternos.TipoCambioBCCR;
import singlentonLogicaDeNegocios.ObjetosTipoBitacoraSinglenton;
import vistaGUI.ConsultaTipoCambioDeVenta;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeVenta implements ActionListener{
    public ConsultaTipoCambioDeVenta vistaGUI;
    
    public ControladorConsultaTipoCambioDeVenta(ConsultaTipoCambioDeVenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverTipoCambioVenta.addActionListener(this);
        consultarTipoDeCambioVenta();
    }
    
    public void consultarTipoDeCambioVenta(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoVenta = tc.obtenerValorVenta();
        
        vistaGUI.txtCambioVenta.setText(""+tipoVenta+" ₡");
        
        ObjetosTipoBitacora accion = ObjetosTipoBitacoraSinglenton.instanciar();
        accion.registrarBitacoraXML(LocalDate.now(), "Consultar tipo cambio venta", "GUI");
        accion.registrarBitacoraCSV(LocalDate.now(), "Consultar tipo cambio venta", "GUI");
        accion.registrarBitacoraTXT(LocalDate.now(), "Consultar tipo cambio venta", "GUI");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
