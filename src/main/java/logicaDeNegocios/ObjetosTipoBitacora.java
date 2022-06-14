package logicaDeNegocios;

import java.time.LocalDate;

/**
 *
 * @author estadm
 */
public class ObjetosTipoBitacora {
    
    public void registrarBitacoraXML(LocalDate pFecha, String pAccion, String pVista){
        RegistroDeBitacora registro = new RegistroDeBitacora(pFecha, pAccion, pVista);
        Bitacora bitacora = new BitacoraXML(registro);
        registro.agregarBitacora(bitacora);
        registro.registrarEnBitacoras();
    }
    
    public void registrarBitacoraCSV(LocalDate pFecha, String pAccion, String pVista){
        RegistroDeBitacora registro = new RegistroDeBitacora(pFecha, pAccion, pVista);
        Bitacora bitacora = new BitacoraCSV(registro);
        registro.agregarBitacora(bitacora);
        registro.registrarEnBitacoras();
    }
    
    public void registrarBitacoraTXT(LocalDate pFecha, String pAccion, String pVista){
        RegistroDeBitacora registro = new RegistroDeBitacora(pFecha, pAccion, pVista);
        Bitacora bitacora = new BitacoraTramaPlana(registro);
        registro.agregarBitacora(bitacora);
        registro.registrarEnBitacoras();
    }
}
