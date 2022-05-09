/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistaCLI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Jairo Calderón
 */
public class TextoIngresadoPorElUsuario {
    public static String solicitarIngresoDeTextoAlUsuario() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String textoIngresadoPorElUsuario = reader.readLine();
        return textoIngresadoPorElUsuario;
    }
}
