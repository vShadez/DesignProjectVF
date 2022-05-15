/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import logicaDeAccesoADatos.DAOCatalogoDeClientes;
import logicaDeAccesoADatos.IDAOCatalogoDeClientes;
import logicaDeNegocios.Cliente;
import logicaDeNegocios.ICliente;
import validacion.ExpresionRegular;
import validacion.ValidacionTipoDeDato;
import vistaGUI.RegistroClientes;
import clasesUtilitarias.Conversion;
import java.util.Date;

/**
 *
 * @author Jairo Calderón
 */
public class ControladorRegistroClientes implements ActionListener{
    public RegistroClientes vistaGUI;
    
    public ControladorRegistroClientes(RegistroClientes pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnRegistrarCliente.addActionListener(this);
        this.vistaGUI.btnCancelar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Registrar")) {
            String primerApellido = this.vistaGUI.txtPrimerApellido.getText();
            String segundoApellido = this.vistaGUI.txtSegundoApellido.getText();
            String nombre = this.vistaGUI.txtNombre.getText();
            String identificacion = this.vistaGUI.txtIdentificacion.getText();
            String numeroDeTelefono = this.vistaGUI.txtNumeroDeTelefono.getText();
            String correoElectronico = this.vistaGUI.txtCorreoElectronico.getText();
            int identificacionEnFormatoEntero = Conversion.convertirStringEnEntero(identificacion);
            int numeroTelefonicoEnFormatoEntero = Conversion.convertirStringEnEntero(numeroDeTelefono);
            
            String formatoFecha = "dd/MM/YYYY";
            Date fecha = this.vistaGUI.fechaNacimientoCliente.getDatoFecha();
            SimpleDateFormat formateador = new SimpleDateFormat(formatoFecha);
            String fechaDeNacimiento = formateador.format(fecha);
            
            String[] split = fechaDeNacimiento.split("/");
            int dia = Conversion.convertirStringEnEntero(split[0]);
            int mes = Conversion.convertirStringEnEntero(split[1]);
            int ano = Conversion.convertirStringEnEntero(split[2]);
            
            if(validarTipoDeDatos(identificacion) && validarFormatoDeDatos(numeroDeTelefono, correoElectronico) && validarExistenciaCliente(identificacionEnFormatoEntero)) {
                IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
                int obtenerCantidadClientes = cantidadDeClientes.consultarCantidadDeClientes()+1;
                String codigo = "CIF-" + obtenerCantidadClientes;
                ICliente nuevoCliente = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroTelefonicoEnFormatoEntero, correoElectronico);
                
                MensajeEnPantallaCliente.imprimirMensajeCreadoExitoso(codigo, nombre, identificacion, fechaDeNacimiento, numeroDeTelefono);
            }
        }
        if(evento.getActionCommand().equals("Volver")) {
           ControladorMenuPrincipal.volverMenuPrincipal();
           vistaGUI.setVisible(false);
       }
    }
    
    public static void actionPerformed(String nombre, String primerApellido, String segundoApellido, String identificacion, String diaDeFechaDeNacimiento, String mesDeFechaDeNacimiento, String anoDeFechaDeNacimiento, String numeroDeTelefono, String correoElectronico) {
        int identificacionEnFormatoEntero = Conversion.convertirStringEnEntero(identificacion);
        int dia = Conversion.convertirStringEnEntero(diaDeFechaDeNacimiento);
        int mes = Conversion.convertirStringEnEntero(mesDeFechaDeNacimiento);
        int ano = Conversion.convertirStringEnEntero(anoDeFechaDeNacimiento);
        int numeroTelefonicoEnFormatoEntero = Conversion.convertirStringEnEntero(numeroDeTelefono);
        
        String fechaDeNacimiento = dia+"/"+mes+"/"+ano;
        if(validarTipoDeDatos(identificacion) && validarFormatoDeDatos(numeroDeTelefono, correoElectronico) && validarExistenciaCliente(identificacionEnFormatoEntero)) {
            IDAOCatalogoDeClientes cantidadDeClientes = new DAOCatalogoDeClientes();
            String codigo = "CIF-" + cantidadDeClientes.consultarCantidadDeClientes();
            ICliente nuevoCliente = new Cliente(codigo, nombre, primerApellido, segundoApellido, identificacionEnFormatoEntero, dia, mes, ano, numeroTelefonicoEnFormatoEntero, correoElectronico);

            MensajeEnPantallaCliente.imprimirMensajeCreadoExitoso(codigo, nombre, identificacion, fechaDeNacimiento, numeroDeTelefono);
        }
        
    }
    
    public static boolean validarTipoDeDatos(String pIdentificacion) {
        if(ValidacionTipoDeDato.verificarEsEntero(pIdentificacion) == false) {
            MensajeEnPantallaCliente.imprimirErrorIdentificacionInvalida();
            return false;
        }
        else {
            return true;
        }
    }
    
    public static void pasarDatos(String nombre, String primerApellido, String segundoApellido, String identificacion, String diaDeFechaDeNacimiento, String mesDeFechaDeNacimiento, String anoDeFechaDeNacimiento, String numeroDeTelefono, String correoElectronico){
        
        String nombre1 = nombre;
        String primerApellido1 = primerApellido;
        String segundoApellido1 = segundoApellido;
        String identificacion1 = identificacion;
        String numeroDeTelefono1 = numeroDeTelefono;
        String correoElectronico1 = correoElectronico;
        
        String diaDeFechaDeNacimiento1 = diaDeFechaDeNacimiento;
        String mesDeFechaDeNacimiento1 = mesDeFechaDeNacimiento;
        String anoDeFechaDeNacimiento1 = anoDeFechaDeNacimiento;
        
        System.out.println("Dia: "+ diaDeFechaDeNacimiento1);
        System.out.println("Mes: "+ mesDeFechaDeNacimiento1);
        System.out.println("Ano: "+ anoDeFechaDeNacimiento1);
        
        System.out.println("Numero telefono: "+ numeroDeTelefono1);
        System.out.println("Correo: "+ correoElectronico);
        
        actionPerformed(nombre1, primerApellido1, segundoApellido1, identificacion1, diaDeFechaDeNacimiento1, mesDeFechaDeNacimiento1, anoDeFechaDeNacimiento1, numeroDeTelefono1, correoElectronico1);
    }
    
    public static boolean validarFormatoDeDatos(String pNumeroDeTelefono, String pCorreoElectronico) {
        if(ExpresionRegular.verificarFormatoCorreoElectronicoEsValido(pCorreoElectronico) == false) {
          MensajeEnPantallaCliente.imprimirErrorFormatoDeCorreoIncorrecto();
          return false;
        }
        else if(ExpresionRegular.verificarFormatoNumeroTelefonicoEsValido(pNumeroDeTelefono) == false) {
            MensajeEnPantallaCliente.imprimirErrorFormatoDeNumeroDeTelefonoIncorrecto();
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean validarExistenciaCliente(int pIdentificacion){
        IDAOCatalogoDeClientes prueb = new DAOCatalogoDeClientes();
        if(prueb.consultarSiExisteCliente(pIdentificacion) == false) {
            MensajeEnPantallaCliente.imprimirErrorIdentificacionExistente();
            return false;
        }
        else {
            return true;
        }
    }
    
}
