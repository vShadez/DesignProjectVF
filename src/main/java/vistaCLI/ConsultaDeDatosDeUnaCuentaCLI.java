/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import clasesUtilitarias.Conversion;
import listaDinamica.Lista;
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICuenta;
import logicaDeNegocios.Cuenta;

/**
 *
 * @author Jairo Calderón
 */
public class ConsultaDeDatosDeUnaCuentaCLI {
    public void cargarCuentasRegistradas() {
        Cuenta[] arregloCuentasOrdenadas;
        IDAOCatalogoDeCuentas daoCatalogoDeCuentas = new DAOCatalogoDeCuentas();
        int cantidadDeCuentas = daoCatalogoDeCuentas.consultarCantidadCuentas();
        Lista<ICuenta> consultarListaDeCuentas = daoCatalogoDeCuentas.consultarListaDeCuentas();
        arregloCuentasOrdenadas = Conversion.convertirListaCuentaEnArreglo(consultarListaDeCuentas, cantidadDeCuentas);
        Cuenta cuenta[] = arregloCuentasOrdenadas;
        System.out.println("Lista de clientes registrados en el sistema:");
        for (int i = 0; i < cantidadDeCuentas; i++) {
            String numeroDeCuenta = cuenta[i].numeroCuenta;
            String estatus = cuenta[i].estatus;
            double saldo = cuenta[i].getSaldo();
            System.out.println("\nCliente n°" + i+1 + ":");
            System.out.println("Número de cuenta: " + numeroDeCuenta);
            System.out.println("Estatus: " + estatus);
            System.out.println("Saldo: " + saldo);
            Cliente duenoDeCuenta = (Cliente) cuenta[i].propietario;
            System.out.println("Identificacion del dueño de la cuenta: " + duenoDeCuenta.identificacion);
            System.out.println("Identificacion del dueño de la cuenta: " + duenoDeCuenta.nombre + duenoDeCuenta.primerApellido + duenoDeCuenta.segundoApellido);
        }
        System.out.println("\nDigite el número de cuenta de la cuenta sobre la cual desea conocer los detalles:");
        //this.recibirIdentificacionDeCliente();
    }
}
