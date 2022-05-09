/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import vistaGUI.DetalleCuenta;
import vistaGUI.ListaCuentas;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorListaCuentas{
    public ListaCuentas vistaGUI;
    private DefaultTableModel modeloDeTabla;
    
    public ControladorListaCuentas(ListaCuentas pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.cargarCuentasDeTabla();
        this.vistaGUI.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            
        @Override
        public void mouseClicked(MouseEvent evento) {
            if(evento.getComponent() == vistaGUI.jTable1) {
                int numeroDeFilaSeleccionada = vistaGUI.jTable1.getSelectedRow();
                int numeroDeColumnaSeleccionada = vistaGUI.jTable1.getSelectedColumn();
                if(numeroDeColumnaSeleccionada == 1) {
                    String numeroDeCuentaSeleccionada = vistaGUI.jTable1.getValueAt(numeroDeFilaSeleccionada, numeroDeColumnaSeleccionada).toString();
                    DetalleCuenta vistaDetalleCuenta = new DetalleCuenta();
                    ControladorDetalleCuenta controladorDetalleCuenta = new ControladorDetalleCuenta(numeroDeCuentaSeleccionada, vistaDetalleCuenta);
                    controladorDetalleCuenta.vistaGUI.setVisible(true);
                    controladorDetalleCuenta.vistaGUI.setLocationRelativeTo(null);
                    vistaGUI.setVisible(false);
                }
            }
        }
        });
    }
    
    public void cargarCuentasDeTabla() {
        this.modeloDeTabla = (DefaultTableModel) this.vistaGUI.jTable1.getModel();
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentasDesordenada = daoCatalogoDeCuentas.consultarListaDeCuentas();
        Cuenta[] listaDeCuentasOrdenada = Conversion.convertirListaCuentaEnArreglo(listaDeCuentasDesordenada, listaDeCuentasDesordenada.size);
        for(int i = 0; i < listaDeCuentasDesordenada.size; i++) {
            Cuenta cuenta = listaDeCuentasOrdenada[i];
            Cliente duenoDeCuenta = (Cliente) cuenta.propietario;
            String nombreCompletoDePropietarioDeCuenta = duenoDeCuenta.nombre + duenoDeCuenta.primerApellido + duenoDeCuenta.segundoApellido;
            int identificacionDePropietarioDeCuenta = duenoDeCuenta.identificacion;
            this.modeloDeTabla.addRow(new Object[]{cuenta.numeroCuenta,cuenta.getSaldo(),cuenta.estatus, nombreCompletoDePropietarioDeCuenta,identificacionDePropietarioDeCuenta});
        }
        this.vistaGUI.jTable1.setModel(this.modeloDeTabla);
    }
}
