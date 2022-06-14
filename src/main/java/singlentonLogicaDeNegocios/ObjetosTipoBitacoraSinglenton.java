/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlentonLogicaDeNegocios;
import logicaDeNegocios.ObjetosTipoBitacora;
/**
 *
 * @author estadm
 */
public class ObjetosTipoBitacoraSinglenton {
    private static ObjetosTipoBitacora instancia;
    
    private ObjetosTipoBitacoraSinglenton() {}
    
    public static synchronized ObjetosTipoBitacora instanciar() {
        if(instancia == null) {
            instancia = new ObjetosTipoBitacora();
        }
        return instancia;
    }
}
