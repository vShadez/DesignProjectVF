<%-- 
    Document   : TransferenciasEntreCuentas
    Created on : May 11, 2022, 10:03:52 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Transferencia bancaria</h1>
                    <div>
                        <img src="../imagenesVista/Imagen11.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <label>Numero de cuenta origen</label>
                        <input type="text" class "form-control" name = "numeroCuenta" placeholder="Numero de cuenta" required>
                    </div>
                    <div class="form-group">
                        <label>Pin</label>
                        <input type="password" class "form-control" name = "pin" placeholder="Pin" required>
                    </div>
                    <div class="box" style="text-align: center">
                        <button type="submit" class= "btn btn-primary">
                            Continuar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
