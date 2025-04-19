<%-- 
    Document   : equipos
    Created on : 15/04/2025, 10:41:01 p. m.
    Author     : nyath
--%>

<%@page import="Servicios.UsuarioManager"%>
<%@page import="Clases.EquipoTrabajo"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Equipos de trabajo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css"> <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"> <!-- Iconos -->
</head>
<body>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario"); //obtener datos de sesión
        EquipoTrabajo equipo = (EquipoTrabajo) request.getAttribute("equipo");
        if(user == null){ //si no hay sesión iniciada regresar al index
            response.sendRedirect("../index.jsp");
            return;
        }
        UsuarioManager user_manager = new UsuarioManager();
        Usuario[] empleados = user_manager.getAllUsuario();
        Usuario vacio = user_manager.buscarUsuario("10", "");
        
        Usuario lider = new Usuario();
        Usuario miembro1 = new Usuario();
        Usuario miembro2 = new Usuario();
        
        if(equipo != null){
            if(equipo.getLider() != null){
                lider = user_manager.buscarUsuario(equipo.getLider(), "");
            }
            if(equipo.getMiembro1() != null){
                miembro1 = user_manager.buscarUsuario(equipo.getMiembro1(), "");
            }
            if(equipo.getMiembro2() != null){
                miembro2 = user_manager.buscarUsuario(equipo.getMiembro2(), "");
            }            
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
                    <li class = "actual_page"><a class = "a_actual_page">Equipo de trabajo</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/clientes.jsp">Clientes</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/valores.jsp">Varios</a></li>
                </ul>            
            </div>
        </div> 
    </header>
    <div class="pages_div">
        <div>
            <form class="form_pages" action="<%= request.getContextPath() %>/EquipoServlet" method="post">   
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color:darkblue;"><%= request.getAttribute("error") %></p>
                <% } %>
                <% if (request.getAttribute("mensaje") != null) { %>
                    <p style="color:grey;"><%= request.getAttribute("mensaje") %></p>
                <% } %>
                <div class="form_display">                    
                    <label for="id_equipo">Código</label>
                    <input type="text" name="id_equipo" id="id_equipo" value="<%= equipo != null ? equipo.getCodigo() : "" %>">
                    <label for="nombre_equipo">Nombre de Equipo</label>
                    <input type="text" name="nombre_equipo" id="nombre_equipo" value="<%= equipo != null ? equipo.getNombre_equipo() : "" %>">
                    <label for="lider">Lider</label>
                    <select name="lider" id="lider">
                        <option value="<%= equipo != null ? equipo.getLider() : vacio.getCodigo() %>"><%= equipo != null && lider != null ? user_manager.UsuarioToString(lider) : user_manager.UsuarioToString(vacio) %></option>
                        <%
                            for (Usuario l : empleados){
                                if (equipo == null || !l.getCodigo().equals(equipo.getLider())){
                        %>
                        <option value="<%= l.getCodigo() %>">
                            <%= user_manager.UsuarioToString(l) %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="miembro1">Miembro</label>
                    <select name="miembro1" id="miembro1">
                        <option value="<%= equipo != null ? equipo.getMiembro1(): vacio.getCodigo() %>"><%= equipo != null && miembro1 != null ? user_manager.UsuarioToString(miembro1) : user_manager.UsuarioToString(vacio) %></option>
                        <%
                            for (Usuario l : empleados){
                                if (equipo == null || !l.getCodigo().equals(equipo.getMiembro1())){
                        %>
                        <option value="<%= l.getCodigo() %>">
                            <%= user_manager.UsuarioToString(l) %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="miembro2">Miembro</label>
                    <select name="miembro2" id="miembro2">
                        <option value="<%= equipo != null ? equipo.getMiembro2(): vacio.getCodigo() %>"><%= equipo != null && miembro2 != null ? user_manager.UsuarioToString(miembro2) : user_manager.UsuarioToString(vacio) %></option>
                        <%
                            for (Usuario l : empleados){
                                if (equipo == null || !l.getCodigo().equals(equipo.getMiembro2())){
                        %>
                        <option value="<%= l.getCodigo() %>">
                            <%= user_manager.UsuarioToString(l) %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
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
<!--<script type="module" src="../Js/equipos.js"></script>-->
</html>
