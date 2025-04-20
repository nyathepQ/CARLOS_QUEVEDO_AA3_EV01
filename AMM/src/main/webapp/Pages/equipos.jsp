<%-- 
    Document   : equipos
    Created on : 15/04/2025, 10:41:01 p. m.
    Author     : nyath
--%>

<%@page import="Servicios.EquipoManager"%>
<%@page import="java.util.List"%>
<%@page import="Servicios.UsuarioManager"%>
<%@page import="Clases.Equipo"%>
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
        Equipo equipo = (Equipo) request.getAttribute("equipo");
        if(user == null){ //si no hay sesión iniciada regresar al index
            response.sendRedirect("../index.jsp");
            return;
        }
        //lista de usuarios para select
        UsuarioManager usManager = new UsuarioManager();
        List<Usuario> allUser = usManager.getAllUsuario();
        //lista de equipos para select
        EquipoManager eqManager = new EquipoManager();
        List<Equipo> equipos_lista = eqManager.getAllEquipo();
    %>
    <header class="header_pages">
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
                    <select name="id_equipo" id="id_equipo">
                        <option value="<%= equipo != null ? equipo.getId_equipo() : "NA"  %>"><%= equipo != null ? equipo.getId_equipo() : "== Nuevo registro ==" %></option>
                        <%
                            if(equipo != null){
                        %>
                        <option value = "NA">== Nuevo registro ==</option>
                        <%
                            }
                        %>
                        <%
                            for (Equipo eq : equipos_lista){
                                if (equipo == null || equipo.getId_equipo() != eq.getId_equipo()){
                        %>
                        <option value="<%= eq.getId_equipo() %>">
                            <%= eq.getId_equipo() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="nombre_equipo">Nombre de Equipo</label>
                    <input type="text" name="nombre_equipo" id="nombre_equipo" value="<%= equipo != null ? equipo.getNombre_equipo() : "" %>">
                    <label for="lider">Lider</label>
                    <select name="lider" id="lider">
                        <%
                                Usuario lider = null;
                                Usuario miembro1 = null;
                                Usuario miembro2 = null;
                                
                                if(equipo != null){
                                lider = equipo.getUsuariosEquipos().get(0).getUsuario();
                                miembro1 = equipo.getUsuariosEquipos().get(1).getUsuario();
                                miembro2 = equipo.getUsuariosEquipos().get(2).getUsuario();
                            }
                        %>
                        <option value="<%= equipo != null && lider != null ? equipo.getLider() : "NA" %>"><%= equipo != null && lider != null ? lider.toString() : "== Seleccione empleado ==" %></option>
                        <%
                            for (Usuario l : allUser){
                                if (equipo == null || !l.getId_usuario().equals(equipo.getLider())){
                        %>
                        <option value="<%= l.getId_usuario() %>">
                            <%= l.toString() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="miembro1">Miembro</label>
                    <select name="miembro1" id="miembro1">
                        <option value="<%= equipo != null && miembro1 != null ? equipo.getMiembro1() : "NA" %>"><%= equipo != null && miembro1 != null ? miembro1.toString() : "== Seleccione empleado ==" %></option>
                        <%
                            for (Usuario l : allUser){
                                if (equipo == null || !l.getId_usuario().equals(equipo.getMiembro1())){
                        %>
                        <option value="<%= l.getId_usuario() %>">
                            <%= l.toString() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                    <label for="miembro2">Miembro</label>
                    <select name="miembro2" id="miembro2">
                        <option value="<%= equipo != null && miembro2 != null ? equipo.getMiembro2() : "NA" %>"><%= equipo != null && miembro2 != null ? miembro2.toString() : "== Seleccione empleado ==" %></option>
                        <%
                            for (Usuario l : allUser){
                                if (equipo == null || !l.getId_usuario().equals(equipo.getMiembro2())){
                        %>
                        <option value="<%= l.getId_usuario() %>">
                            <%= l.toString() %>
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
