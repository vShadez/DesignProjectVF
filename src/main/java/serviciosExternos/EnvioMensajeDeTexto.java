/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosExternos;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author Jairo Calderón
 */
public class EnvioMensajeDeTexto {

    public void enviarMensaje(String pNumeroReceptor, String pMensaje) {
        Twilio.init("AC9fb2dd20b5d3b44904dfc5ad80ceadc8", "878e8b2510ce6fb9b516689d20f91ed2");
        Message.creator(
                new com.twilio.type.PhoneNumber("+506" + pNumeroReceptor),
                new com.twilio.type.PhoneNumber("+19794935549"),
                pMensaje)
        .create();
    }
}
