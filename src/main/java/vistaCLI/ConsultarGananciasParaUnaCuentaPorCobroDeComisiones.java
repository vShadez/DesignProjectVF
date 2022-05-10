/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import serviciosExternos.TipoCambioBCCR;
import validacion.ValidacionCuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultarGananciasParaUnaCuentaPorCobroDeComisiones {
    public ConsultarGananciasParaUnaCuentaPorCobroDeComisiones() {
        recibirNumeroDeCuenta();
    }
    
    private void recibirNumeroDeCuenta() {
        try {
            System.out.println("Ingrese el número de cuenta");
            String numeroDeCuenta = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
            boolean numeroDeCuentaEsValido = this.validarNumeroDeCuenta(numeroDeCuenta);
            if(numeroDeCuentaEsValido) {
                this.mostrarGananciasTotalesDeBancoPorCobroDeComisiones(numeroDeCuenta);
            }
        } 
        catch (Exception ex) {
            System.out.println("Ha ocurrido un error al recibir el texto");
        }
    }
    
    private boolean validarNumeroDeCuenta(String pNumeroDeCuenta) {
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(pNumeroDeCuenta);
        if(existeCuenta) {
            return true;
        }
        else {
            System.out.println(MensajeEnConsolaCuenta.imprimirMensajeDeErrorCuentaNoExiste(pNumeroDeCuenta));
            return false;
        }
    }
    
    private void mostrarGananciasTotalesDeBancoPorCobroDeComisiones(String pNumeroDeCuenta) {
        System.out.println("Ganancias del Banco por el cobro de comisiones a la cuenta: " + pNumeroDeCuenta);
        System.out.println("\nResultados en colones: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnColones(pNumeroDeCuenta);
        System.out.println("\nResultados en dólares: ");
        this.mostrarGananciasTotalesDeBancoPorCobroDeComisionesEnDolares(pNumeroDeCuenta);
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
        System.out.println("Ganancias por depositos: " + (comisionesDepositos * valorDeCompraDelDolar));
        System.out.println("Ganancias por retiros: " + (comisionesRetiros * valorDeCompraDelDolar));
        System.out.println("Ganancias por depositos y retiros: " + (comisionesDepositosYRetiros * valorDeCompraDelDolar));
    }
}
