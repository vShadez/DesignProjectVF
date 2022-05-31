/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import clasesUtilitarias.Conversion;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import singletonClasesUtilitarias.ConversionSingleton;

/**
 *
 * @author Jairo Calderón
 */
public class BitacoraCSV extends Bitacora{
    private RegistroDeBitacora registroGuardado;

    public BitacoraCSV(RegistroDeBitacora pRegistroGuardado) {
        this.registroGuardado = pRegistroGuardado;
    }

    @Override
    protected void agregarRegistro() {
        try {
            String [] registro = {this.registroGuardado.fecha.toString(), this.registroGuardado.tipoDeAccion, this.registroGuardado.vista};
            String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\Bitacora.csv";
            CSVWriter escritorDeArchivoCSV = new CSVWriter(new FileWriter(archivoCSV, true));
            escritorDeArchivoCSV.writeNext(registro);
            escritorDeArchivoCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(BitacoraCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String visualizarBitacora() throws Exception {
        String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.csv";
        CSVReader lectorDeArchivoCSV = new CSVReader(new FileReader(archivoCSV));
        String[] fila = null;
        String resultado = "Fecha de Registro,Tipo de acción,Vista desde donde se accedió \n";
        while((fila = lectorDeArchivoCSV.readNext()) != null) {
            String fechaDeRegistro = fila[0];
            String tipoDeAccion = fila[1];
            String vista = fila[2];
            resultado += fechaDeRegistro + "," + tipoDeAccion + "," + vista + "\n";
        }
        lectorDeArchivoCSV.close();
        return resultado;
    }

    @Override
    protected void vaciarVisualizadorDeBitacora() throws Exception {
        File archivoDeVisualizacionDeBitacoraPorBorrar = new File(System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.csv"); 
        archivoDeVisualizacionDeBitacoraPorBorrar.delete();
        File archivoDeVisualizacionDeBitacoraPorCrear = new File(System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.csv"); 
        archivoDeVisualizacionDeBitacoraPorCrear.createNewFile();
    }
    
    @Override
    protected void cargarVisualizadorDeBitacora(Lista<RegistroDeBitacora> pListaDeRegistros) throws Exception {
        String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\VisualizacionDeBitacora.csv";
        CSVWriter escritorDeArchivoCSV = new CSVWriter(new FileWriter(archivoCSV, true));
        Nodo puntero = pListaDeRegistros.inicio;
        while(puntero != null) {
            RegistroDeBitacora registro = (RegistroDeBitacora) puntero.objeto;
            String[] nuevoRegistro = {registro.fecha.toString(), registro.tipoDeAccion, registro.vista};
            escritorDeArchivoCSV.writeNext(nuevoRegistro);
            puntero = puntero.siguiente;
        }
        escritorDeArchivoCSV.close();
    }

    @Override
    public String consultarRegistrosDelDia() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\Bitacora.csv";
        CSVReader lectorDeArchivoCSV = new CSVReader(new FileReader(archivoCSV));
        String[] fila = null;
        while((fila = lectorDeArchivoCSV.readNext()) != null) {
            String fechaDeRegistro = fila[0];
            if(fechaDeRegistro.equals(LocalDate.now().toString())) {
                String tipoDeAccion = fila[1];
                String vista = fila[2];
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        lectorDeArchivoCSV.close();
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarRegistrosDeVista(String pVista) throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\Bitacora.csv";
        CSVReader lectorDeArchivoCSV = new CSVReader(new FileReader(archivoCSV));
        String[] fila = null;
        while((fila = lectorDeArchivoCSV.readNext()) != null) {
            String vista = fila[2];
            if(vista.equals(pVista)) {
                String fechaDeRegistro = fila[0];
                String tipoDeAccion = fila[1];
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        lectorDeArchivoCSV.close();
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarTodosLosRegistros() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        String archivoCSV = System.getProperty("user.dir") + "\\almacenamientoDeBitacoras\\Bitacora.csv";
        CSVReader lectorDeArchivoCSV = new CSVReader(new FileReader(archivoCSV));
        String[] fila = null;
        while((fila = lectorDeArchivoCSV.readNext()) != null) {
            String fechaDeRegistro = fila[0];
            String tipoDeAccion = fila[1];
            String vista = fila[2];
            LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
            RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
            resultadosDeConsulta.agregarNodo(registro);
        }
        lectorDeArchivoCSV.close();
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }
}
