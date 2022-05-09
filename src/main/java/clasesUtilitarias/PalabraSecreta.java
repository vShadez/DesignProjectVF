/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

/**
 *
 * @author Jairo Calderon
 */
public class PalabraSecreta {
    
    public static String generarPalabraSecreta() {
        String palabraSecreta = "";
        for(int i = 0; i < 5; i++) {
            palabraSecreta += generarCaracter();
        }
        return palabraSecreta;
    }
    
    private static char generarCaracter() {
        int letraONumero = NumeroAleatorio.generarNumeroAleatorioEnRango(1, 2);
        if(letraONumero == 1) {
            int mayusculaOMinuscula = NumeroAleatorio.generarNumeroAleatorioEnRango(1, 2);
            if(mayusculaOMinuscula == 1) {
                    int valorASCII = NumeroAleatorio.generarNumeroAleatorioEnRango(65, 90);
                    char mayuscula = (char) valorASCII;
                    return mayuscula;
            }
            else {
                    int valorASCII = NumeroAleatorio.generarNumeroAleatorioEnRango(97, 122);
                    char minuscula = (char) valorASCII;
                    return minuscula;
            }
        }
        else {
            int valorASCII = NumeroAleatorio.generarNumeroAleatorioEnRango(48, 57);
            char numeroCaracter = (char) valorASCII;
            return numeroCaracter;
        }
    }
}
