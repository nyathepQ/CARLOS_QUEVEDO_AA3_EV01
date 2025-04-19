<%-- 
    Document   : listaEmpleados
    Created on : 17/04/2025, 8:53:14 p. m.
    Author     : nyath
--%>

<%@page import="Clases.Varios"%>
<%@page import="Servicios.VariosManager"%>
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
            Usuario[] empleados = (Usuario[]) request.getAttribute("empleados");
        %>
        <h2>Listado de Empleados</h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Tipo de empleado</th>
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
                    VariosManager vr = new VariosManager();
                    
                    for(Usuario e: empleados){
                        Varios tus = vr.buscarTipoVarios("tipo_usuario", "id_tipoUsua", e.getTipo_user());
                        Varios tdo = vr.buscarTipoVarios("tipo_documento", "id_tipoDocu", e.getTipo_docu());
            %>
            <tr>
                <td><%= e.getCodigo() %></td>
                <td><%= tus.getNombre() %></td>
                <td><%= e.getUser() %></td>
                <td><%= e.getPassword() %></td>
                <td><%= tdo.getNombre() %></td>
                <td><%= e.getDocumento() %></td>
                <td><%= e.getNombres() %></td>
                <td><%= e.getApellidos() %></td>
                <td><%= e.getTelefono() %></td>
                <td><%= e.getEmail() %></td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>
