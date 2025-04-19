package controladores;

import Clases.EquipoTrabajo;
import Clases.Usuario;
import Servicios.EquipoTrabajoManager;
import Utils.TimeUtils;
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
public class EquipoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null) {
        
            String accion = request.getParameter("accion");
            EquipoTrabajoManager manager = new EquipoTrabajoManager();

            if("buscar".equals(accion)){
                String id_str = request.getParameter("id_equipo");
                

                try {
                    int id = Integer.parseInt(id_str);
                    EquipoTrabajo equipo = manager.buscarEquipo(id);

                    if(equipo != null) {
                        request.setAttribute("mensaje", "Equipo encontrado: " + id);
                        request.setAttribute("equipo", equipo);
                    } else {
                        request.setAttribute("error", "No se encontro el equipo");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error", "Código de equipo invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
            }

            if("crear/modificar".equals(accion)){
                String id_Str = request.getParameter("id_equipo");

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");                        
                        EquipoTrabajo equipo = new EquipoTrabajo();
                        boolean idExist = id_Str != null && !id_Str.isEmpty();
                        int id = idExist ? Integer.parseInt(id_Str) : 0;

                        equipo.setNombre_equipo(request.getParameter("nombre_equipo"));
                        equipo.setLider(request.getParameter("lider"));
                        equipo.setMiembro1(request.getParameter("miembro1"));
                        equipo.setMiembro2(request.getParameter("miembro2"));
                        
                        if(id == 0) {
                            equipo.setUser_crea(user.getUser());
                            boolean creation = manager.insertEquipo(equipo);
                            if (creation){
                                request.setAttribute("mensaje", "Equipo creado exitosamente.");
                                request.setAttribute("equipo", null);
                            } else {
                                request.setAttribute("error", "No se pudo crear el equipo");
                            }
                        } else {
                            TimeUtils timeU = new TimeUtils();
                            equipo.setCodigo(id);
                            equipo.setUser_modifica(user.getUser());
                            equipo.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarEquipo(equipo);                            
                            if (modificacion){
                                request.setAttribute("mensaje", "Equipo modificado exitosamente.");
                                request.setAttribute("equipo", equipo);
                            } else {
                                request.setAttribute("error", "No se pudo modificar el equipo");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Código de equipo invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
            }

            if("mostrar".equals(accion)){
                EquipoTrabajo[] equipos = manager.getAllEquipo();

                request.setAttribute("equipos", equipos);
                request.getRequestDispatcher("Pages/listadoEquipos.jsp").forward(request, response);
            }

            if("eliminar".equals(accion)){
                String id_str =  request.getParameter("id_equipo");
                try {
                    if (id_str != null && !id_str.isEmpty()) {
                        int id = Integer.parseInt(id_str);
                        
                        boolean eliminado = manager.eliminarEquipo(id);
                        if (eliminado) {
                            request.setAttribute("mensaje", "Equipo eliminado exitosamente.");
                            request.setAttribute("empleado", null); // limpiar formulario
                        } else {
                            request.setAttribute("error", "No se pudo eliminar el equipo, confirme que no este ligado a ningún servicio");
                        }
                    } else {
                        request.setAttribute("error", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código inválido o inexistente.");
                }

                request.getRequestDispatcher("Pages/equipos.jsp").forward(request, response);
            }        
        } else {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
    }
}
