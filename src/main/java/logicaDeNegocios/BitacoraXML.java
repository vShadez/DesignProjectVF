/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeNegocios;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import listaDinamica.Lista;
import listaDinamica.Nodo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jairo Calderón
 */
public class BitacoraXML extends Bitacora{
    private RegistroDeBitacora registroGuardado;

    public BitacoraXML(RegistroDeBitacora pRegistroGuardado) {
        this.registroGuardado = pRegistroGuardado;
    }

    @Override
    protected boolean agregarRegistro() {
        try {
            DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
            
            Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\Bitacora.xml"));
            Element documentoRaiz = documento.getDocumentElement();
            Element elemento = documento.createElement("Registro");
            documentoRaiz.appendChild(elemento);
            elemento.setAttribute("Fecha", this.registroGuardado.fecha.toString());
            elemento.setAttribute("AccionEjecutada", this.registroGuardado.tipoDeAccion);
            elemento.setAttribute("VistaDeAcceso", this.registroGuardado.vista);
            TransformerFactory fabricaDeTransformadoresDeDatos = TransformerFactory.newInstance();
            Transformer transformadorDeDatos = fabricaDeTransformadoresDeDatos.newTransformer();
            DOMSource fuente = new DOMSource(documento);
            StreamResult resultado = new StreamResult(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\Bitacora.xml"));
            transformadorDeDatos.transform(fuente, resultado);
            return true;
        }
        catch (Exception excepcion) {
            return false;
        }
    }
    
    @Override
    protected String visualizarBitacora() throws Exception {
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        TransformerFactory fabricaDeTransformadoresDeFormato = TransformerFactory.newInstance();
        Transformer transformadorDeFormato = fabricaDeTransformadoresDeFormato.newTransformer();
        transformadorDeFormato.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer = new StringWriter();
        transformadorDeFormato.transform(new DOMSource(documento), new StreamResult(writer));
        String resultado = writer.getBuffer().toString();
        return resultado;
    }

    @Override
    protected void vaciarVisualizadorDeBitacora() throws Exception {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        javax.xml.xpath.XPath xpath = xPathFactory.newXPath();
        XPathExpression expresion = xpath.compile("Registros");
        DocumentBuilderFactory fabricaDeConstructorDeDocumentos = DocumentBuilderFactory.newInstance();
        Document documento = fabricaDeConstructorDeDocumentos.newDocumentBuilder().parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        Node nodo = (Node) expresion.evaluate(documento, XPathConstants.NODE);
        nodo.getParentNode().removeChild(nodo);
        TransformerFactory fabricaDeTransformadores = TransformerFactory.newInstance();
        Transformer transformador = fabricaDeTransformadores.newTransformer();
        transformador.transform(new DOMSource(documento), new StreamResult(System.out));
    }
    
    @Override
    protected void cargarVisualizadorDeBitacora(Lista<RegistroDeBitacora> pListaDeRegistros) throws Exception{
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        Element documentoRaiz = documento.getDocumentElement();
        Nodo puntero = pListaDeRegistros.inicio;
        while(puntero != null) {
            RegistroDeBitacora registro = (RegistroDeBitacora) puntero.objeto;
            Element elemento = documento.createElement("Registro");
            documentoRaiz.appendChild(elemento);
            elemento.setAttribute("Fecha", registro.fecha.toString());
            elemento.setAttribute("AccionEjecutada", registro.tipoDeAccion);
            elemento.setAttribute("VistaDeAcceso", registro.vista);
            puntero = puntero.siguiente;
        }
        TransformerFactory fabricaDeTrabsformadoresDeDatos = TransformerFactory.newInstance();
        Transformer transformadorDeDatos = fabricaDeTrabsformadoresDeDatos.newTransformer();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(new File(System. getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\VisualizacionDeBitacora.xml"));
        transformadorDeDatos.transform(source, result);
    }
    
    @Override
    public String consultarRegistrosDelDia() throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        fabricaDeDocumentos.setIgnoringComments(true);
        fabricaDeDocumentos.setIgnoringElementContentWhitespace(true);
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        
        Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String fechaDeRegistro = elemento.getAttribute("Fecha");
            if(fechaDeRegistro.equals(LocalDate.now().toString()))
            {
                String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
                String vista = elemento.getAttribute("VistaDeAcceso");
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarRegistrosDeVista(String pVista) throws Exception {
        this.vaciarVisualizadorDeBitacora();
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String vista = elemento.getAttribute("VistaDeAcceso");
            if(vista.equals(pVista))
            {
                String fechaDeRegistro = elemento.getAttribute("Fecha");
                String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
                LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
                RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
                resultadosDeConsulta.agregarNodo(registro);
            }
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }

    @Override
    public String consultarTodosLosRegistros() throws Exception {
        Lista<RegistroDeBitacora> resultadosDeConsulta = new Lista<>();
        DocumentBuilderFactory fabricaDeDocumentos = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeDocumentos.newDocumentBuilder();
        Document documento = constructor.parse(new File(System.getProperty("user.dir") + "\\src\\main\\java\\almacenamientoXML\\Bitacora.xml"));
        documento.getDocumentElement().normalize();
        NodeList listaDeNodos = documento.getElementsByTagName("Registro");
        for (int indice = 0; indice < listaDeNodos.getLength(); indice++) 
        {
            Node nodo = listaDeNodos.item(indice);
            Element elemento = (Element) nodo;
            String fechaDeRegistro = elemento.getAttribute("Fecha");
            String tipoDeAccion = elemento.getAttribute("AccionEjecutada");
            String vista = elemento.getAttribute("VistaDeAcceso");
            LocalDate fechaEnFormatoLocalDate = LocalDate.parse(fechaDeRegistro);
            RegistroDeBitacora registro = new RegistroDeBitacora(fechaEnFormatoLocalDate, tipoDeAccion, vista);
            resultadosDeConsulta.agregarNodo(registro);
        }
        this.cargarVisualizadorDeBitacora(resultadosDeConsulta);
        return this.visualizarBitacora();
    }
}
