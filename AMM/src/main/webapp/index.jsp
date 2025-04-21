<%-- 
    Document   : index
    Created on : 15/04/2025, 9:48:33 p. m.
    Author     : nyath
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Estilos/styles.css"> <!-- Estilos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"> <!-- Iconos -->
</head>
<body>
    <main>
        <header class="header_question">
            <i class="fa-solid fa-circle-question fa-3x" style="color: black;"></i>
        </header>
        <div id="div_login_logo">
            <img src="Img/Logo.jpeg" alt="Logo ALF" id="login_logo">
        </div>
        <div id="login_form">
            <form name="ingreso" action="LoginServlet" method="post">
                <i class="fa-solid fa-user fa-2x"></i>
                <input type="text" id="user" name="user" placeholder="Usuario" required>
                <i class="fa-solid fa-lock fa-2x"></i>
                <input type="password" name="pass" id="pass" placeholder="Contraseña" required>
                
                <button type="submit">Ingresar</button>
                <%
                String error = request.getAttribute("error") != null 
                   ? (String) request.getAttribute("error") 
                   : request.getParameter("error");
                String mensaje = request.getParameter("mensaje");
                %>
                <% if (error != null) { %>
                <p style="color:darkred;"><%= error %></p>
                <% } %>
                <% if (mensaje != null) { %>
                <p style="color:black;"><%= mensaje %></p>
                <% } %>
            </form>
        </div>
    </main>
    <footer id="footer_login">
        <a href="https://www.facebook.com/AlfprofessionalC" target="_blank" class="icon_link">
            <i class="fa-brands fa-instagram fa-5x"></i>
        </a>
        <a href="https://www.instagram.com/alfprofessionalcleaning/" target="_blank" class="icon_link">
            <i class="fa-brands fa-facebook fa-5x"></i>
        </a>
    </footer>
</body>
</html>
