<%-- 
    Document   : listaServUs
    Created on : 20/04/2025, 4:05:42 p. m.
    Author     : nyath
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="Servicios.UsuarioManager"%>
<%@page import="Clases.Servicio"%>
<%@page import="java.util.List"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de servicios</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            List<Servicio> servicios = (List<Servicio>) request.getAttribute("servicios");            
        %>
        <h2>Listado de Servicios</h2>
        
        <table border="1">
            <tr>
                <th>Cliente</th>
                <th>Tipo limpieza</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Hora finalización</th>
                <th>Observaciones</th>
            </tr>
            
            <%
                if(servicios != null){
                    for(Servicio s: servicios){                        
            %>
            <tr>
                <td><%= s.getCliente().toString() %></td>
                <td><%= s.getTipoLimpieza().getNombre_tipo() %></td>
                <td><%= s.getFecha() %></td>
                <td><%= s.getHora()%></td>
                <td><%= s.getTiempo_finalizacion()%></td>
                <td><%= s.getObservacion()%></td>
            </tr>
            <%
                }
            }
            %>
        </table>        
    </body>
</html>
