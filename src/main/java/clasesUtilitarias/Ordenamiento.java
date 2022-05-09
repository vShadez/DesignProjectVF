/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

/**
 *
 * @author Jairo Calderón
 */
public class Ordenamiento {
    public void ordenarAscendentemente(Comparable[] pArreglo) {
        for (int i = 1; i < pArreglo.length; i ++) { 
            Comparable nuevo = pArreglo [i] ; 
            int j = i - 1; 
            while (j >= 0 && ! pArreglo [j].menorQue(nuevo)) { 
                pArreglo [j + 1] = pArreglo [j]; 
                j --; 
            } 
            pArreglo [j + 1] = nuevo; 
        }
    }
    
    public void ordenarDescendentemente(Comparable[] pArreglo) {
        for (int i = 1; i < pArreglo.length; i ++) { 
            Comparable nuevo = pArreglo [i] ; 
            int j = i - 1; 
            while (j >= 0 && pArreglo [j].menorQue(nuevo)) { 
                pArreglo [j + 1] = pArreglo [j]; 
                j --; 
            } 
            pArreglo [j + 1] = nuevo; 
        }
    }
}
