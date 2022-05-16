<%-- 
    Document   : ListaClientes
    Created on : May 11, 2022, 10:00:37 PM
    Author     : sebashdez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <th>Primer apellido</th>
                        <th>Segundo apellido</th>
                        <th>Nombre</th>
                        <th>Identificación</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${dtos}">
                        <tr>
                            <td>${row.primerApellido}</td>
                            <td>${row.segundoApellido}</td>
                            <td>${row.nombre}</td>
                            <td><a href="DetalleCliente?identificacion=${row.identificacion}">${row.identificacion}</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <a href="../index.html">Cancelar</a>
    </div>
</html>
