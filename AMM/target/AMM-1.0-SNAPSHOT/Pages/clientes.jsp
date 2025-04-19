<%-- 
    Document   : clientes
    Created on : 15/04/2025, 10:39:07 p. m.
    Author     : nyath
--%>

<%@page import="Clases.Cliente"%>
<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css"> <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"> <!-- Iconos -->
</head>
<body>
    <%
        Usuario user = (Usuario) session.getAttribute("usuario"); //obtener datos de sesión
        Cliente cliente = (Cliente) request.getAttribute("cliente");
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
                    <li class = "actual_page"><a class = "a_actual_page">Clientes</a></li>
                    <li><a href="<%= request.getContextPath() %>/Pages/valores.jsp">Varios</a></li>
                </ul>            
            </div>
        </div>
    </header>
    <div class="pages_div">
        <div>
            <form class="form_pages" action="<%= request.getContextPath() %>/ClienteServlet" method="post">
                <% if (request.getAttribute("error") != null) { %>
                    <p style="color:darkblue;"><%= request.getAttribute("error") %></p>
                <% } %>
                <% if (request.getAttribute("mensaje") != null) { %>
                    <p style="color:grey;"><%= request.getAttribute("mensaje") %></p>
                <% } %>
                <div class="form_display">  
                    <label for="id_cliente">Código</label>
                    <input type="text" name="id_cliente" id="id_cliente" value="<%= cliente != null ? cliente.getCodigo() : "" %>">                  
                    <label for="nombre_cliente">Nombre</label>
                    <input type="text" name="nombre_cliente" id="nombre_cliente" value="<%= cliente != null ? cliente.getNombres() : "" %>">
                    <label for="apellido_cliente">Apellido</label>
                    <input type="text" name="apellido_cliente" id="apellido_cliente" value="<%= cliente != null ? cliente.getApellidos() : "" %>">
                    <label for="direccion_cliente">Dirección</label>
                    <input type="text" name="direccion_cliente" id="direccion_cliente" value="<%= cliente != null ? cliente.getDireccion() : "" %>">
                    <label for="telefono_cliente">Telefono</label>
                    <input type="tel" name="telefono_cliente" id="telefono_cliente" value="<%= cliente != null ? cliente.getTelefono() : "" %>">
                    <label for="correo_cliente">Telefono</label>
                    <input type="tel" name="correo_cliente" id="correo_cliente" value="<%= cliente != null ? cliente.getEmail() : "" %>">
                    <label for="observacion_cliente">Observaciones</label>
                    <input type="text" name="observacion_cliente" id="observacion_cliente" value="<%= cliente != null ? cliente.getObservaciones() : "" %>">
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
<!--<script type="module" src="../Js/clientes.js"></script>-->
</html>
