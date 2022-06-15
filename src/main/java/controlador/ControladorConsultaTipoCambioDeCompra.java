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
import vistaGUI.ConsultaTipoCambioDeCompra;

/**
 *
 * @author estadm
 */
public class ControladorConsultaTipoCambioDeCompra implements ActionListener{
    public ConsultaTipoCambioDeCompra vistaGUI;
    
    public ControladorConsultaTipoCambioDeCompra(ConsultaTipoCambioDeCompra pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverTipoCambioCompra.addActionListener(this);
        consultarTipoDeCambioCompra();
    }
    
    public void consultarTipoDeCambioCompra(){
        TipoCambioBCCR tc = new TipoCambioBCCR();
        double tipoCompra = tc.obtenerValorCompra();
        
        vistaGUI.txtCambioCompra.setText(""+tipoCompra+" ₡");
        
        ObjetosTipoBitacora accion = ObjetosTipoBitacoraSinglenton.instanciar();
        accion.registrarBitacoraXML(LocalDate.now(), "Consultar tipo cambio compra", "GUI");
        accion.registrarBitacoraCSV(LocalDate.now(), "Consultar tipo cambio compra", "GUI");
        accion.registrarBitacoraTXT(LocalDate.now(), "Consultar tipo cambio compra", "GUI");
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
