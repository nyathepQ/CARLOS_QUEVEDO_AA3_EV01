<%-- 
    Document   : listadoEquipos
    Created on : 18/04/2025, 12:13:58 a. m.
    Author     : nyath
--%>

<%@page import="java.util.List"%>
<%@page import="Clases.Equipo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Equipos</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            List<Equipo> equipos = (List<Equipo>) request.getAttribute("equipos");
        %>
        <h2>Listado de Equipos</h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre del equipo</th>
                <th>Lider</th>
                <th>Miembro</th>
                <th>Miembro</th>
            </tr>
            
            <%
                if(equipos != null){
                    for(Equipo e: equipos){                        
            %>
            <tr>
                <td><%= e.getId_equipo() %></td>
                <td><%= e.getNombre_equipo() %></td>
                <td><%= e.getLider() != null ? e.getUsuariosEquipos().get(0).getUsuario().toString() : "" %></td>
                <td><%= e.getMiembro1() != null ? e.getUsuariosEquipos().get(1).getUsuario().toString() : "" %></td>
                <td><%= e.getMiembro2() != null ? e.getUsuariosEquipos().get(2).getUsuario().toString() : "" %></td>
            </tr>
            <%
                }
            }
            %>
        </table>       
    </body>
</html>
