/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCliente;
import logicaDeAccesoADatos.DAOClienteCuenta;
import logicaDeAccesoADatos.IDAOCliente;
import logicaDeAccesoADatos.IDAOClienteCuenta;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import vistaGUI.DetalleCliente;

/**
 *
 * @author estadm
 */
public class ControladorDetalleCliente {
    public DetalleCliente vistaGUI;
    private int identificacionCliente;
    
    public ControladorDetalleCliente(int pIdentificacionCliente, DetalleCliente pDetalleCliente){
        this.vistaGUI = pDetalleCliente;
        Cuenta[] arregloDeCuentasDeCliente;
        this.identificacionCliente = pIdentificacionCliente;
        
        
        IDAOCliente daoCliente = new DAOCliente();
        Cliente cliente = (Cliente) daoCliente.consultarCliente(this.identificacionCliente);
        
        IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
        int cantidadCuentasCliente = daoClienteCuenta.consultarCantidadDeCuentasDeCliente(pIdentificacionCliente);
        Lista<ICuenta> consultarListaCuenta = daoClienteCuenta.consultarCuentasDeCliente(pIdentificacionCliente);
        
        arregloDeCuentasDeCliente = Conversion.convertirListaCuentaEnArreglo(consultarListaCuenta, cantidadCuentasCliente);
        this.vistaGUI.txtCodigo.setText(cliente.getCodigo());
        int identificacionConvertidaString = cliente.identificacion;
        this.vistaGUI.txtIdentificacion.setText(""+identificacionConvertidaString);
        this.vistaGUI.txtPrimerApellido.setText(cliente.primerApellido);
        this.vistaGUI.txtSegundoApellido.setText(cliente.segundoApellido);
        this.vistaGUI.txtNombre.setText(cliente.nombre);
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        String fechaNacimientoConvertidaString = fechaNacimiento.toString();
        this.vistaGUI.txtFechaNacimiento.setText(fechaNacimientoConvertidaString);
        this.vistaGUI.txtCorreo.setText(cliente.correoElectronico);
        int telefonoConvertidoString = cliente.numeroTelefono;
        this.vistaGUI.txtTelefono.setText(""+telefonoConvertidoString);
        
        cargarCuentasATabla(arregloDeCuentasDeCliente, cantidadCuentasCliente);
        
    }
    
    public void cargarCuentasATabla(Cuenta cuentas[], int pCantidadCuentas){
        JTable tabla = this.vistaGUI.tblCuentasAsociadas; 
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Números de cuentas asociadas");
        for (int i = 0; i < pCantidadCuentas; i++) {
            String numeroDeCuenta = cuentas[i].numeroCuenta;
            
            model.addRow(new Object[]{numeroDeCuenta});
        }
        
        tabla.setModel(model);
    }
}
