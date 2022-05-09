/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

/**
 *
 * @author Jairo Calderón
 */
public class MensajeEnConsolaCliente {
    public static String imprimirMensajeCreadoExitoso(String pCodigo, String pNombre, String pIdentificacion, String pFechaNacimiento, String pNumeroTelefono) {
        String mensaje = "Se ha creado un nuevo cliente en el sistema, los datos que fueron almacenados son: \n";
        mensaje += "Código: " + pCodigo + "\n";
        mensaje += "Nombre: " + pNombre + "\n";
        mensaje += "Identificación: " + pIdentificacion + "\n";
        mensaje += "Fecha de nacimiento: " + pFechaNacimiento+ "\n";
        mensaje += "Número de teléfono: " + pNumeroTelefono;
        return mensaje;
    }
    
    public static String imprimirErrorIdentificacionInvalida() {
        String mensaje = "La identificación debe ser un número entero";
        return mensaje;
    }
    
    public static String imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto() {
        String mensaje = "Asegúrese de que su número de telefono incluya ocho dígitos";
        return mensaje;
    }
    
    public static String imprimirErrorFormatoDeCorreoIncorrecto() {
        String mensaje = "Asegúrese de que su correo tenga un formato correcto";
        return mensaje;
    }
       
    public static String imprimirErrorIdentificacionExistente() {
        String mensaje = "Ya existe un cliente con esa identificación";
        return mensaje;
    }
    
    public static String imprimirErrorIdentificacionInexistente() {
        String mensaje = "No existe ningún cliente con esa identificación";
        return mensaje;
    }
    
    public static String imprimirErrorFechaDeNacimientoIncorrecta() {
        String mensaje = "El formato de la fecha de nacimiento no es correcto";
        return mensaje;
    }
}
