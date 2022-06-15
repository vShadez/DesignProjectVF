package seguridad;

import javax.swing.JOptionPane;

/**
 *
 * @author estadm
 */
public class SeguridadAdministrador {
    
    public static boolean iniciarSesionAdministrador(String pUsuario, String pPin){
        return "JSF".equals(pUsuario) && "123".equals(pPin);
    }
}
