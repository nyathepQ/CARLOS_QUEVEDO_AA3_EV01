package controladores;

import Clases.Usuario;
import Clases.Varios;
import Servicios.VariosManager;
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
public class TiposServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
        
        String accion = request.getParameter("accion");
        VariosManager manager = new VariosManager();
        String tu_tabla = "tipo_usuario";
        String tu_colum = "id_tipoUsua";

        if("mostrar_u".equals(accion)){
            Varios[] tipos_usuario = manager.getAllTipoVarios(tu_tabla, tu_colum);

            request.setAttribute("tipos_usua", tipos_usuario);
            request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
        }        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null) {
        
            String accion = request.getParameter("accion");
            VariosManager manager = new VariosManager();
            String td_tabla = "tipo_documento";
            String td_colum = "id_tipoDocu";
            String tl_tabla = "tipo_limpieza";
            String tl_colum = "id_tipoLimp";

            if("buscar_d".equals(accion)){
                String id_str = request.getParameter("id_tipoDocu");
                

                try {
                    int id = Integer.parseInt(id_str);
                    Varios tipo_docu = manager.buscarTipoVarios(td_tabla, td_colum, id);

                    if(tipo_docu != null) {
                        request.setAttribute("mensaje_docu", "Tipo de documento encontrado: " + id);
                        request.setAttribute("tipo_docu", tipo_docu);
                    } else {
                        request.setAttribute("error_docu", "No se encontro el tipo de documento");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error_docu", "Código de tipo de documento invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }

            if("crear/modificar_d".equals(accion)){
                String id_Str = request.getParameter("id_tipoDocu");

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");                        
                        Varios tipo_docu = new Varios();
                        boolean idExist = id_Str != null && !id_Str.isEmpty();
                        int id = idExist ? Integer.parseInt(id_Str) : 0;

                        tipo_docu.setNombre(request.getParameter("nombre_tipoD"));
                        
                        if(id == 0) {
                            tipo_docu.setUser_crea(user.getUser());
                            boolean creation = manager.insertTipoVarios(td_tabla, tipo_docu);
                            if (creation){
                                request.setAttribute("mensaje_docu", "Tipo de documento creado exitosamente.");
                                request.setAttribute("tipo_docu", null);
                            } else {
                                request.setAttribute("error_docu", "No se pudo crear el tipo de documento");
                            }
                        } else {
                            TimeUtils timeU = new TimeUtils();
                            tipo_docu.setCodigo(id);
                            tipo_docu.setUser_modifica(user.getUser());
                            tipo_docu.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarTipoVarios(td_tabla, td_colum, tipo_docu);                            
                            if (modificacion){
                                request.setAttribute("mensaje_docu", "Tipo de documento modificado exitosamente.");
                                request.setAttribute("tipo_docu", tipo_docu);
                            } else {
                                request.setAttribute("error_docu", "No se pudo modificar el tipo de documento");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error_docu", "Código de tipo de documento invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }

            if("mostrar_d".equals(accion)){
                Varios[] tipos_docu = manager.getAllTipoVarios(td_tabla, td_colum);

                request.setAttribute("tipos_docu", tipos_docu);
                request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
            }

            if("eliminar_d".equals(accion)){
                String id_str =  request.getParameter("id_tipoDocu");
                try {
                    if (id_str != null && !id_str.isEmpty()) {
                        int id = Integer.parseInt(id_str);
                        
                        boolean eliminado = manager.eliminarTipoVarios(td_tabla, td_colum, id);
                        if (eliminado) {
                            request.setAttribute("mensaje_docu", "Tipo de documento eliminado exitosamente.");
                            request.setAttribute("tipo_docu", null); // limpiar formulario
                        } else {
                            request.setAttribute("error_docu", "No se pudo eliminar el tipo de documento, confirme que no este ligado a ningún empleado");
                        }
                    } else {
                        request.setAttribute("error_docu", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error_docu", "Código inválido o inexistente.");
                }

                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }
            
            if("buscar_l".equals(accion)){
                String id_str = request.getParameter("id_tipoLimp");
                

                try {
                    int id = Integer.parseInt(id_str);
                    Varios tipo_limp = manager.buscarTipoVarios(tl_tabla, tl_colum, id);

                    if(tipo_limp != null) {
                        request.setAttribute("mensaje_limp", "Tipo de limpieza encontrada: " + id);
                        request.setAttribute("tipo_limp", tipo_limp);
                    } else {
                        request.setAttribute("error_limp", "No se encontro el tipo de limpieza");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error_limp", "Código de tipo de limpieza invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }

            if("crear/modificar_l".equals(accion)){
                String id_Str = request.getParameter("id_tipoLimp");

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");                        
                        Varios tipo_limp = new Varios();
                        boolean idExist = id_Str != null && !id_Str.isEmpty();
                        int id = idExist ? Integer.parseInt(id_Str) : 0;

                        tipo_limp.setNombre(request.getParameter("nombre_tipoL"));
                        
                        if(id == 0) {
                            tipo_limp.setUser_crea(user.getUser());
                            boolean creation = manager.insertTipoVarios(tl_tabla, tipo_limp);
                            if (creation){
                                request.setAttribute("mensaje_limp", "Tipo de limpieza creado exitosamente.");
                                request.setAttribute("tipo_limp", null);
                            } else {
                                request.setAttribute("error_limp", "No se pudo crear el tipo de limpieza");
                            }
                        } else {
                            TimeUtils timeU = new TimeUtils();
                            tipo_limp.setCodigo(id);
                            tipo_limp.setUser_modifica(user.getUser());
                            tipo_limp.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarTipoVarios(tl_tabla, tl_colum, tipo_limp);                            
                            if (modificacion){
                                request.setAttribute("mensaje_limp", "Tipo de limpieza modificado exitosamente.");
                                request.setAttribute("tipo_limp", tipo_limp);
                            } else {
                                request.setAttribute("error_limp", "No se pudo modificar el tipo de limpieza");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error_limp", "Código de tipo de limpieza invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }

            if("mostrar_l".equals(accion)){
                Varios[] tipos_limp = manager.getAllTipoVarios(tl_tabla, tl_colum);

                request.setAttribute("tipos_limp", tipos_limp);
                request.getRequestDispatcher("Pages/listadoVarios.jsp").forward(request, response);
            }

            if("eliminar_l".equals(accion)){
                String id_str =  request.getParameter("id_tipoLimp");
                try {
                    if (id_str != null && !id_str.isEmpty()) {
                        int id = Integer.parseInt(id_str);
                        
                        boolean eliminado = manager.eliminarTipoVarios(tl_tabla, tl_colum, id);
                        if (eliminado) {
                            request.setAttribute("mensaje_limp", "Tipo de limpieza eliminado exitosamente.");
                            request.setAttribute("tipo_limp", null); // limpiar formulario
                        } else {
                            request.setAttribute("error_limp", "No se pudo eliminar el tipo de limpieza, confirme que no este ligado a ningún servicio");
                        }
                    } else {
                        request.setAttribute("error_limp", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error_limp", "Código inválido o inexistente.");
                }

                request.getRequestDispatcher("Pages/valores.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
    }
}
