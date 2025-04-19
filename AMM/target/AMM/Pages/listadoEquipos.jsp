<%-- 
    Document   : listadoEquipos
    Created on : 18/04/2025, 12:13:58 a. m.
    Author     : nyath
--%>

<%@page import="Clases.Usuario"%>
<%@page import="Servicios.UsuarioManager"%>
<%@page import="Clases.EquipoTrabajo"%>
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
            EquipoTrabajo[] equipos = (EquipoTrabajo[]) request.getAttribute("equipos");
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
                    UsuarioManager um = new UsuarioManager();
                    
                    for(EquipoTrabajo e: equipos){
                        Usuario lider = new Usuario();
                        Usuario miembro1 = new Usuario();
                        Usuario miembro2 = new Usuario();
                        if(e.getLider() != null){
                            lider = um.buscarUsuario(e.getLider(), "");
                        }
                        if(e.getMiembro1() != null){
                            miembro1 = um.buscarUsuario(e.getMiembro1(), "");
                        }
                        if(e.getMiembro2() != null){
                            miembro2 = um.buscarUsuario(e.getMiembro2(), "");
                        }
            %>
            <tr>
                <td><%= e.getCodigo() %></td>
                <td><%= e.getNombre_equipo() %></td>
                <td><%= lider.getCodigo() != null ? um.UsuarioToString(lider) : "" %></td>
                <td><%= miembro1.getCodigo() != null ? um.UsuarioToString(miembro1) : "" %></td>
                <td><%= miembro2.getCodigo() != null ? um.UsuarioToString(miembro2) : "" %></td>
            </tr>
            <%
                }
            }
            %>
        </table>       
    </body>
</html>
