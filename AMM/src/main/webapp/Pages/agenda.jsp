<%-- 
    Document   : agenda
    Created on : 15/04/2025, 10:37:16 p. m.
    Author     : nyath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Clases.Usuario"%>
<%@page import="Clases.Servicio"%>
<%@page import="Clases.Cliente"%>
<%@page import="Clases.EquipoTrabajo"%>
<%@page import="Clases.Varios"%>
<%@page import="Servicios.ClienteManager"%>
<%@page import="Servicios.EquipoTrabajoManager"%>
<%@page import="Servicios.VariosManager"%>
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
        if(user == null){ //si no hay sesión iniciada regresar al index
            response.sendRedirect("../index.jsp");
            return;
        }
        ClienteManager cl_manager = new ClienteManager();
        EquipoTrabajoManager eq_manager = new EquipoTrabajoManager();
        VariosManager tipos_manager = new VariosManager();
        Cliente[] cl = cl_manager.getAllCliente();
        EquipoTrabajo[] eq = eq_manager.getAllEquipo();
        Varios[] ti_li = tipos_manager.getAllTipoVarios("tipo_limpieza", "id_tipoLimp");
        
        Cliente cl_serv = new Cliente();
        EquipoTrabajo eq_serv = new EquipoTrabajo();
        Varios limp_serv = new Varios();
        
        if(serv != null){
            cl_serv = cl_manager.buscarCliente(serv.getId_cliente());
            eq_serv = eq_manager.buscarEquipo(serv.getId_equipo());
            limp_serv = tipos_manager.buscarTipoVarios("tipo_limpieza", "id_tipoLimp", serv.getId_tipo_limp());
        }
        
    %>
    <header class="header_pages"> <!-- header con el logo y los enlaces a las demás paginas -->
        <div class="iconUserName">
            <a href="https://wa.me/573212300716" target="_blank">
                <i class="fa-solid fa-circle-question fa-2x question_icon" style="color: black;"></i>
            </a>
            <p class ="name_user_show">
                <%= user != null ? user.getUser() : "Invitado" %>
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
                    <input type="text" name="id_servicio" id="id_servicio" value="<%= serv != null ? serv.getId_servicio() : "" %>">
                    <label for="id_cliente">Cliente</label>
                    <select name="id_cliente" id="id_cliente">
                        <option value="<%= serv != null ? serv.getId_cliente() : "" %>"><%= serv != null && cl_serv != null ? cl_manager.clienteToString(cl_serv) : "--Seleccione un cliente--" %></option>
                        <%
                            for (Cliente c : cl){
                                if (serv == null || !c.getCodigo().equals(String.valueOf(serv.getId_equipo()))){
                        %>
                        <option value="<%= c.getCodigo() %>">
                            <%= cl_manager.clienteToString(c) %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="id_equipo">Equipo</label>
                    <select name="id_equipo" id="id_equipo">
                        <option selected="true" value="<%= serv != null ? serv.getId_equipo(): "" %>"><%= serv != null && eq_serv != null ? eq_serv.getNombre_equipo() : "--Seleccione un equipo--" %></option>
                        <%
                            for (EquipoTrabajo e : eq){
                                if (serv == null || e.getCodigo() != serv.getId_equipo()){
                        %>
                        <option value="<%= e.getCodigo() %>">
                            <%= e.getNombre_equipo() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="fecha">Fecha</label>
                    <input type="date" name="fecha" id="fecha" value="<%= serv != null ? java.sql.Date.valueOf(serv.getFecha()) : "" %>">
                    <label for="hora">Hora</label>
                    <input type="time" name="hora" id="hora" value="<%= serv != null ? java.sql.Time.valueOf(serv.getHora()) : "" %>">
                    <label for="tiempo_estimado">Tiempo estimado</label>
                    <input type="time" name="tiempo_estimado" id="tiempo_estimado" value="<%= serv != null ? java.sql.Time.valueOf(serv.getTiempo_estimado()) : "" %>">
                    <label for="tiempo_finalizacion">Finalización</label>
                    <input type="time" name="tiempo_finalizacion" id="tiempo_finalizacion" value="<%= serv != null ? java.sql.Time.valueOf(serv.getTiempo_finalizacion()) : "" %>" disabled>
                    <label for="id_tipoLimp">Limpieza</label>
                    <select name="id_tipoLimp" id="id_tipoLimp">
                        <option selected="true" value="<%= serv != null ? serv.getId_tipo_limp(): "" %>"><%= serv != null && limp_serv != null ? limp_serv.getNombre() : "--Seleccione un tipo de limpieza--"%></option>
                        <%
                            for (Varios tl : ti_li){
                                if (serv == null || tl.getCodigo() != serv.getId_tipo_limp()){
                        %>
                        <option value="<%= tl.getCodigo() %>">
                            <%= tl.getNombre() %>
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