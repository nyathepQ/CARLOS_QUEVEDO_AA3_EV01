package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nyath
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null){
            session.invalidate(); //cerrar la sesión
        }        
        response.sendRedirect(request.getContextPath() + "/index.jsp?mensaje=Haz+cerrado+sesion");
    }
}
