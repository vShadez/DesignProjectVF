<%-- 
    Document   : ListaClientes
    Created on : May 11, 2022, 10:00:37 PM
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
            <div class ="mb-4 text-center" >
                <h1> Lista de clientes </h1>
                <div>
                    <img src="../imagenesVista/Imagen2.png"/>
                </div>
                <thead>
                    <tr>
                        <th>Numero de cuenta</th>
                        <th>A</th>
                        <th>B</th>
                        <th>C</th>
                    </tr>
                </thead>
                {{#each cliente}}
                <tbody>
                    <tr>
                        <td class= "btn btn-secondary" onClick="document.location.href='../index.html';"> Numero de cuenta </td>
                        <td>{{b}}</td>
                        <td>{{c}}</td>
                        <td>{{identificacion}}</td>
                    </tr>
                </tbody>
                {{/each}}
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
