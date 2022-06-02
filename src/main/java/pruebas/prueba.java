/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import com.google.cloud.translate.Detection;
import java.time.LocalDate;
import logicaDeNegocios.Bitacora;
import logicaDeNegocios.BitacoraCSV;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import logicaDeNegocios.BitacoraTramaPlana;

/**
 *
 * @author calde
 */
public class prueba {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        /*
        RegistroDeBitacora registro1 = new RegistroDeBitacora(LocalDate.now(), "Consulta", "Web");
        RegistroDeBitacora registro2 = new RegistroDeBitacora(LocalDate.now(), "Consulta", "GUI");
        RegistroDeBitacora registro3 = new RegistroDeBitacora(LocalDate.now(), "Consulta", "CLI");
        Bitacora bitacoraXML1 = new BitacoraXML(registro1);
        Bitacora bitacoraXML2 = new BitacoraXML(registro2);
        Bitacora bitacoraXML3 = new BitacoraXML(registro3);
        //Bitacora bitacoraCSV1 = new BitacoraCSV(registro1);
        //Bitacora bitacoraCSV2 = new BitacoraCSV(registro2);
        //Bitacora bitacoraCSV3 = new BitacoraCSV(registro3);
        registro1.agregarBitacora(bitacoraXML1);
        //registro1.agregarBitacora(bitacoraCSV1);
        registro2.agregarBitacora(bitacoraXML2);
        //registro2.agregarBitacora(bitacoraCSV2);
        registro3.agregarBitacora(bitacoraXML3);
        //registro3.agregarBitacora(bitacoraCSV3);
        registro1.registrarEnBitacoras();
        registro2.registrarEnBitacoras();
        registro3.registrarEnBitacoras();
        */
        Bitacora bitacoraXML1 = new BitacoraXML(null);
        System.out.println(bitacoraXML1.consultarTodosLosRegistros());
        /*
        System.setProperty("GOOGLE_API_KEY", "AIzaSyAa5fnltG5HikNxs6unxMr_zfB1dTUWeZc");
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate("La camisa es roja", TranslateOption.sourceLanguage("es"),TranslateOption.targetLanguage("en"));
        System.out.printf("%s%n", translation.getTranslatedText());
        */
        /*
        RegistroDeBitacora registro1 = new RegistroDeBitacora(LocalDate.now(), "Consulta de primer tipo", "Web");
        RegistroDeBitacora registro2 = new RegistroDeBitacora(LocalDate.now(), "Consulta de segundo tipo", "GUI");
        RegistroDeBitacora registro3 = new RegistroDeBitacora(LocalDate.now(), "Consulta de tercer tipo", "CLI");
        Bitacora bitacoraTP2 = new BitacoraTramaPlana(registro2);
        Bitacora bitacoraTP3 = new BitacoraTramaPlana(registro3);
        registro1.agregarBitacora(bitacoraTP1);
        registro2.agregarBitacora(bitacoraTP2);
        registro3.agregarBitacora(bitacoraTP3);
        registro1.registrarEnBitacoras();
        registro2.registrarEnBitacoras();
        registro3.registrarEnBitacoras();
        System.out.println(bitacoraTP1.consultarRegistrosDelDia());
        System.out.println(bitacoraTP1.consultarRegistrosDeVista("CLI"));
        */
    }
}
