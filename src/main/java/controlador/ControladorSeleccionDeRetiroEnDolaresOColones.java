/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.SeleccionDeRetiroEnDolaresOColones;
import vistaGUI.SolicitarMontoDeRetiroEnColonesTerceraEtapa;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorSeleccionDeRetiroEnDolaresOColones implements ActionListener{
    public SeleccionDeRetiroEnDolaresOColones vistaGUI;
    private String numeroDeCuenta;
    
    public ControladorSeleccionDeRetiroEnDolaresOColones(SeleccionDeRetiroEnDolaresOColones pVistaGUI, String pNumeroDeCuenta) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI.jButton1.addActionListener(this);
        this.vistaGUI.jButton2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Colones")) {
            SolicitarMontoDeRetiroEnColonesTerceraEtapa vistaSolicitarMontoDeRetiroEnColonesTerceraEtapa = new SolicitarMontoDeRetiroEnColonesTerceraEtapa();
            ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa = new ControladorSolicitarMontoDeRetiroEnColonesTerceraEtapa(vistaSolicitarMontoDeRetiroEnColonesTerceraEtapa, numeroDeCuenta);
            controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa.vistaGUI.setVisible(true);
            controladorSolicitarMontoDeRetiroEnColonesTerceraEtapa.vistaGUI.setLocationRelativeTo(null);
            this.vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Dólares")) {
            
        }
    }
}
