<%-- 
    Document   : listadoServicios
    Created on : 16/04/2025, 10:33:53 p. m.
    Author     : nyath
--%>

<%@page import="java.util.List"%>
<%@page import="Clases.Servicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de servicios</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            List<Servicio> servicios = (List<Servicio>) request.getAttribute("servicios");
        %>
        <h2>Listado de Servicios</h2>
        
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Equipo</th>
                <th>Tipo limpieza</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Tiempo estimado</th>
                <th>Hora finalización</th>
                <th>Valor</th>
                <th>Observaciones</th>
            </tr>
            
            <%
                if(servicios != null){
                    for(Servicio s: servicios){                        
            %>
            <tr>
                <td><%= s.getId_servicio() %></td>
                <td><%= s.getCliente().toString() %></td>
                <td><%= s.getEquipo().getNombre_equipo() %></td>
                <td><%= s.getTipoLimpieza().getNombre_tipo() %></td>
                <td><%= s.getFecha() %></td>
                <td><%= s.getHora()%></td>
                <td><%= s.getTiempo_estimado()%></td>
                <td><%= s.getTiempo_finalizacion()%></td>
                <td><%= s.getPrecio()%></td>
                <td><%= s.getObservacion()%></td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>