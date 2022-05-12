<%-- 
    Document   : SeleccionDeConsultaEstadoDeCuenta
    Created on : May 11, 2022, 10:01:21 PM
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
                    <h1>Estado de cuenta en</h1>
                    <div>
                        <img src="../imagenesVista/Imagen17.png"/>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <a class="btn btn-primary " href="InformacionPorConsultaDeEstadoCuentaColones.html"> Colones </a> 
                </div>
                <div class="form-group">
                    <a class="btn btn-primary " href="InformacionPorConsultaDeEstadoCuentaDolares.html"> Dolares </a> 
                </div>
                <div class="box" style="text-align: center">
                    <a href="../index.html">Cancelar</a>
                </div>
            </div>
        </div>
    </div>
</html>
