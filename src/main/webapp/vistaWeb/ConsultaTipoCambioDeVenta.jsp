<%-- 
    Document   : ConsultaTipoCambioDeVenta
    Created on : May 11, 2022, 9:58:55 PM
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
                    <h1>Tipo de cambio venta</h1>
                    <div>
                        <img src="../imagenesVista/Imagen15.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <table class="table table-striped">
                            <div class="card-body">
                                {{#each consultarTipoCambioVenta}}
                                <h4>
                                    1 dolar = {{tipoDeCambio}}
                                </h4>
                                {{/each}}
                            </div>
                        </table>
                    </div>
                    <div class="form-group">
                        <a href="../index.html">Volver</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>
