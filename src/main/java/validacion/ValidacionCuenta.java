/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacion;

import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOOperacionCuenta;
import logicaDeNegocios.Cuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ValidacionCuenta {
    public static boolean validarExisteCuenta(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        Cuenta cuentaEncontrada = (Cuenta) daoCuenta.consultarCuenta(pNumeroDeCuenta);
        return cuentaEncontrada != null;
    }
    
    public static boolean validarPinCorrespondeACuenta(String pNumeroDeCuenta, String pPin) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        return daoCuenta.verificarPinCorrespondeACuenta(pNumeroDeCuenta, pPin);
    }
    
    public static boolean validarHayFondosSufientes(String pNumeroDeCuenta, double pMontoRetiro) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        IDAOOperacionCuenta daoOperacionCuenta = new DAOOperacionCuenta();
        int cantidadDeRetirosYDepositosRealizados = daoOperacionCuenta.consultarCantidadDeDepositosYRetirosRealizados(pNumeroDeCuenta);
        double montoComision = 0.0;
        if(cantidadDeRetirosYDepositosRealizados >= 3) {
            montoComision += pMontoRetiro * 0.02;
        }
        boolean hayFondosSuficientes = daoCuenta.verificarHayFondosSuficientes(pNumeroDeCuenta, pMontoRetiro + montoComision);
        return hayFondosSuficientes;
    }
    
    public static boolean validarFormatoDeMontoDeRetiroODeposito(String pMontoRetiroODeposito) {
        if(validacion.ExpresionRegular.verificarEsNumero(pMontoRetiroODeposito)) {
            boolean montoDeRetiroEsUnNumeroEntero = validacion.ValidacionTipoDeDato.verificarEsEntero(pMontoRetiroODeposito);
            if(montoDeRetiroEsUnNumeroEntero) {
                int montoRetiro = clasesUtilitarias.Conversion.convertirStringEnEntero(pMontoRetiroODeposito);
                boolean montoDeRetiroEsUnNumeroPositivo = validacion.ValidacionTipoDeDato.esPositivo(montoRetiro);
                if(montoDeRetiroEsUnNumeroPositivo) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean validarCuentaEstaActiva(String pNumeroDeCuenta) {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        String estatusDeCuenta = daoCuenta.consultarEstatusCuenta(pNumeroDeCuenta);
        return estatusDeCuenta.equals("Activa");
    }
    
    public static boolean validarFormatoDePin(String pPin) {
        boolean pinEstaCompuestoPorSeisCaracteres = ExpresionRegular.verificarContieneSeisCaracteresSinEspacios(pPin);
        if(pinEstaCompuestoPorSeisCaracteres) {
            boolean pinContieneUnaLetraMayuscula = ExpresionRegular.verificarContieneLetraMayuscula(pPin);
            if(pinContieneUnaLetraMayuscula) {
                boolean pinContieneUnNumero = ExpresionRegular.verificarContieneNumero(pPin);
                if(pinContieneUnNumero) {
                    boolean pinContieneUnCaracterEspecial = ExpresionRegular.verificarContieneCaracterEspecial(pPin);
                    if(pinContieneUnCaracterEspecial) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
