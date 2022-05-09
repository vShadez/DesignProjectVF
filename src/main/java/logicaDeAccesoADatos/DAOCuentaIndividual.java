/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDeAccesoADatos;

import com.mongodb.client.MongoCollection;
import logicaDeNegocios.Cuenta;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Projections;
import logicaDeNegocios.Cliente;
import org.bson.conversions.Bson;
import clasesUtilitarias.Encriptacion;
import clasesUtilitarias.Conversion;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import clasesUtilitarias.Conversion;



/**
 *
 * @author sebashdez
 */
public class DAOCuentaIndividual implements IDAOCuentaIndividual{
    public static MongoCollection coleccionCuentas = ConexionBaseDeDatos.ConectarBase().getCollection("Cuentas");

    /**
     *
     * @param pNumeroCuenta
     * @param pPin
     * @param pFechaCreacion
     * @param pSaldo
     * @param pEstatus
     * @return
     */
    @Override
    public boolean registrarCuenta(String pNumeroCuenta, String pPin, String pFechaCreacion, String pSaldo, String pEstatus) {
        try{
            Document Cuenta = new Document("numeroCuenta", pNumeroCuenta).append("pin", pPin).append("fechaCreacion", pFechaCreacion).append("saldo", pSaldo).append("estatus", pEstatus);
            coleccionCuentas.insertOne(Cuenta);
        
            System.out.println("Cuenta registrada en la base de datos");
            return true;
        } catch (Error e){
            return false;
        }
    }
    
    @Override
    public boolean depositar(String pNumeroCuenta, double pMontoDeposito){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("saldo"))).first();
        String saldoEncriptado = documento.getString("saldo");
        String saldoNuevamenteEncriptado;
        double saldo;
        try {
            String saldoDesencriptado = Encriptacion.desencriptar(saldoEncriptado);
            saldo = Conversion.convertirStringEnDecimal(saldoDesencriptado);
            saldo = saldo + pMontoDeposito;
            saldoNuevamenteEncriptado = Encriptacion.encriptar(String.valueOf(saldo));
            
        } catch (Exception ex) {
            return false;
        }
               
        Document actualizarDocumento = (Document) coleccionCuentas.find(new Document("numeroCuenta", pNumeroCuenta)).first();
        if (actualizarDocumento != null){
            Bson updatedValue =  new Document("saldo",  saldoNuevamenteEncriptado);
            Bson updateOperation =  new Document("$set",  updatedValue);
            coleccionCuentas.updateOne(actualizarDocumento, updateOperation);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean retirar(String pNumeroCuenta, double pMontoRetiro){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("saldo"))).first();
        double pSaldo = documento.getDouble("saldo");
        pSaldo = pSaldo - pMontoRetiro;
        
        Document actualizarDocumento = (Document) coleccionCuentas.find(new Document("numeroCuenta", pNumeroCuenta)).first();
        if (actualizarDocumento != null){
            Bson updatedValue =  new Document("saldo",  pSaldo);
            Bson updateOperation =  new Document("$set",  updatedValue);
            coleccionCuentas.updateOne(actualizarDocumento, updateOperation);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean cambiarPin(String pNumeroCuenta, String pNuevoPin){
        Document actualizarDocumento = (Document) coleccionCuentas.find(new Document("numeroCuenta", pNumeroCuenta)).first();
        if (actualizarDocumento != null){
            Bson updatedValue =  new Document("pin",  pNuevoPin);
            Bson updateOperation =  new Document("$set",  updatedValue);
            coleccionCuentas.updateOne(actualizarDocumento, updateOperation);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean actualizarEstatus(String pNumeroCuenta, String pNuevoEstatus){
        Document actualizarDocumento = (Document) coleccionCuentas.find(new Document("numeroCuenta", pNumeroCuenta)).first();
        if (actualizarDocumento != null){
            Bson updatedValue =  new Document("estatus",  pNuevoEstatus);
            Bson updateOperation =  new Document("$set",  updatedValue);
            coleccionCuentas.updateOne(actualizarDocumento, updateOperation);
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param pNumeroCuenta
     * @return
     */
    @Override
    public Cuenta consultarCuenta(String pNumeroCuenta){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("fechaCreacion"), Projections.include("saldo"), Projections.include("estatus"), Projections.include("pin"))).first();
        String numeroCuenta = documento.getString("numeroCuenta");
        String fechaCreacion = documento.getString("fechaCreacion");
        String saldoEncriptado = documento.getString("saldo");
        String estatus = documento.getString("estatus");
        String pPinEncriptado = documento.getString("pin");
        LocalDate fechaCreacionEnFormatoLocalDate = LocalDate.parse(fechaCreacion);

        try {
            String saldoDesencriptado = Encriptacion.desencriptar(saldoEncriptado);
            String pinDesencriptado = Encriptacion.desencriptar(pPinEncriptado);
            double saldoConvertido = Conversion.convertirStringEnDecimal(saldoDesencriptado);
            IDAOClienteCuenta daoClienteCuenta = new DAOClienteCuenta();
            Cliente duenoDeCuenta = (Cliente) daoClienteCuenta.consultarClienteAsociadoACuenta(pNumeroCuenta);
            Cuenta cuenta = new Cuenta(numeroCuenta, fechaCreacionEnFormatoLocalDate, saldoConvertido, estatus, pinDesencriptado, duenoDeCuenta);
            return cuenta;
        } 
        catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public double consultarSaldoActual(String pNumeroCuenta){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("saldo"))).first();
        if (documento != null){
            String saldoEncriptado = documento.getString("saldo");
            try {
                String saldoDesencriptado = Encriptacion.desencriptar(saldoEncriptado);
                return Conversion.convertirStringEnDecimal(saldoDesencriptado);
            } catch (Exception ex) {
                return -1;
            }
        }
        return -1;
    }
    
    @Override
    public String consultarEstatusCuenta(String pNumeroCuenta){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("estatus"))).first();
        if (documento != null){
            String pEstatus = documento.getString("estatus");
            return pEstatus;
        }
        return "";
    }
    
    @Override
    public boolean verificarHayFondosSuficientes(String pNumeroCuenta, double pMontoOperacion){
        return consultarSaldoActual(pNumeroCuenta) >= pMontoOperacion;
    }
    
    @Override
    public boolean verificarPinCorrespondeACuenta(String pNumeroCuenta, String pPin){
        Document documento = (Document) coleccionCuentas.find(new BasicDBObject("numeroCuenta", pNumeroCuenta)).projection(Projections.fields(Projections.include("numeroCuenta"), Projections.include("pin"))).first();
        String pinRealEncriptado = documento.getString("pin");
        try {
            String pinRealDesencriptado = Encriptacion.desencriptar(pinRealEncriptado);
            return pinRealDesencriptado.equals(pPin);
        } 
        catch (Exception ex) {
            return false;
        }
    }
}
