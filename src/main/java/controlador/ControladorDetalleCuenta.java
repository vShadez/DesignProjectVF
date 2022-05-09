/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import vistaGUI.DetalleCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorDetalleCuenta {
    private String numeroDeCuenta;
    public DetalleCuenta vistaGUI;
    
    public ControladorDetalleCuenta(String pNumeroDeCuenta, DetalleCuenta pVistaGUI) {
        this.numeroDeCuenta = pNumeroDeCuenta;
        this.vistaGUI = pVistaGUI;
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuenta = (Cuenta) daoCuenta.consultarCuenta(this.numeroDeCuenta);
        Cliente cliente = (Cliente) cuenta.propietario;
        String nombreDePropietario = cliente.nombre + cliente.primerApellido + cliente.segundoApellido;
        this.vistaGUI.txtNumeroCuenta.setText(cuenta.numeroCuenta);
        this.vistaGUI.txtFechaCreacion.setText(cuenta.fechaCreacion.toString());
        this.vistaGUI.txtSaldoCuenta.setText(String.valueOf(cuenta.getSaldo()));
        this.vistaGUI.txtPin.setText(cuenta.getPin());
        this.vistaGUI.txtNombrePropietario.setText(nombreDePropietario);
    }
}
