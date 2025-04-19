package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import Servicios.UsuarioManager;
import Clases.Usuario;

/**
 *
 * @author nyath
 */
public class LoginServlet extends HttpServlet {
    
    UsuarioManager user_manager = new UsuarioManager();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("user");
        String password = request.getParameter("pass");
        
        Usuario user = user_manager.verificarCredencial(usuario, password);
        
        if(user != null){
            //login exitoso
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            response.sendRedirect("Pages/inicio.jsp");
        } else {
            //login fallido
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
