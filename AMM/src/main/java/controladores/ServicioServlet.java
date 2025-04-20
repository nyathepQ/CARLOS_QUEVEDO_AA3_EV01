package controladores;

import Clases.Cliente;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Servicios.ServicioManager;
import Clases.Servicio;
import Clases.Usuario;
import Servicios.ClienteManager;
import Servicios.EquipoManager;
import Servicios.TiLiManager;
import java.sql.Date;
import java.sql.Time;
import Utils.TimeUtils;
import jakarta.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author nyath
 */
public class ServicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion"); //Obtener botón activado
        ServicioManager manager = new ServicioManager();

        if("buscar".equals(accion)){ //Botón buscar
            String id_str = request.getParameter("id_servicio");

            try {
                if(id_str == null || "NA".equals(id_str)){
                    request.setAttribute("error", "Debe seleccionar un código para buscar");
                } else { //si existe el id o es diferente a NA
                    int id = Integer.parseInt(id_str);
                    Servicio servicio = manager.buscarServicio(id);
                    
                    request.setAttribute("mensaje", "Servicio encontrado: " + id);
                    request.setAttribute("servicio", servicio);
                }
            } catch (NumberFormatException e){
                request.setAttribute("error", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
        }

        if("crear/modificar".equals(accion)){ //Botón crear/modificar
            String id_str = request.getParameter("id_servicio");
            TimeUtils timeU = new TimeUtils();

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario");
                    ClienteManager clManager = new ClienteManager();
                    EquipoManager eqManager = new EquipoManager();
                    TiLiManager tlManager = new TiLiManager();
                    boolean idExist = id_str != null && !"NA".equals(id_str);
                    int id = idExist ? Integer.parseInt(id_str) : 0;

                    Servicio servicio = new Servicio();
                    
                    servicio.setCliente(clManager.buscarCliente(Integer.parseInt(request.getParameter("id_cliente")))); //Settear cliente por id
                    servicio.setEquipo(eqManager.buscarEquipo(Integer.parseInt(request.getParameter("id_equipo")))); //Settear equipo por id
                    servicio.setTipoLimpieza(tlManager.buscarTipoLimpieza(Integer.parseInt(request.getParameter("id_tipoLimp")))); //Settear tipo limpieza por id
                    servicio.setFecha(Date.valueOf(request.getParameter("fecha")));
                    LocalTime hr = LocalTime.parse(request.getParameter("hora")); //Transformar a localTime
                    servicio.setHora(Time.valueOf(hr));
                    LocalTime hrEs = LocalTime.parse(request.getParameter("tiempo_estimado")); //Transformar a localTime
                    servicio.setTiempo_estimado(Time.valueOf(hrEs));
                    servicio.setTiempo_finalizacion(timeU.calcHoraFinalizacion(servicio.getHora(), servicio.getTiempo_estimado()));
                    servicio.setPrecio(Integer.parseInt(request.getParameter("precio")));
                    servicio.setObservacion(request.getParameter("observacion"));
                    
                    if(id == 0) { // Creación
                        servicio.setUser_crea(user.getNombre_usuario());
                        boolean creation = manager.crearServicio(servicio);
                        if (!creation){
                            request.setAttribute("error", "No se pudo crear el servicio");
                        } else {
                            request.setAttribute("mensaje", "Servicio creado exitosamente");
                            request.setAttribute("servicio", null);
                        }
                    } else { // Modificación
                        servicio.setId_servicio(id);
                        servicio.setUser_modifica(user.getNombre_usuario());
                        servicio.setModificado_el(timeU.getNowTime());
                        boolean modificacion = manager.modificarServicio(servicio);

                        if (!modificacion){
                            request.setAttribute("error", "No se pudo modificar el servicio");
                        } else {
                            request.setAttribute("mensaje", "Servicio modificado exitosamente.");
                            request.setAttribute("servicio", servicio);
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
        }

        if("mostrar".equals(accion)){ // Botón mostrar
            List<Servicio> servicios = manager.getAllServicio(); //obtener todos los servicios

            request.setAttribute("servicios", servicios); // Enviar registros a pagina de lista
            request.getRequestDispatcher("Pages/listadoServicios.jsp").forward(request, response);
        }

        if("eliminar".equals(accion)){ //Botón eliminar
            String id_str =  request.getParameter("id_servicio");
            try {
                if (id_str == null || "NA".equals(id_str)) { // id de servicio no existe o es NA
                    request.setAttribute("error", "Debe ingresar un código para eliminar.");
                } else { // si la id es valida
                    int id = Integer.parseInt(id_str);

                    boolean eliminado = manager.eliminarServicio(id);
                    if (!eliminado) {
                        request.setAttribute("error", "No se pudo eliminar el servicio");
                    } else {
                        request.setAttribute("mensaje", "Servicio eliminado exitosamente");
                        request.setAttribute("servicio", null); // limpiar formulario
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Código inválido o inexistente");
            }

            request.getRequestDispatcher("Pages/agenda.jsp").forward(request, response);
        }        
    }
}
