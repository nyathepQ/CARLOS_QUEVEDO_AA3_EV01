<%-- 
    Document   : listadoVarios
    Created on : 18/04/2025, 3:05:17 a. m.
    Author     : nyath
--%>

<%@page import="Clases.Varios"%>
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
            Varios[] documento = (Varios[]) request.getAttribute("tipos_docu");
            Varios[] limpieza = (Varios[]) request.getAttribute("tipos_limp");
            Varios[] usuario = (Varios[]) request.getAttribute("tipos_usua");
        
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
                    for(Varios d: documento){                        
            %>
            <tr>
                <td><%= d.getCodigo() %></td>
                <td><%= d.getNombre() %></td>
            </tr>
            <%
                }
            }
            %>
            
            <%
                if(limpieza != null){
                    for(Varios l: limpieza){                        
            %>
            <tr>
                <td><%= l.getCodigo() %></td>
                <td><%= l.getNombre() %></td>
            </tr>
            <%
                }
            }
            %>
            
            <%
                if(usuario != null){
                    for(Varios u: usuario){                        
            %>
            <tr>
                <td><%= u.getCodigo() %></td>
                <td><%= u.getNombre() %></td>
            </tr>
            <%
                }
            }
            %>
        </table> 
    </body>
</html>
