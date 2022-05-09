/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaDeNegocios;

/**
 *
 * @author Jairo Calderón
 */
public interface ICuenta {
    public abstract void cambiarPin(String pNuevoPin);
    public abstract void registrarOperacion(String pTipoOperacion, boolean pSeAplicoComision, double pMontoComision);
    public abstract void asignarPropietario(ICliente pPropietario);
    public abstract void depositar(double pMontoDepositado);
    public abstract void retirar(double pMonto);
    public abstract void transferir(ICuenta pCuentaDestino, double pMontoTransferido);
    public abstract void recibirTransferencia(double pMontoTransferido);
    public abstract double calcularMontoComision(double pMontoOperacion);
    public abstract void aplicarCobroComision(double pMontoComision);
}