package controladores;

import Clases.Usuario;
import Clases.Tipo_documento;
import Clases.Tipo_limpieza;
import Clases.Tipo_usuario;
import Servicios.TiDoManager;
import Servicios.TiLiManager;
import Servicios.TiUsManager;
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
public class TiposServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion"); //Obtener botón usado
        TiUsManager manager = new TiUsManager();

        if("mostrar_u".equals(accion)){ //Botón mostrar en tipo usuario
            List<Tipo_usuario> tipos_usuario = manager.getAllTipoUsuario();

            request.setAttribute("tipos_usua", tipos_usuario);
            request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion"); //Obtener botón activado
        TiDoManager tdManager = new TiDoManager();
        TiLiManager tlManager = new TiLiManager();

        if("buscar_d".equals(accion)){ //Botón buscar en tipo documento
            String id_str = request.getParameter("id_tipoDocu"); //Obtener id de documento

            try {
                if(id_str == null || "NA".equals(id_str)){ //Si no existe o es NA
                    request.setAttribute("error_docu", "Debe seleccionar un código para buscar");
                } else { // si es diferente a NA
                    int id = Integer.parseInt(id_str);
                    Tipo_documento tipo_docu = tdManager.buscarTipoDocumento(id); //Buscar registro por id
                    request.setAttribute("mensaje_docu", "Tipo de documento encontrado: " + id);
                    request.setAttribute("tipo_docu", tipo_docu);
                }
            } catch (NumberFormatException e){
                request.setAttribute("error_docu", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }

        if("crear/modificar_d".equals(accion)){ //Botón crear/modificar en tipo documento
            String id_str = request.getParameter("id_tipoDocu");

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario");                        
                    Tipo_documento tipo_docu = new Tipo_documento();
                    boolean idExist = id_str != null && !"NA".equals(id_str);
                    int id = idExist ? Integer.parseInt(id_str) : 0; //Si existe pasar a int, si no =0

                    tipo_docu.setNombre_tipo(request.getParameter("nombre_tipoD"));
                    if(id == 0) {
                        tipo_docu.setUser_crea(user.getNombre_usuario());
                        boolean creation = tdManager.crearTipoDocumento(tipo_docu);
                        if (!creation){ //si no se pudo crear
                            request.setAttribute("error_docu", "No se pudo crear el tipo de documento");
                        } else { //si fue creado
                            request.setAttribute("mensaje_docu", "Tipo de documento creado exitosamente");
                            request.setAttribute("tipo_docu", tdManager.getLast()); //Último registro al formulario
                        }
                    } else {
                        TimeUtils timeU = new TimeUtils();
                        tipo_docu.setId_tipoDocu(id);
                        tipo_docu.setUser_modifica(user.getId_usuario());
                        tipo_docu.setModificado_el(timeU.getNowTime());
                        boolean modificacion = tdManager.modificarTipoDocumento(tipo_docu);                            
                        if (!modificacion){ //si no se pudo modificar
                            request.setAttribute("error_docu", "No se pudo modificar el tipo de documento");
                        } else {//si fue modificado
                            request.setAttribute("mensaje_docu", "Tipo de documento modificado exitosamente");
                            request.setAttribute("tipo_docu", tipo_docu);
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error_docu", "Código de tipo de documento invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }

        if("mostrar_d".equals(accion)){ //botón mostrar en tipo documento
            List<Tipo_documento> tipos_docu = tdManager.getAllTipoDocumento(); //Obtener todos los registros

            request.setAttribute("tipos_docu", tipos_docu); //Enviar registro a pagina de tabla
            request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
        }

        if("eliminar_d".equals(accion)){ //Botón eliminar en tipo documento
            String id_str =  request.getParameter("id_tipoDocu");
            try {
                if (id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error_docu", "Debe seleccionar un código para eliminar");
                } else {
                    int id = Integer.parseInt(id_str); //convertir a int

                    boolean eliminado = tdManager.eliminarTipoDocumento(id);
                    if (!eliminado) {
                        request.setAttribute("error_docu", "No se pudo eliminar el tipo de documento, confirme que no este ligado a ningún empleado");
                    } else {
                        request.setAttribute("mensaje_docu", "Tipo de documento eliminado exitosamente");
                        request.setAttribute("tipo_docu", null); // limpiar formulario
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error_docu", "Código inválido o inexistente");
            }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }

        if("buscar_l".equals(accion)){ //Botón buscar en tipo limpieza
            String id_str = request.getParameter("id_tipoLimp");

            try {
                if(id_str == null || "NA".equals(id_str)){
                    request.setAttribute("error_limp", "Debe seleccionar un código para buscar");
                } else {
                    int id = Integer.parseInt(id_str);
                    Tipo_limpieza tipo_limp = tlManager.buscarTipoLimpieza(id);
                    
                    request.setAttribute("mensaje_limp", "Tipo de limpieza encontrada: " + id);
                    request.setAttribute("tipo_limp", tipo_limp);                    
                }
            } catch (NumberFormatException e){
                request.setAttribute("error_limp", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }

        if("crear/modificar_l".equals(accion)){ //Botón crear/modificar en tipo limpieza
            String id_str = request.getParameter("id_tipoLimp");

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario"); //Obtener usuario de seión
                    //verificar que id no sea NA o null
                    boolean idExist = id_str != null && !"NA".equals(id_str);
                    int id = idExist ? Integer.parseInt(id_str) : 0;
                    //crear variable y asignar
                    Tipo_limpieza tipo_limp = new Tipo_limpieza();
                    tipo_limp.setNombre_tipo(request.getParameter("nombre_tipoL"));

                    if(id == 0) {
                        tipo_limp.setUser_crea(user.getNombre_usuario());
                        boolean creation = tlManager.crearTipoLimpieza(tipo_limp);
                        if (!creation){
                            request.setAttribute("error_limp", "No se pudo crear el tipo de limpieza");
                        } else {
                            request.setAttribute("mensaje_limp", "Tipo de limpieza creado exitosamente");
                            request.setAttribute("tipo_limp", tlManager.getLast()); //Último registro al formulario
                        }
                    } else {
                        TimeUtils timeU = new TimeUtils();
                        tipo_limp.setId_tipoLimp(id);
                        tipo_limp.setUser_modifica(user.getNombre_usuario());
                        tipo_limp.setModificado_el(timeU.getNowTime());
                        boolean modificacion = tlManager.modificarTipoLimpieza(tipo_limp);                            
                        if (!modificacion){
                            request.setAttribute("error_limp", "No se pudo modificar el tipo de limpieza");
                        } else {
                            request.setAttribute("mensaje_limp", "Tipo de limpieza modificado exitosamente");
                            request.setAttribute("tipo_limp", tipo_limp);
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error_limp", "Código invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }

        if("mostrar_l".equals(accion)){ //Botón mostrar en tipo limpieza
            List<Tipo_limpieza> tipos_limp = tlManager.getAllTipoLimpieza();

            request.setAttribute("tipos_limp", tipos_limp);
            request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
        }

        if("eliminar_l".equals(accion)){ //Botón eliminar en tipo limpieza
            String id_str =  request.getParameter("id_tipoLimp");
            try {
                if (id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error_limp", "Debe seleccionar un código para eliminar");
                } else {
                    int id = Integer.parseInt(id_str); //id_str a int

                    boolean eliminado = tlManager.eliminarTipoLimpieza(id);
                    if (!eliminado) {
                        request.setAttribute("error_limp", "No se pudo eliminar el tipo de limpieza, confirme que no este ligado a ningún servicio");
                    } else {
                        request.setAttribute("mensaje_limp", "Tipo de limpieza eliminado exitosamente");
                        request.setAttribute("tipo_limp", null); // limpiar formulario
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error_limp", "Código inválido o inexistente.");
            }

            request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
        }        
    }
}
