package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Servicios.ServicioManager;
import Clases.Servicio;
import Clases.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import Utils.TimeUtils;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nyath
 */
public class ServicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session != null) {
        
            String accion = request.getParameter("accion");
            ServicioManager manager = new ServicioManager();

            if("buscar".equals(accion)){
                String id_str = request.getParameter("id_servicio");

                try {
                    int id = Integer.parseInt(id_str);

                    Servicio servicio = manager.buscarServicio(id);

                    if(servicio != null) {
                        request.setAttribute("mensaje", "Servicio: " + id + " encontrado.");
                        request.setAttribute("servicio", servicio);
                    } else {
                        request.setAttribute("error", "No se encontro el servicio");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error", "Código invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
            }

            if("crear/modificar".equals(accion)){
                String id_Str = request.getParameter("id_servicio");
                TimeUtils timeU = new TimeUtils();

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");
                        boolean idExist = id_Str != null && !id_Str.isEmpty();
                        int id = idExist ? Integer.parseInt(id_Str) : 0;
                        
                        Servicio servicio = new Servicio();

                        servicio.setId_cliente(Integer.parseInt(request.getParameter("id_cliente")));
                        servicio.setId_equipo(Integer.parseInt(request.getParameter("id_equipo")));
                        servicio.setId_tipo_limp(Integer.parseInt(request.getParameter("id_tipoLimp")));
                        servicio.setFecha(LocalDate.parse(request.getParameter("fecha")));
                        servicio.setHora(LocalTime.parse(request.getParameter("hora")));
                        servicio.setTiempo_estimado(LocalTime.parse(request.getParameter("tiempo_estimado")));
                        servicio.setTiempo_finalizacion(timeU.calcHoraFinalizacion(servicio.getHora(), servicio.getTiempo_estimado()));
                        servicio.setPrecio(Integer.parseInt(request.getParameter("precio")));
                        String obs = request.getParameter("observacion");
                        servicio.setObservacion(obs != null ? obs : "");
                        
                        if(id == 0) {
                            servicio.setUser_crea(user.getUser());
                            boolean creation = manager.insertServicio(servicio);
                            if (creation){
                                request.setAttribute("mensaje", "Servicio creado exitosamente.");
                                request.setAttribute("servicio", null);
                            } else {
                                request.setAttribute("error", "No se pudo crear el servicio");
                            }
                        } else {
                            servicio.setId_servicio(id);
                            servicio.setUser_modifica(user.getUser());
                            servicio.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarServicio(servicio);
                            
                            if (modificacion){
                                request.setAttribute("mensaje", "Servicio modificado exitosamente.");
                                request.setAttribute("servicio", servicio);
                            } else {
                                request.setAttribute("error", "No se pudo modificar el servicio");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Código invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
            }

            if("mostrar".equals(accion)){
                Servicio[] servicios = manager.getAllServicio();

                request.setAttribute("servicios", servicios);
                request.getRequestDispatcher("Pages/listadoServicios.jsp").forward(request, response);
            }

            if("eliminar".equals(accion)){
                String idStr =  request.getParameter("id_servicio");
                try {
                    if (idStr != null && !idStr.isEmpty()) {
                        int id = Integer.parseInt(idStr);

                        boolean eliminado = manager.eliminarServicio(id);
                        if (eliminado) {
                            request.setAttribute("mensaje", "Servicio eliminado exitosamente.");
                            request.setAttribute("servicio", null); // limpiar formulario
                        } else {
                            request.setAttribute("error", "No se pudo eliminar el servicio.");
                        }
                    } else {
                        request.setAttribute("error", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código inválido.");
                }

                request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
            }        
        } else {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
    }

}
