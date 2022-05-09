/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import vistaGUI.ConsultaGananciasCobroComisionesPorCadaCuenta;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.Operacion;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICuenta;
import clasesUtilitarias.Conversion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesPorCadaCuenta implements ActionListener{
    public ConsultaGananciasCobroComisionesPorCadaCuenta vistaGUI;
    
    public ControladorConsultaGananciasCobroComisionesPorCadaCuenta(ConsultaGananciasCobroComisionesPorCadaCuenta pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarMontosCadaCuenta.addActionListener(this);
        this.vistaGUI.btnConsultarMontosCadaCuenta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            
            Lista<ICuenta> listaCuentas;
            Cuenta[] arregloCuentasOrdenadas;
            
            IDAOCatalogoDeCuentas cuentas = new DAOCatalogoDeCuentas();
            int cantidadCuentas = cuentas.consultarCantidadCuentas();
            listaCuentas = cuentas.consultarListaDeCuentas();
            
            IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
            
            arregloCuentasOrdenadas = Conversion.convertirListaCuentaEnArreglo(listaCuentas, cantidadCuentas);
            
            JTable tabla = this.vistaGUI.tblMontosTotalesDeCadaCuenta; 
        
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Cuenta");
            model.addColumn("Total de depósitos y retiros");
            model.addColumn("Total depósitos");
            model.addColumn("Total retiros");
            for (int i = 0; i < cantidadCuentas; i++) {
                String numeroDeCuenta = arregloCuentasOrdenadas[i].numeroCuenta;
                IDAOOperacionCuenta operacionesCenta = new DAOOperacionCuenta();
                double cantidadDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorDepositos(numeroDeCuenta);
                double cantidadRetirosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetiros(numeroDeCuenta);
                double cantidadRetirosDepositosRealizados = operacionesCenta.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(numeroDeCuenta);
                model.addRow(new Object[]{numeroDeCuenta, cantidadRetirosDepositosRealizados, cantidadDepositosRealizados, cantidadRetirosRealizados});
            }
        
            tabla.setModel(model);
            
        }
    }
}
