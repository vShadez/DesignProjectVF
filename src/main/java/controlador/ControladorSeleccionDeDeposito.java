
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistaGUI.DepositoConTipoDeCambio;
import vistaGUI.DepositoEnColones;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaColones;
import vistaGUI.InformacionPorConsultaDeEstadoCuentaDolares;
import vistaGUI.SeleccionDeDeposito;

/**
 *
 * @author estadm
 */
public class ControladorSeleccionDeDeposito implements ActionListener{
    public SeleccionDeDeposito vistaGUI;
    
    public ControladorSeleccionDeDeposito(SeleccionDeDeposito pVistaGUI){
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnColonesTipoDeposito.addActionListener(this);
        this.vistaGUI.btnDolaresTipoDeposito.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Colones")) {
            DepositoEnColones vistaDepositoEnColones = new DepositoEnColones();
            ControladorDepositoEnColones controladorDepositoEnColones = new ControladorDepositoEnColones(vistaDepositoEnColones);
                    
            controladorDepositoEnColones.vistaGUI.setVisible(true);
            controladorDepositoEnColones.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
        if(evento.getActionCommand().equals("Dólares")) {
            DepositoConTipoDeCambio vistaDepositoConTipoDeCambio = new DepositoConTipoDeCambio();
            ControladorDepositoConTipoDeCambio controladorDepositoConTipoDeCambio = new ControladorDepositoConTipoDeCambio(vistaDepositoConTipoDeCambio);
                    
            controladorDepositoConTipoDeCambio.vistaGUI.setVisible(true);
            controladorDepositoConTipoDeCambio.vistaGUI.setLocationRelativeTo(null);
            vistaGUI.setVisible(false);
        }
}
}
