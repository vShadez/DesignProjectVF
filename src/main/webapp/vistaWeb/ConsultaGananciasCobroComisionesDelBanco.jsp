<%-- 
    Document   : ConsultaGananciasCobroComisionesDelBanco
    Created on : May 11, 2022, 9:56:15 PM
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
                    <h1> Ganancias totales por cobro de comision </h1>
                    <div>
                        <img src="../imagenesVista/Imagen23.png"/>
                    </div>
                    <br>
                    <div class="row">
                        {{#each consultaOperaciones}}
                        <div class="form-group col-md-12">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <div class="card-header">
                                        <p class = "lead"> Monto total por concepto de operaciones de depositos: C:{{montoDepositos}}, $:{{montoDepositosDolares}} </p>
                                        <p class = "lead"> Monto total por concepto de operaciones de retiros: C:{{montoRetiros}}, $:{{montoRetirosDolares}}</p>
                                        <p class = "lead"> Monto total por concepto de depositos y retiros: C:{{montoDepositosYRetiros}}, $:{{montoDepositosYRetirosDolares}}</p>
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
    <div class="form-group text-center">
        <button type="submit" class= "btn btn-primary">
            Consultar
        </button>
        <a href="../index.html">Cancelar</a>
    </div>
</html>
