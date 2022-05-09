/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validacion;

import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;

/**
 *
 * @author Jairo Calderón
 */
public class ValidacionCliente {
    public static boolean validarFechaNacimiento(String pFechaPorValidar) {
        boolean formatoDeFechaDeNacimientoEsCorrecto = ExpresionRegular.verificarFormatoDeFecha(pFechaPorValidar);
        if(formatoDeFechaDeNacimientoEsCorrecto) {
            String[] partesDeString = pFechaPorValidar.split("/");
            int dia = Conversion.convertirStringEnEntero(partesDeString[0]);
            int mes = Conversion.convertirStringEnEntero(partesDeString[1]);
            int ano = Conversion.convertirStringEnEntero(partesDeString[2]);
            boolean existeFecha = ValidacionTipoDeDato.verificarFechaValida(dia, mes, ano);
            if(existeFecha) {
                LocalDate fecha = Conversion.convertirStringEnLocalDate(pFechaPorValidar);
                if(fecha.isBefore(LocalDate.now()) || fecha.equals(LocalDate.now())) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    
    public static boolean existeCliente(int pIdentificacion) {
        IDAOCatalogoDeClientes daoClientes = new DAOCatalogoDeClientes();
        return daoClientes.consultarSiExisteCliente(pIdentificacion);
    }
    
    public static boolean verificarFormatoDeCorreoElectronico(String pCorreoPorVerificar) {
        return ExpresionRegular.verificarFormatoCorreoElectronicoEsValido(pCorreoPorVerificar);
    }
    
    public static boolean verificarFormatoDeNumeroDeTelefono(String pNumeroDeTelefonoPorVerificar) {
        return ExpresionRegular.verificarFormatoNumeroTelefonicoEsValido(pNumeroDeTelefonoPorVerificar);
    }
}
