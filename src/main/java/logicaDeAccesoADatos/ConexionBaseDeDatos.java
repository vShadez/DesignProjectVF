/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author sebashdez
 */
public class ConexionBaseDeDatos {
    private static MongoClient clusterBaseDeDatos;
    private static MongoDatabase dataBase;
    
    public static MongoDatabase ConectarBase() {
        try{
            clusterBaseDeDatos = MongoClients.create("mongodb+srv://shadez:123Shadez@cluster0.jiren.mongodb.net/test");
            dataBase = clusterBaseDeDatos.getDatabase("SoftwareDesign");
            
            System.out.println("Conexión con la base de datos exitosa");
            return dataBase;
        } catch(Error e){
            System.out.println(e);
        }
        return null;
    }
    
}
