<%-- 
    Document   : listadoClientes
    Created on : 18/04/2025, 2:06:53 a. m.
    Author     : nyath
--%>

<%@page import="java.util.List"%>
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
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
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
                <td><%= c.getId_cliente() %></td>
                <td><%= c.getNombre_cliente() %></td>
                <td><%= c.getApellido_cliente()%></td>
                <td><%= c.getDireccion_cliente()%></td>
                <td><%= c.getTelefono_cliente()%></td>
                <td><%= c.getCorreo_cliente() %></td>
                <td><%= c.getObservacion_cliente()%></td>
            </tr>
            <%
                }
            }
            %>
        </table> 
    </body>
</html>
