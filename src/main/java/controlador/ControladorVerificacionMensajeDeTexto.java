/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import vistaGUI.VerificacionMensajeDeTexto;
import serviciosExternos.EnvioMensajeDeTexto;
import clasesUtilitarias.PalabraSecreta;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import vistaGUI.SeleccionDeRetiroEnDolaresOColones;
import vistaGUI.SolicitarMontoDepositoYCuentaDestinoDeTransferencia;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorVerificacionMensajeDeTexto implements ActionListener{
    public VerificacionMensajeDeTexto vistaGUI;
    private String numeroDeCuenta;
    private int cantidadDeIntentos = 0;
    private int numeroDeTelefono;
    private String mensajeSecreto;
    private String transaccionAsociada;
    
    public ControladorVerificacionMensajeDeTexto(VerificacionMensajeDeTexto pVistaGUI, String pNumeroDeCuenta, String pTransaccionAsociada) {
        this.vistaGUI = pVistaGUI;
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.transaccionAsociada = pTransaccionAsociada;
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        Cliente clienteAsociadoACuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroDeCuenta);
        this.numeroDeTelefono = clienteAsociadoACuenta.numeroTelefono;
        this.enviarMensaje();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Confirmar")) {
            this.cantidadDeIntentos++;
            String palabraIngresada = this.vistaGUI.txtMensajeDeValidacion.getText();
            if(this.mensajeSecreto.equals(palabraIngresada)) {
                if(this.transaccionAsociada.equals("Retiro")) {
                    SeleccionDeRetiroEnDolaresOColones vistaSeleccionDeRetiroEnDolaresOColones = new SeleccionDeRetiroEnDolaresOColones();
                    ControladorSeleccionDeRetiroEnDolaresOColones controladorSeleccionDeRetiroEnDolaresOColones = new ControladorSeleccionDeRetiroEnDolaresOColones(vistaSeleccionDeRetiroEnDolaresOColones, numeroDeCuenta);
                    controladorSeleccionDeRetiroEnDolaresOColones.vistaGUI.setVisible(true);
                    controladorSeleccionDeRetiroEnDolaresOColones.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
                }
                if(this.transaccionAsociada.equals("Transferencia")) {
                    SolicitarMontoDepositoYCuentaDestinoDeTransferencia vistaSolicitarMontoDepositoYCuentaDestinoDeTransferencia = new SolicitarMontoDepositoYCuentaDestinoDeTransferencia();
                    ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia controladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia = new ControladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia(vistaSolicitarMontoDepositoYCuentaDestinoDeTransferencia, numeroDeCuenta);
                    controladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia.vistaGUI.setVisible(true);
                    controladorSolicitarMontoDepositoYCuentaDestinoDeTransferencia.vistaGUI.setLocationRelativeTo(null);
                    this.vistaGUI.setVisible(false);
                }
                this.vistaGUI.setVisible(false);
            }
            else {
                if(this.cantidadDeIntentos == 1) {
                    MensajeEnPantallaCuenta.imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta();
                    this.enviarMensaje();
                }
                else {
                    // inactivar cuenta
                    this.inactivarCuenta();
                }
            }
        }
    }
    
    private void enviarMensaje() {
        EnvioMensajeDeTexto mensajeDeTexto = new EnvioMensajeDeTexto();
        this.mensajeSecreto = PalabraSecreta.generarPalabraSecreta();
        String mensaje = "Estimado usuario de la cuenta: " + this.numeroDeCuenta + " su palabra secreta es: \n";
        mensaje += this.mensajeSecreto + "\n";
        mensaje += "Ingrese esta palabra correctamente para proceder con su retiro";
        mensajeDeTexto.enviarMensaje(String.valueOf(this.numeroDeTelefono), mensaje);
    }
    
    private void inactivarCuenta() {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        daoCuenta.actualizarEstatus(this.numeroDeCuenta, "Inactiva");
        MensajeEnPantallaCuenta.imprimirMensajeAlertaDeInactivacionDeCuenta();
    }
}
