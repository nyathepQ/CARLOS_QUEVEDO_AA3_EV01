<%-- 
    Document   : listadoClientes
    Created on : 18/04/2025, 2:06:53 a. m.
    Author     : nyath
--%>

<%@page import="Clases.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Clientes</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            Cliente[] clientes = (Cliente[]) request.getAttribute("clientes");
        %>
        <h2>Listado de Clientes</h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Dirección</th>
                <th>Telefono</th>
                <th>Correo electronico</th>
                <th>Observaciones</th>
            </tr>
            
            <%
                if(clientes != null){
                    for(Cliente c: clientes){                        
            %>
            <tr>
                <td><%= c.getCodigo() %></td>
                <td><%= c.getNombres() %></td>
                <td><%= c.getApellidos() %></td>
                <td><%= c.getDireccion() %></td>
                <td><%= c.getTelefono() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getObservaciones() %></td>
            </tr>
            <%
                }
            }
            %>
        </table> 
    </body>
</html>
