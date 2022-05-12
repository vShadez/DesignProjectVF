/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import vistaGUI.ColorCelda;
import vistaGUI.DetalleCuenta;
import vistaGUI.ListaCuentas;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorListaCuentas implements ActionListener{
    public ListaCuentas vistaGUI;
    private DefaultTableModel modeloDeTabla;
    
    public ControladorListaCuentas(ListaCuentas pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverListaCuentas.addActionListener(this);
        this.cargarCuentasDeTabla();
        this.vistaGUI.tblListaCuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            
        @Override
        public void mouseClicked(MouseEvent evento) {
            if(evento.getComponent() == vistaGUI.tblListaCuentas) {
                int numeroDeFilaSeleccionada = vistaGUI.tblListaCuentas.getSelectedRow();
                int numeroDeColumnaSeleccionada = vistaGUI.tblListaCuentas.getSelectedColumn();
                if(numeroDeColumnaSeleccionada == 0) {
                    String numeroDeCuentaSeleccionada = vistaGUI.tblListaCuentas.getValueAt(numeroDeFilaSeleccionada, numeroDeColumnaSeleccionada).toString();
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
        JTable tablaCuentas = this.vistaGUI.tblListaCuentas; 
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Número de cuenta");
        modelo.addColumn("Saldo");
        modelo.addColumn("Estatus");
        modelo.addColumn("Nombre completo");
        modelo.addColumn("Identificación");
        
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentasDesordenada = daoCatalogoDeCuentas.consultarListaDeCuentas();
        int tamanoListaCuenta = daoCatalogoDeCuentas.consultarCantidadCuentas();
        System.err.println(tamanoListaCuenta);
        Cuenta[] listaDeCuentasOrdenada = Conversion.convertirListaCuentaEnArreglo(listaDeCuentasDesordenada, tamanoListaCuenta);
        for(int i = 0; i < tamanoListaCuenta; i++) {
            Cuenta cuenta = listaDeCuentasOrdenada[i];
            Cliente duenoDeCuenta = (Cliente) cuenta.propietario;
            String nombreCompletoDePropietarioDeCuenta = duenoDeCuenta.nombre +" "+ duenoDeCuenta.primerApellido +" "+ duenoDeCuenta.segundoApellido;
            int identificacionDePropietarioDeCuenta = duenoDeCuenta.identificacion;
            modelo.addRow(new Object[]{cuenta.numeroCuenta,cuenta.getSaldo(),cuenta.estatus, nombreCompletoDePropietarioDeCuenta,identificacionDePropietarioDeCuenta});
        }
        tablaCuentas.setModel(modelo);
        ColorCelda color = new ColorCelda(0);
        tablaCuentas.getColumnModel().getColumn(0).setCellRenderer(color);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
