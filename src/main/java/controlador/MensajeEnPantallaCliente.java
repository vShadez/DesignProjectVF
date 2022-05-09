/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeEnPantallaCliente {
    public static void imprimirMensajeCreadoExitoso(String pCodigo, String pNombre, String pIdentificacion, String pFechaNacimiento, String pNumeroTelefono) {
        String mensaje = "Se ha creado un nuevo cliente en el sistema, los datos que fueron almacenados son: \n";
        mensaje += "Código: " + pCodigo + "\n";
        mensaje += "Nombre: " + pNombre + "\n";
        mensaje += "Identificación: " + pIdentificacion + "\n";
        mensaje += "Fecha de nacimiento: " + pFechaNacimiento+ "\n";
        mensaje += "Número de teléfono: " + pNumeroTelefono;
        JOptionPane.showMessageDialog(null, mensaje, "Notificación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void imprimirErrorIdentificacionInvalida() {
        String mensaje = "La identificación debe ser un número entero";
        JOptionPane.showMessageDialog(null, mensaje,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto() {
        String mensaje = "Asegúrese de que su número de telefono incluya ocho dígitos";
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void imprimirErrorFormatoDeCorreoIncorrecto() {
        String mensaje = "Asegúrese de que su correo tenga un formato correcto";
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
       
    public static void imprimirErrorIdentificacionExistente() {
        String mensaje = "La identificación ya existe";
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
