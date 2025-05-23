<%-- 
    Document   : valores
    Created on : 15/04/2025, 10:42:55 p. m.
    Author     : nyath
--%>

<%@page import="Clases.Varios"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css"> <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"> <!-- Iconos -->
</head>
<body>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario"); //obtener datos de sesión
        Varios tipo_docu = (Varios) request.getAttribute("tipo_docu");
        Varios tipo_limp = (Varios) request.getAttribute("tipo_limp");
        if(user == null){ //si no hay sesión iniciada regresar al index
            response.sendRedirect("../index.jsp");
            return;
        }        
    %>
    <header class="header_pages">
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
                    <li><a href="<%= request.getContextPath() %>/Pages/agenda.jsp">Agenda</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/empleados.jsp">Empleados</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/equipos.jsp">Equipo de trabajo</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/clientes.jsp">Clientes</a></li>
                    <li class = "actual_page"><a class = "a_actual_page">Tipos</a></li>
                </ul>            
            </div>
        </div>
    </header>
    <div class="pages_div">
        <div>            
            <form class="form_pages" action="<%= request.getContextPath() %>/TiposServlet" method="post">
                <% if (request.getAttribute("error_docu") != null) { %>
                    <p style="color:darkblue;"><%= request.getAttribute("error_docu") %></p>
                <% } %>
                <% if (request.getAttribute("mensaje_docu") != null) { %>
                    <p style="color:grey;"><%= request.getAttribute("mensaje_docu") %></p>
                <% } %>
                <h2>Tipo de documento</h2>
                <div class="form_display">
                    <label for="id_tipoDocu">Código</label>
                    <input type="text" name="id_tipoDocu" id="id_tipoDocu" value="<%= tipo_docu != null ? tipo_docu.getCodigo() : "" %>">
                    <label for="nombre_tipoD">Nombre</label>
                    <input type="text" name="nombre_tipoD" id="nombre_tipoD" value="<%= tipo_docu != null ? tipo_docu.getNombre(): "" %>">
                </div>
                <div>
                    <button type="submit" name="accion" value="buscar_d">Buscar</button>
                    <button type="submit" name="accion" value="crear/modificar_d">Crear / Modificar</button>
                    <button type="submit" name="accion" value="mostrar_d">Mostrar registros</button>
                    <button type="submit" name="accion" value="eliminar_d">Eliminar</button>
                </div>
            </form>
        </div>
        <div>            
            <form class="form_pages" action="<%= request.getContextPath() %>/TiposServlet" method="post">
                <% if (request.getAttribute("error_limp") != null) { %>
                    <p style="color:darkblue;"><%= request.getAttribute("error_limp") %></p>
                <% } %>
                <% if (request.getAttribute("mensaje_limp") != null) { %>
                    <p style="color:grey;"><%= request.getAttribute("mensaje_limp") %></p>
                <% } %>
                <h2>Tipo de Limpieza</h2>
                <div class="form_display">
                    <label for="id_tipoLimp">Código</label>
                    <input type="text" name="id_tipoLimp" id="id_tipoLimp" value="<%= tipo_limp != null ? tipo_limp.getCodigo() : "" %>">
                    <label for="nombre_tipoL">Nombre</label>
                    <input type="text" name="nombre_tipoL" id="nombre_tipoL" value="<%= tipo_limp != null ? tipo_limp.getNombre() : "" %>">
                </div>
                <div>
                    <button type="submit" name="accion" value="buscar_l">Buscar</button>
                    <button type="submit" name="accion" value="crear/modificar_l">Crear / Modificar</button>
                    <button type="submit" name="accion" value="mostrar_l">Mostrar registros</button>
                    <button type="submit" name="accion" value="eliminar_l">Eliminar</button>
                </div>
            </form>
        </div>
        <div id="form_tipo_usuario">            
            <form class="form_pages" action="<%= request.getContextPath() %>/TiposServlet" method="get">                
                <h2>Tipo de empleado</h2>
                <div>
                    <button type="submit" name="accion" value="mostrar_u">Mostrar registros</button>
                </div>
            </form>
        </div>        
    </div>
</body>
</html>
