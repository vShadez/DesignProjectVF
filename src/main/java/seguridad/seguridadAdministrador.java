package seguridad;

import javax.swing.JOptionPane;

/**
 *
 * @author estadm
 */
public class seguridadAdministrador {
    
    public static boolean inicioSesionAdministrador(String pUsuario, String pPin){
        return "JSF".equals(pUsuario) && "123".equals(pPin);
    }
}
