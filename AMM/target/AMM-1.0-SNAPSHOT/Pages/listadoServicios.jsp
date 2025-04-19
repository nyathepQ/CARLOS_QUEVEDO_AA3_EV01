<%-- 
    Document   : listadoServicios
    Created on : 16/04/2025, 10:33:53 p. m.
    Author     : nyath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Cliente"%>
<%@page import="Servicios.ClienteManager"%>
<%@page import="Clases.EquipoTrabajo"%>
<%@page import="Servicios.EquipoTrabajoManager"%>
<%@page import="Clases.Varios"%>
<%@page import="Servicios.VariosManager"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de servicios</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css">
    </head>
    <body>
        <%
            Servicio[] servicios = (Servicio[]) request.getAttribute("servicios");
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
                    ClienteManager cl = new ClienteManager();
                    EquipoTrabajoManager eq = new EquipoTrabajoManager();
                    VariosManager vr = new VariosManager();
                    
                    for(Servicio s: servicios){
                        Cliente clien = cl.buscarCliente(s.getId_cliente());
                        EquipoTrabajo equip = eq.buscarEquipo(s.getId_equipo());
                        Varios tl = vr.buscarTipoVarios("tipo_limpieza", "id_tipoLimp", s.getId_tipo_limp());
            %>
            <tr>
                <td><%= s.getId_servicio() %></td>
                <td><%= cl.clienteToString(clien) %></td>
                <td><%= equip.getNombre_equipo() %></td>
                <td><%= tl.getNombre() %></td>
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