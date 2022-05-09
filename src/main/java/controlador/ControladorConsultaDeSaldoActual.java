/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import serviciosExternos.TipoCambioBCCR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import validacion.ValidacionCuenta;
import vistaGUI.ConsultaDeSaldoActual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import clasesUtilitarias.Conversion;
/**
 *
 * @author estadm
 */
public class ControladorConsultaDeSaldoActual implements ActionListener{
    public ConsultaDeSaldoActual vistaGUI;
    
    public ControladorConsultaDeSaldoActual(ConsultaDeSaldoActual pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnConsultarColones.addActionListener(this);
        this.vistaGUI.btnConsultarDolares.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String numeroDeCuenta = this.vistaGUI.txtNumeroCuentaConsultaSaldo.getText();
        String pin = this.vistaGUI.txtPinCuentaConsultaSaldo.getText();
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
        if(existeCuenta) {
            boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
            if(pinCorrespondeACuenta) {
                if(evento.getActionCommand().equals("Consultar colones")) {
                    IDAOCuentaIndividual cuentaAconsultarColones = new DAOCuentaIndividual();
                    double saldoActualColones = cuentaAconsultarColones.consultarSaldoActual(numeroDeCuenta);
                    MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualColones(""+saldoActualColones);
                }
                if(evento.getActionCommand().equals("Consultar dólares")) {
                    TipoCambioBCCR tc = new TipoCambioBCCR();
                    IDAOCuentaIndividual cuentaAconsultarDolares = new DAOCuentaIndividual();
                    double saldoActualColones = cuentaAconsultarDolares.consultarSaldoActual(numeroDeCuenta);
                    double valorDeCompra = tc.obtenerValorCompra();
                    double saldoConvertidoADolares = saldoActualColones / valorDeCompra;
                    MensajeEnPantallaCuenta.imprimirMensajeSaldoCuentaActualDolares(saldoConvertidoADolares, valorDeCompra);
                }
            } else {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
              }
        } else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
          }
        
    }
    
    
}
