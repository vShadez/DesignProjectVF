/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import listaDinamica.Lista;
import listaDinamica.Nodo;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;
import logicaDeNegocios.ICuenta;
import serviciosExternos.TipoCambioBCCR;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones {
    public ConsultaDeGananciasParaUnaCuentaPorCobroDeComisiones() {
        mostrarGananciasTotalesDeBancoPorCobroDeComisiones();
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones() {
        IDAOCatalogoDeCuentas daoCuentas = new DAOCatalogoDeCuentas();
        Lista<ICuenta> listaDeCuentas = daoCuentas.consultarListaDeCuentas();
        Nodo puntero = listaDeCuentas.inicio;
        while(puntero != null) {
            Cuenta cuenta = (Cuenta) puntero.objeto;
            String numeroDeCuenta = cuenta.numeroCuenta;
            System.out.println("\nGanancias del Banco por el cobro de comisiones a la cuenta: " + numeroDeCuenta);
            System.out.println("\nResultados en colones: ");
            this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(numeroDeCuenta);
            System.out.println("\nResultados en dólares: ");
            this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(numeroDeCuenta);
        }
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(String pNumeroDeCuenta) {
        IDAOOperacionCuenta comisionesCobradas = new DAOOperacionCuenta();
        double comisionesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos(pNumeroDeCuenta);
        double comisionesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros(pNumeroDeCuenta);
        double comisionesDepositosYRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(pNumeroDeCuenta);
        System.out.println("Ganancias por depositos: " + comisionesDepositos);
        System.out.println("Ganancias por retiros: " + comisionesRetiros);
        System.out.println("Ganancias por depositos y retiros: " + comisionesDepositosYRetiros);
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(String pNumeroDeCuenta) {
        IDAOOperacionCuenta comisionesCobradas = new DAOOperacionCuenta();
        double comisionesDepositos = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos(pNumeroDeCuenta);
        double comisionesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros(pNumeroDeCuenta);
        double comisionesDepositosYRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos(pNumeroDeCuenta);
        TipoCambioBCCR tipoDeCambio = new TipoCambioBCCR();
        double valorDeCompraDelDolar = tipoDeCambio.obtenerValorCompra();
        System.out.println("Ganancias por depositos en dolares: " + (comisionesDepositos * valorDeCompraDelDolar));
        System.out.println("Ganancias por retiros en dolares: " + (comisionesRetiros * valorDeCompraDelDolar));
        System.out.println("Ganancias por depositos y retiros en dolares: " + (comisionesDepositosYRetiros * valorDeCompraDelDolar));
    }
}
