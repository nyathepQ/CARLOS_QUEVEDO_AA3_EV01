package controladores;

import Clases.Equipo;
import Clases.Usuario;
import Servicios.EquipoManager;
import Utils.TimeUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author nyath
 */
public class EquipoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion"); //obtener botón activado
        EquipoManager manager = new EquipoManager();

        if("buscar".equals(accion)){
            String id_str = request.getParameter("id_equipo"); //obtener id del input

            try {
                int id = Integer.parseInt(id_str);
                Equipo equipo = manager.buscarEquipo(id);

                if(equipo == null) {
                    request.setAttribute("error", "Debe seleccionar un código para buscar");
                } else {
                    request.setAttribute("mensaje", "Equipo encontrado: " + id);
                    request.setAttribute("equipo", equipo);
                }
            } catch (NumberFormatException e){
                request.setAttribute("error", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
        }

        if("crear/modificar".equals(accion)){ //Botón crear/modificar
            String id_str = request.getParameter("id_equipo"); //Obtener id del input

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario"); //Obtener usuario de la sesión                        
                    Equipo equipo = new Equipo();
                    boolean idExist = id_str != null && !"NA".equals(id_str);
                    int id = idExist ? Integer.parseInt(id_str) : 0;

                    equipo.setNombre_equipo(request.getParameter("nombre_equipo"));
                    equipo.setLider(request.getParameter("lider"));
                    equipo.setMiembro1(request.getParameter("miembro1"));
                    equipo.setMiembro2(request.getParameter("miembro2"));

                    if(id == 0) { //crear registro
                        equipo.setUser_crea(user.getNombre_usuario());
                        boolean creation = manager.crearEquipo(equipo);
                        if (creation){
                            request.setAttribute("mensaje", "Equipo creado exitosamente");
                            request.setAttribute("equipo", null);
                        } else {
                            request.setAttribute("error", "No se pudo crear el equipo");
                        }
                    } else { //modificar registro
                        TimeUtils timeU = new TimeUtils();
                        equipo.setId_equipo(id);
                        equipo.setUser_modifica(user.getNombre_usuario());
                        equipo.setModificado_el(timeU.getNowTime()); //Timestamp de modificación
                        boolean modificacion = manager.modificarEquipo(equipo);                            
                        if (modificacion){
                            request.setAttribute("mensaje", "Equipo modificado exitosamente");
                            request.setAttribute("equipo", equipo);
                        } else {
                            request.setAttribute("error", "No se pudo modificar el equipo");
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
        }

        if("mostrar".equals(accion)){ //Botón mostrar
            List<Equipo> equipos = manager.getAllEquipo(); //Obtener todos los registros

            request.setAttribute("equipos", equipos); //Enviar información a la pagina de tabla
            request.getRequestDispatcher("Pages/listadoEquipos.jsp").forward(request, response);
        }

        if("eliminar".equals(accion)){ //Botón eliminar
            String id_str =  request.getParameter("id_equipo"); //Obtener id de inputs
            try {
                if (id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error", "Debe ingresar un código para eliminar");
                } else {
                    int id = Integer.parseInt(id_str);

                    boolean eliminado = manager.eliminarEquipo(id);
                    if (eliminado) {
                        request.setAttribute("mensaje", "Equipo eliminado exitosamente");
                        request.setAttribute("empleado", null); // limpiar formulario
                    } else {
                        request.setAttribute("error", "No se pudo eliminar el equipo, confirme que no este ligado a ningún servicio");
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Código inválido o inexistente");
            }

        request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
        }        
    }
}
