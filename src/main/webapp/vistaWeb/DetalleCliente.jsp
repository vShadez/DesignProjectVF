<%-- 
    Document   : DetalleCliente
    Created on : May 11, 2022, 9:59:41 PM
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
                    <h1> Detalles del cliente </h1>
                    <div class="row">
                        {{#each consultaCliente}}
                        <div class="form-group col-md-6">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <div class="card-header">
                                        <p class = "lead"> Codigo: {{codigo}} </p>
                                        <p class = "lead"> Primer apellido: {{primerApellido}} </p>
                                        <p class = "lead"> Segundo apellido: {{segundoApellido}} </p>
                                        <p class = "lead"> Nombre: {{nombre}} </p>
                                        <p class = "lead"> Identificacion: {{identificacion}} </p>
                                        <p class = "lead"> Fecha nacimiento: {{fechaNacimiento}} </p>
                                        <p class = "lead"> Numero telefono: {{numeroTelefono}} </p>
                                        <p class = "lead"> Correo electronico: {{correoElectronico}} </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {{/each}}
                        <div class="col">
                            <h1> Cuentas del cliente </h1>
                            {{#each consultaCuentasCliente}}
                            <div class="form-group col-md-6">
                                <div class="card mb-2">
                                    <div class="card-body">
                                        <div class="card-header">
                                            <p class = "lead"> Numero de cuenta: {{numeroCuenta}} </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {{/each}}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </table>
</html>
