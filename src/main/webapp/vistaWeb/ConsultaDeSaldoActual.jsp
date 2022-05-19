<%-- 
    Document   : ConsultaDeSaldoActual
    Created on : May 11, 2022, 9:55:56 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar saldo actual</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css%22%3E
    </head>

    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Consultar saldo actual</h1>
                    <div>
                        <img src="../imagenesVista/Imagen16.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/ConsultaDeSaldoActual"/>" >
                    <div class="form-group">
                        <label>Numero de cuenta</label>
                        <input type="text" class "form-control" name = "numeroCuenta" placeholder="Numero de cuenta" required>
                    </div>
                    <div class="form-group">
                        <label>Pin</label>
                        <input type="password" class "form-control" name = "pin" placeholder="Pin" required>
                    </div>
                    <div class="box" style="text-align: center">
                        <input type="submit" class= "btn btn-primary " name="event" value="Consultar en colones">
                        <input type="submit" class= "btn btn-secondary " name="event" value="Consultar en dolares">
                    </div>
                    <br>
                    <a href="../index.html">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>