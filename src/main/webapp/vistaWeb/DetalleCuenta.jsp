<%-- 
    Document   : DetalleCuenta
    Created on : May 11, 2022, 9:59:59 PM
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
                    <h1> Detalle de la cuenta </h1>
                    <div class="row">
                        {{#each consultaCuenta}}
                        <div class="form-group col-md-6">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <div class="card-header">
                                        <p class = "lead"> Numero de cuenta: {{numeroCuenta}} </p>
                                        <p class = "lead"> Fecha de creacion: {{fechaCreacion}}</p>
                                        <p class = "lead"> Saldo de la cuenta: {{saldo}}</p>
                                        <p class = "lead"> Pin: {{pin}}</p>
                                        <p class = "lead"> Estatus: {{estatus}}</p>
                                        <p class = "lead"> Cantidad de retiros/depositos: {{cantidadDepositosYRetiros}}</p>
                                        <p class = "lead"> Nombre propietario: {{nombrePropietario}}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </table>
</html>
