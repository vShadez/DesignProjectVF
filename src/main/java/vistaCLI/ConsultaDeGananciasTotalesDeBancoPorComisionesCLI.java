/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import serviciosExternos.TipoCambioBCCR;
import vistaGUI.MenuPrincipal;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeGananciasTotalesDeBancoPorComisionesCLI {
    public ConsultaDeGananciasTotalesDeBancoPorComisionesCLI() {
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisiones();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones() {
        System.out.println("Ganancias totales del Banco por el cobro de comisiones ");
        System.out.println("\nResultados en colones: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones();
        System.out.println("\nResultados en dólares: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares();
        MenuPrincipal volverAMenuPrincipal = new MenuPrincipal();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones() {
        IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
        double comisionesTotalesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
        double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
        double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
        System.out.println("Ganancias por depositos: " + comisionesTotalesDepositos);
        System.out.println("Ganancias por retiros: " + comisionesTotalesRetiros);
        System.out.println("Ganancias por depositos y retiros: " + comisionesTotalesDepositoRetiros);
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares() {
        IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
        double comisionesTotalesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
        double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
        double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
        TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
        double valorDeCompraDelDolar = tipoDeCambio.obtenerValorCompra();
        System.out.println("Ganancias por depositos: " + (comisionesTotalesDepositos * valorDeCompraDelDolar));
        System.out.println("Ganancias por retiros: " + (comisionesTotalesRetiros * valorDeCompraDelDolar));
        System.out.println("Ganancias por depositos y retiros: " + (comisionesTotalesDepositoRetiros * valorDeCompraDelDolar));
    }
}
