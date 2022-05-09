/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clasesUtilitarias;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jairo Calderón
 */
public class Encriptacion {
    private static SecretKeySpec claveSecreta;
    
    private static void generarClaveSecreta() throws Exception{
        String clave = "remontada";
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
        MessageDigest algoritmoHash = MessageDigest.getInstance("SHA-1");
        claveEncriptacion = algoritmoHash.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
        Encriptacion.claveSecreta = new SecretKeySpec(claveEncriptacion, "AES");
    }
    
    private static Cipher generarAlgortimoCipherEncriptado() throws Exception {
        Cipher algoritmoCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
        algoritmoCipher.init(Cipher.ENCRYPT_MODE, Encriptacion.claveSecreta);
        return algoritmoCipher;
    }
    
    private static Cipher generarAlgortimoCipherDesencriptado() throws Exception {
        Cipher algoritmoCipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        algoritmoCipher.init(Cipher.DECRYPT_MODE, Encriptacion.claveSecreta);
        return algoritmoCipher;
    }
    
    public static String encriptar(String pMensajePorEncriptar) throws Exception {
        Encriptacion.generarClaveSecreta();
        Cipher algoritmoCipher = Encriptacion.generarAlgortimoCipherEncriptado();
        byte[] byestPorEncriptar = pMensajePorEncriptar.getBytes("UTF-8");
        byte[] bytesEncriptados = algoritmoCipher.doFinal(byestPorEncriptar);
        String mensajeEncriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
        return mensajeEncriptado;
    }
    
    public static String desencriptar(String pMensajePorDesencriptar) throws Exception {
        Encriptacion.generarClaveSecreta();
        Cipher algoritmoCipher = Encriptacion.generarAlgortimoCipherDesencriptado();
        byte[] bytesEncriptados = Base64.getDecoder().decode(pMensajePorDesencriptar);
        byte[] bytesDesencriptados = algoritmoCipher.doFinal(bytesEncriptados);
        String datos = new String(bytesDesencriptados);
        return datos;
    }
}
