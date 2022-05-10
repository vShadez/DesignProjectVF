/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author estadm
 */
public class ControladorMenuPrincipal implements ActionListener{
    
    public MenuPrincipal vistaGUI;
    
    
    public ControladorMenuPrincipal(MenuPrincipal pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        //this.vistaGUI.btnCancelarConsultaComision.addActionListener(this);
        //this.vistaGUI.btnConsultarMontosPorCobroComision.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
