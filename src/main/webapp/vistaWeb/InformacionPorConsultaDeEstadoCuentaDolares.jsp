<%-- 
    Document   : InformacionPorConsultaDeEstadoCuentaDolares
    Created on : May 11, 2022, 10:00:23 PM
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

    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4">
                <div class = " mb-4 text-center">
                    <h1> Informacion del estado de cuenta en dolares </h1>
                    <div>
                        <img src="../imagenesVista/Imagen17.png"/>
                    </div>
                    <br>
                    <div class="row">
                        {{#each consultaEstadoCuenta}}
                        <div class="form-group col-md-6">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <div class="card-header">
                                        <p class = "lead"> Numero de cuenta: {{numeroCuenta}} </p>
                                        <p class = "lead"> Saldo actual: {{saldo}}</p>
                                        <p class = "lead"> Propietario: {{propietario}}</p>
                                        <p class = "lead"> Correo electronico: {{correoElectronico}}</p>
                                        <p class = "lead"> Numero telefonico: {{numeroTelefono}}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                    <div class ="mb-4 text-center" >
                        <h1> Operaciones </h1>
                        <thead>
                            <tr>
                                <th>Tipo de operacion</th>
                                <th>Fecha</th>
                                <th>Monto</th>
                                <th>Comision</th>
                            </tr>
                        </thead>
                        {{#each consultarOperacion}}
                        <tbody>
                            <tr>
                                <td>{{tipoOperacion}}</td>
                                <td>{{fecha}}</td>
                                <td>{{monto}}</td>
                                <td>{{comision}}</td>
                            </tr>
                        </tbody>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </table>
    <div class="box" style="text-align: center">
        <a href="../index.html">Cancelar</a>
    </div>
</html>
