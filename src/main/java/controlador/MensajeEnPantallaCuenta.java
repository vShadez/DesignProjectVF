/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author estadm
 */
public class MensajeEnPantallaCuenta {
    public static void imprimirMensajeRegistroExitoso(String pNumeroCuenta, String pEstatus, String pSaldoActual, String pNombrePropietario, String pPrimerApellido, String pSegundoApellido, int pTelefonoAsociadoCliente, String pCorreoAsociadoCliente) {
        String mensajeRegistroExitoso = "";
        mensajeRegistroExitoso += "Se ha creado una nueva cuenta en el sistema, los datos de la cuenta son: \n\n";
        mensajeRegistroExitoso += "Numero de cuenta: " + pNumeroCuenta + "\n";
        mensajeRegistroExitoso += "Estatus de la cuenta: " + pEstatus + "\n";
        mensajeRegistroExitoso += "Saldo actual: " + pSaldoActual + "\n";
        mensajeRegistroExitoso += "-- \n";
        mensajeRegistroExitoso += "Nombre del dueno de la cuenta: " + pNombrePropietario+" "+ pPrimerApellido +" "+ pSegundoApellido + "\n";
        mensajeRegistroExitoso += "Número de teléfono “asociado” a la cuenta: " + pTelefonoAsociadoCliente + "\n";
        mensajeRegistroExitoso += "Dirección de correo electrónico “asociada” a la cuenta: " + pCorreoAsociadoCliente;
        JOptionPane.showMessageDialog(null, mensajeRegistroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeCambioDePinExitoso(String pNumeroDeCuenta) {
        String mensajeCambioDePinExitoso = "Se ha modificado exitosamente el pin de la cuenta: " + pNumeroDeCuenta;
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeDepositoEnColonesExitoso(String pNumeroDeCuenta, int pMontoDepositado, double pMontoComision) {
        String mensajeDepositoExitoso = "";
        mensajeDepositoExitoso += "Estimado usuario, se han depositado correctamente " + pMontoDepositado + " colones \n";
        mensajeDepositoExitoso += "[El monto real depositado a su cuenta" + pNumeroDeCuenta + "es de " + (pMontoDepositado - pMontoComision) + " colones] \n";
        mensajeDepositoExitoso += "[El monto cobrado por concepto de comisión fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual]";
        JOptionPane.showMessageDialog(null, mensajeDepositoExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeDepositoEnDolaresExitoso(String pNumeroDeCuenta,double pMontoDepositadoDolares, double pMontoDepositadoColones, double pTipoCambio, double pMontoComision, LocalDate pFechaDeposito) {
        String mensajeDepositoExitoso = "";
        mensajeDepositoExitoso += "Estimado usuario, se han recibido correctamente " + pMontoDepositadoDolares + " dolares \n";
        mensajeDepositoExitoso += "[Según el BCCR, el tipo de cambio de compra del dólar de " + pFechaDeposito + " es: " + pTipoCambio + "] \n";
        mensajeDepositoExitoso += "[El monto equivalente en colones es " + pMontoDepositadoColones + "] \n";
        mensajeDepositoExitoso += "[El monto real depositado a su cuenta " + pNumeroDeCuenta + " es de " + (pMontoDepositadoColones - pMontoComision) + " colones] \n";
        mensajeDepositoExitoso += "[El monto cobrado por concepto de comisión fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual]";
        JOptionPane.showMessageDialog(null, mensajeDepositoExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeRetiroEnColonesExitoso(double pMontoRetirado, double pMontoComision) {
        String mensajeRetiroExitoso = "";
        mensajeRetiroExitoso += "Estimado usuario, el monto de este retiro es " + pMontoRetirado + " colones \n";
        mensajeRetiroExitoso += "[El monto cobrado por concepto de comisión fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual]";
        JOptionPane.showMessageDialog(null, mensajeRetiroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeRetiroEnDolaresExitoso(double pMontoRetiradoColones, double pMontoRetiradoDolares, double pTipoCambio, double pMontoComision) {
        String mensajeRetiroExitoso = "";
        mensajeRetiroExitoso += "Estimado usuario, el monto de este retiro es " + pMontoRetiradoDolares + " dolares \n";
        mensajeRetiroExitoso += "[Según el BCCR, el tipo de cambio de venta del dólar es: " + pTipoCambio + "] \n";
        mensajeRetiroExitoso += "[El monto equivalente de su retiro es es " + pMontoRetiradoColones + "] \n";
        mensajeRetiroExitoso += "[El monto cobrado por concepto de comisión fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual]";
        JOptionPane.showMessageDialog(null, mensajeRetiroExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeTransferenciaExitosa(double pMontoTransferido, double pMontoComision) {
        String mensajeTransferenciaExitosa = "";
        mensajeTransferenciaExitosa += "Estimado usuario, la transferencia de fondos se ejecuto satisfactoriamente. \n";
        mensajeTransferenciaExitosa += "El monto retirado de la cuenta origen y depositado en la cuenta destino es " + pMontoTransferido + "\n";
        mensajeTransferenciaExitosa += "[El monto cobrado por concepto de comisión a la cuenta origen fue de " + pMontoComision + " colones, que fueron rebajados automáticamente de su saldo actual] \n";
        JOptionPane.showMessageDialog(null, mensajeTransferenciaExitosa, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeNotificacionDeEnvioDeMensaje() {
        String mensajeNotificacionDeEnvioDeMensaje = "";
        mensajeNotificacionDeEnvioDeMensaje += "Estimado usuario se ha enviado una palabra por mensaje de texto. \n";
        mensajeNotificacionDeEnvioDeMensaje += "Por favor revise sus mensajes y proceda a digitar la palabra enviada";
        JOptionPane.showMessageDialog(null, mensajeNotificacionDeEnvioDeMensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeAdvertenciaSegundoIntentoPalabraSecreta() {
        String mensajeAdvertenciaSegundoIntentoPalabraSecreta = "ATENCIÓN: se enviará nuevamente la palabra secreta por mensaje de texto \n";
        mensajeAdvertenciaSegundoIntentoPalabraSecreta += "Si vuelve a ingresar una palabra incorrecta su cuenat será inactivada";
        JOptionPane.showMessageDialog(null, mensajeAdvertenciaSegundoIntentoPalabraSecreta, "Notificación", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void imprimirMensajeAlertaDeInactivacionDeCuenta() {
        String mensajeAlertaDeInactivacionDeCuenta = "ATENCIÓN: su cuenta ha sido inactivada debido a cuestiones de seguridad \n";
        mensajeAlertaDeInactivacionDeCuenta += "Se ha enviado un correo electrónico con detalles de la inactivación";
        JOptionPane.showMessageDialog(null, mensajeAlertaDeInactivacionDeCuenta, "Notificación", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorCuentaNoExiste(String pNumeroDeCuenta) {
        String mensajeDeError = "No existe ninguna cuenta registrada con el número: " + pNumeroDeCuenta;
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(String pNumeroDeCuenta, String pPin) {
        String mensajeDeError = "El pin " + pPin + " no corresponde la cuenta " + pNumeroDeCuenta;
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFormatoDePinInvalido() {
        String mensajeDeError = "El formato del pin ingresado no es válido \n";
        mensajeDeError += "Debe ingresar al menos una letra mayúscula, un número y un caracter especial";
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFormatoDeMontoDeRetiroIncorrecto() {
        String mensajeDeError = "El formato del monto de retiro ingresado no es válido \n";
        mensajeDeError += "El monto debe ser un valor numérico entero positivo";
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorFondosInsuficientes() {
        String mensajeDeError = "Usted no tiene suficientes fondos para realizar esta transacción";
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeDeErrorCuentaInactiva() {
        String mensajeDeError = "Usted no puede realizar esta operación porque su cuenta ha sido inactivada \n";
        mensajeDeError += "Únicamente podrá consultar su estatus";
        JOptionPane.showMessageDialog(null, mensajeDeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirMensajeEstatusDeCuenta(String pNumeroDeCuenta, String pEstatusCuenta) {
        String mensajeCambioDePinExitoso = "La cuenta número "+ pNumeroDeCuenta +" tiene estatus de " + pEstatusCuenta;
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeSaldoCuentaActualColones(String pSaldoActual) {
        String mensajeCambioDePinExitoso = "Estimado usuario el saldo actual de su cuenta es " + pSaldoActual + " colones";
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirMensajeSaldoCuentaActualDolares(double pSaldoActual, double pValorDeCompra) {
        String mensajeCambioDePinExitoso = "Estimado usuario el saldo actual de su cuenta es " + String.format("%.2f", pSaldoActual) + " dólares\n" + "Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra. \n" + "Según el BCCR, el tipo de cambio de compra del dólar de hoy es: "  + String.format("%.2f", pValorDeCompra);
        JOptionPane.showMessageDialog(null, mensajeCambioDePinExitoso, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
}
