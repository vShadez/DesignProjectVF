/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

/**
 *
 * @author Jairo Calderón
 */
public class MenuPrincipalCLI {
    public MenuPrincipalCLI() {
        this.imprimirOpciones();
    }
    
    public void imprimirOpciones() {
        String menuDeOpciones = "";
        menuDeOpciones += "¡BIENVENIDO AL SISTEMA! \n";
        menuDeOpciones += "PULSE 1 para registrar un cliente \n";
        menuDeOpciones += "PULSE 2 para registrar una cuenta \n";
        menuDeOpciones += "PULSE 3 para consultar los datos de un cliente \n";
        menuDeOpciones += "PULSE 4 para consultar los datos de una cuenta \n";
        menuDeOpciones += "PULSE 5 para cambiar el pin de una cuenta \n";
        menuDeOpciones += "PULSE 6 para realizar un depósito en colones \n";
        menuDeOpciones += "PULSE 7 para realizar un depósito en dólares \n";
        menuDeOpciones += "PULSE 8 para realizar un retiro en colones \n";
        menuDeOpciones += "PULSE 9 para realizar un retiro en dólares \n";
        menuDeOpciones += "PULSE 10 para realizar una transferencia \n";
        menuDeOpciones += "PULSE 11 para consultar el tipo de cambio de compra del dólar \n";
        menuDeOpciones += "PULSE 12 para consultar el tipo de cambio de venta del dólar \n";
        menuDeOpciones += "PULSE 13 para consultar el saldo actual \n";
        menuDeOpciones += "PULSE 14 para consultar el saldo actual en dólares \n";
        menuDeOpciones += "PULSE 15 para consultar el estado de cuenta \n";
        menuDeOpciones += "PULSE 16 para consultar el estado de cuenta en dólares \n";
        menuDeOpciones += "PULSE 17 para consultar el estatus de una cuenta \n";
        menuDeOpciones += "PULSE 18 para consultar ganancias totales del banco producto del cobro de comisiones \n";
        menuDeOpciones += "PULSE 19 para Consultar ganancias del banco producto del cobro de comisiones para una cuenta en específico \n";
        menuDeOpciones += "PULSE 20 para salir \n";
        System.out.println(menuDeOpciones);
    }
    
    public void leerOpcionIngresada() throws Exception {
        String opcionIngresada = TextoIngresadoPorElUsuario.solicitarIngresoDeTextoAlUsuario();
        switch(opcionIngresada) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            default:
                System.out.println("La opción ingresada no es válida");
        }
    }
}
