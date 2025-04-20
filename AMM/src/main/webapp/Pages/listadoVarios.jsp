<%-- 
    Document   : listadoVarios
    Created on : 18/04/2025, 3:05:17 a. m.
    Author     : nyath
--%>

<%@page import="Clases.Tipo_usuario"%>
<%@page import="Clases.Tipo_documento"%>
<%@page import="Clases.Tipo_limpieza"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de tipos</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            List<Tipo_documento> documento = (List<Tipo_documento>) request.getAttribute("tipos_docu");
            List<Tipo_limpieza> limpieza = (List<Tipo_limpieza>) request.getAttribute("tipos_limp");
            List<Tipo_usuario> usuario = (List<Tipo_usuario>) request.getAttribute("tipos_usua");
        
        if (documento != null) {
        %>
        <h2>Lista tipos de documento</h2>
        <%
        } else if (limpieza != null){
        %>
        <h2>Lista tipos de limpieza</h2>
        <%
        } else if (usuario != null){
        %>
        <h2>Lista tipos de usuario</h2>
        <%
        }
        %> 
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            
            <%
                if(documento != null){
                    for(Tipo_documento d: documento){                        
            %>
            <tr>
                <td><%= d.getId_tipoDocu() %></td>
                <td><%= d.getNombre_tipo()%></td>
            </tr>
            <%
                }
            }
            %>
            
            <%
                if(limpieza != null){
                    for(Tipo_limpieza l: limpieza){                        
            %>
            <tr>
                <td><%= l.getId_tipoLimp() %></td>
                <td><%= l.getNombre_tipo() %></td>
            </tr>
            <%
                }
            }
            %>
            
            <%
                if(usuario != null){
                    for(Tipo_usuario u: usuario){                        
            %>
            <tr>
                <td><%= u.getId_tipoUsua() %></td>
                <td><%= u.getNombre_tipo() %></td>
            </tr>
            <%
                }
            }
            %>
        </table> 
    </body>
</html>
