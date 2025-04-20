<%-- 
    Document   : agenda
    Created on : 15/04/2025, 10:37:16 p. m.
    Author     : nyath
--%>

<%@page import="Clases.Tipo_limpieza"%>
<%@page import="Servicios.TiLiManager"%>
<%@page import="Servicios.EquipoManager"%>
<%@page import="Clases.Equipo"%>
<%@page import="java.util.List"%>
<%@page import="Servicios.ServicioManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Clases.Usuario"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Cliente"%>
<%@page import="Servicios.ClienteManager"%>
<%@page import="Utils.TimeUtils"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css"> <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"> <!-- Iconos -->
</head>
<body>    
    <%
        Usuario user = (Usuario) session.getAttribute("usuario"); //obtener datos de sesión
        Servicio serv = (Servicio) request.getAttribute("servicio");
        if(user == null && user.getTipoUsuario().getId_tipoUsua() != 0){ //si no hay sesión iniciada regresar al index
            response.sendRedirect("../index.jsp");
            return;
        }
        //lista de todos los servicios para select
        ServicioManager sManager = new ServicioManager();
        List<Servicio> servicios_list = sManager.getAllServicio();
        //Listas para select
        ClienteManager cl_manager = new ClienteManager();
        List<Cliente> cl = cl_manager.getAllCliente();
        EquipoManager eqManager = new EquipoManager();
        List<Equipo> eq = eqManager.getAllEquipo();
        TiLiManager tlManager = new TiLiManager();
        List<Tipo_limpieza> ti_li = tlManager.getAllTipoLimpieza();
    %>
    <header class="header_pages"> <!-- header con el logo y los enlaces a las demás paginas -->
        <div class="iconUserName">
            <a href="https://wa.me/573212300716" target="_blank">
                <i class="fa-solid fa-circle-question fa-2x question_icon" style="color: black;"></i>
            </a>
            <p class ="name_user_show">
                <%= user != null ? user.getNombre_usuario(): "Invitado" %>
            </p>            
        </div>
        <div class="logo_list">
            <a href="<%= request.getContextPath() %>/Pages/inicio.jsp"><img src="<%= request.getContextPath() %>/Img/Logo.jpeg" alt="Logo ALF" class="logo_pages"></a>
            <div class="list_pages">
                <ul>
                    <li class = "actual_page"><a class = "a_actual_page">Agenda</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/empleados.jsp">Empleados</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/equipos.jsp">Equipo de trabajo</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/clientes.jsp">Clientes</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/valores.jsp">Varios</a></li>
                </ul>            
            </div>
        </div>       
    </header>
    <div class="pages_div">        
        <div>
            <form class="form_pages" action="<%= request.getContextPath() %>/ServicioServlet" method="post"> <!-- form con todos los datos -->
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color:darkblue;"><%= request.getAttribute("error") %></p>
                <% } %>
                <% if (request.getAttribute("mensaje") != null) { %>
                    <p style="color:grey;"><%= request.getAttribute("mensaje") %></p>
                <% } %>
                <div class="form_display">
                    <label for="id_servicio">Código</label>
                    <select name="id_servicio" id="id_servicio">
                        <option value="<%= serv != null ? serv.getId_servicio() : "NA"  %>"><%= serv != null ? serv.getId_servicio() : "== Nuevo registro ==" %></option>
                        <%
                            if(serv != null){
                        %>
                        <option value = "NA">== Nuevo registro ==</option>
                        <%
                            }
                        %>
                        <%
                            for (Servicio sv : servicios_list){
                                if (serv == null || serv.getId_servicio() != sv.getId_servicio()){
                        %>
                        <option value="<%= sv.getId_servicio() %>">
                            <%= sv.getId_servicio() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="id_cliente">Cliente</label>
                    <select name="id_cliente" id="id_cliente">
                        <option value="<%= serv != null && serv.getCliente() != null ? serv.getCliente().getId_cliente() : "" %>"><%= serv != null && serv.getCliente() != null ? serv.getCliente().toString() : "--Seleccione un cliente--" %></option>
                        <%
                            for (Cliente c : cl){
                                if (serv == null || c.getId_cliente() != serv.getCliente().getId_cliente()){
                        %>
                        <option value="<%= c.getId_cliente() %>">
                            <%= c.toString() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="id_equipo">Equipo</label>
                    <select name="id_equipo" id="id_equipo">
                        <option selected="true" value="<%= serv != null && serv.getEquipo() != null ? serv.getEquipo().getId_equipo() : "" %>"><%= serv != null && serv.getEquipo() != null ? serv.getEquipo().getNombre_equipo() : "--Seleccione un equipo--" %></option>
                        <%
                            for (Equipo e : eq){
                                if (serv == null || e.getId_equipo() != serv.getEquipo().getId_equipo()){
                        %>
                        <option value="<%= e.getId_equipo() %>">
                            <%= e.getNombre_equipo() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="fecha">Fecha</label>
                    <input type="date" name="fecha" id="fecha" value="<%= serv != null ? serv.getFecha() : "" %>">
                    <label for="hora">Hora</label>
                    <input type="time" name="hora" id="hora" value="<%= serv != null ? serv.getHora() : "" %>">
                    <label for="tiempo_estimado">Tiempo estimado</label>
                    <input type="time" name="tiempo_estimado" id="tiempo_estimado" value="<%= serv != null ? serv.getTiempo_estimado() : "" %>">
                    <label for="tiempo_finalizacion">Finalización</label>
                    <input type="time" name="tiempo_finalizacion" id="tiempo_finalizacion" value="<%= serv != null ? serv.getTiempo_finalizacion() : "" %>" disabled>
                    <label for="id_tipoLimp">Limpieza</label>
                    <select name="id_tipoLimp" id="id_tipoLimp">
                        <option selected="true" value="<%= serv != null && serv.getTipoLimpieza() != null ? serv.getTipoLimpieza().getId_tipoLimp(): "" %>"><%= serv != null && serv.getTipoLimpieza() != null ? serv.getTipoLimpieza().getNombre_tipo() : "--Seleccione un tipo de limpieza--"%></option>
                        <%
                            for (Tipo_limpieza tl : ti_li){
                                if (serv == null || tl.getId_tipoLimp() != serv.getTipoLimpieza().getId_tipoLimp()){
                        %>
                        <option value="<%= tl.getId_tipoLimp() %>">
                            <%= tl.getNombre_tipo() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="precio">Valor a pagar</label>
                    <input type="number" name="precio" id="precio" value="<%= serv != null ? serv.getPrecio() : "" %>">
                    <label for="observacion">Observaciones</label>
                    <input type="text" name="observacion" id="observacion" value="<%= serv != null ? serv.getObservacion() : "" %>">
                </div>
                <div>
                    <button type="submit" name="accion" value="buscar">Buscar</button>
                    <button type="submit" name="accion" value="crear/modificar">Crear / Modificar</button>
                    <button type="submit" name="accion" value="mostrar">Mostrar registros</button>
                    <button type="submit" name="accion" value="eliminar">Eliminar</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>