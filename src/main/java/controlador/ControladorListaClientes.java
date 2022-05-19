/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import clasesUtilitarias.Conversion;
import clasesUtilitarias.Ordenamiento;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDinamica.Lista;
import vistaGUI.ListaClientes;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import vistaGUI.DetalleCliente;
import java.awt.event.ActionListener;
import vistaGUI.ColorCelda;

/**
 *
 * @author estadm
 */
public class ControladorListaClientes implements ActionListener{
    public ListaClientes  vistaGUI;
    
    public ControladorListaClientes(ListaClientes pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnVolverListaClientes.addActionListener(this);
        iniciarListaClientes();
        this.vistaGUI.tblListaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int filaSeleccionada = vistaGUI.tblListaClientes.getSelectedRow();
            int columnaSeleccionada = vistaGUI.tblListaClientes.getSelectedColumn();
            if (columnaSeleccionada == 3) {
                String clienteSeleccionado = vistaGUI.tblListaClientes.getValueAt(filaSeleccionada, columnaSeleccionada).toString();
                DetalleCliente vistaDetalleCliente = new DetalleCliente();
                ControladorDetalleCliente controladorDetalleCliente = new ControladorDetalleCliente(Conversion.convertirStringEnEntero(clienteSeleccionado), vistaDetalleCliente);
                controladorDetalleCliente.vistaGUI.setVisible(true);
                controladorDetalleCliente.vistaGUI.setLocationRelativeTo(null);
                vistaGUI.setVisible(false);
            }
        }
        
    }
        ); 
    }
    
    public void iniciarListaClientes(){
        Cliente[] arregloClientesDesordenados;
        IDAOCatalogoDeClientes daoCatalogoDeClientes = new DAOCatalogoDeClientes();
        int cantidadDeClientes = daoCatalogoDeClientes.consultarCantidadDeClientes();
        JTable tabla = this.vistaGUI.tblListaClientes; 
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Primer apellido");
        model.addColumn("Segundo apellido");
        model.addColumn("Nombre");
        model.addColumn("Identificación");
        
        Lista<ICliente> consultarListaCliente = daoCatalogoDeClientes.consultarListaDeClientes();
        
        arregloClientesDesordenados = Conversion.convertirListaClienteEnArreglo(consultarListaCliente, cantidadDeClientes);
        Cliente cliente[] = Ordenamiento.ordenarAscendentemente(arregloClientesDesordenados);
      
        for (int i = 0; i < cantidadDeClientes; i++) {
            String primerApellido = cliente[i].primerApellido;
            String segundoApellido = cliente[i].segundoApellido;
            String nombre = cliente[i].nombre;
            int identificacion = cliente[i].identificacion;
            
            model.addRow(new Object[]{primerApellido, segundoApellido,nombre, identificacion});
        }
       
        tabla.setModel(model);
        ColorCelda color = new ColorCelda(3);
        tabla.getColumnModel().getColumn(3).setCellRenderer(color);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
       if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
}
