/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singlentonLogicaDeNegocios;
import logicaDeNegocios.RegistroGeneralBitacoras;
/**
 *
 * @author estadm
 */
public class ObjetosTipoBitacoraSinglenton {
    private static RegistroGeneralBitacoras instancia;
    
    private ObjetosTipoBitacoraSinglenton() {}
    
    public static synchronized RegistroGeneralBitacoras instanciar() {
        if(instancia == null) {
            instancia = new RegistroGeneralBitacoras();
        }
        return instancia;
    }
}
