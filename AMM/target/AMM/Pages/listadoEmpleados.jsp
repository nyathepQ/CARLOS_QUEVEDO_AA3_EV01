<%-- 
    Document   : listaEmpleados
    Created on : 17/04/2025, 8:53:14 p. m.
    Author     : nyath
--%>

<%@page import="java.util.List"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de empleados</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            List<Usuario> empleados = (List<Usuario>) request.getAttribute("empleados");
        %>
        <h2>Listado de Empleados</h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Tipo de usuario</th>
                <th>Nombre de usuario</th>
                <th>Contraseña</th>
                <th>Tipo de documento</th>
                <th>Documento</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Telefono</th>
                <th>Correo electronico</th>
            </tr>
            
            <%
                if(empleados != null){                    
                    for(Usuario e: empleados){                        
            %>
            <tr>
                <td><%= e.getId_usuario() %></td>
                <td><%= e.getTipoUsuario().getNombre_tipo() %></td>
                <td><%= e.getNombre_usuario()%></td>
                <td><%= e.getContrasena_usuario() %></td>
                <td><%= e.getTipoDocumento().getNombre_tipo() %></td>
                <td><%= e.getDocumento_usuario() %></td>
                <td><%= e.getNombres() %></td>
                <td><%= e.getApellidos() %></td>
                <td><%= e.getTelefono_usuario()%></td>
                <td><%= e.getCorreo_usuario() %></td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>
