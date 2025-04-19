package controladores;

import Clases.Usuario;
import Servicios.UsuarioManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Utils.TimeUtils;

/**
 *
 * @author nyath
 */
public class EmpleadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null) {
        
            String accion = request.getParameter("accion");
            UsuarioManager manager = new UsuarioManager();

            if("buscar".equals(accion)){
                String id_str = request.getParameter("id_usuario");
                String userN_str = request.getParameter("nombre_usuario");

                try {
                    Usuario empleado = manager.buscarUsuario(id_str, userN_str);

                    if(empleado != null) {
                        request.setAttribute("mensaje", "Empleado encontrado: " + id_str + " " + userN_str);
                        request.setAttribute("empleado", empleado);
                    } else {
                        request.setAttribute("error", "No se encontro el empleado");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error", "Código/Nombre de usuario invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
            }

            if("crear/modificar".equals(accion)){
                String id_Str = request.getParameter("id_usuario");

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");                        
                        Usuario empleado = new Usuario();

                        empleado.setTipo_user(Integer.parseInt(request.getParameter("id_tipoUsua")));
                        empleado.setUser(request.getParameter("nombre_usuario"));
                        empleado.setPassword(request.getParameter("contrasena_usuario"));
                        empleado.setTipo_docu(Integer.parseInt(request.getParameter("id_tipoDocu")));
                        empleado.setDocumento(request.getParameter("documento_usuario"));
                        empleado.setNombres(request.getParameter("nombres"));
                        empleado.setApellidos(request.getParameter("apellidos"));
                        empleado.setTelefono(request.getParameter("telefono_usuario"));
                        empleado.setEmail(request.getParameter("correo_usuario"));
                        
                        if(id_Str == null || id_Str.isEmpty()) {
                            empleado.setUser_crea(user.getUser());
                            boolean creation = manager.insertUsuario(empleado);
                            if (creation){
                                request.setAttribute("mensaje", "Empleado creado exitosamente.");
                                request.setAttribute("empleado", null);
                            } else {
                                request.setAttribute("error", "No se pudo crear el empleado");
                            }
                        } else {
                            TimeUtils timeU = new TimeUtils();
                            empleado.setCodigo(id_Str);
                            empleado.setUser_modifica(user.getUser());
                            empleado.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarUsuario(empleado);                            
                            if (modificacion){
                                request.setAttribute("mensaje", "Empleado modificado exitosamente.");
                                request.setAttribute("empleado", empleado);
                            } else {
                                request.setAttribute("error", "No se pudo modificar el empleado");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Código/Nombre de usuario invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
            }

            if("mostrar".equals(accion)){
                Usuario[] empleados = manager.getAllUsuario();

                request.setAttribute("empleados", empleados);
                request.getRequestDispatcher("Pages/listadoEmpleados.jsp").forward(request, response);
            }

            if("eliminar".equals(accion)){
                String idStr =  request.getParameter("id_usuario");
                try {
                    if (idStr != null && !idStr.isEmpty()) {
                        
                        boolean eliminado = manager.eliminarUsuario(idStr);
                        if (eliminado) {
                            request.setAttribute("mensaje", "Empleado eliminado exitosamente.");
                            request.setAttribute("empleado", null); // limpiar formulario
                        } else {
                            request.setAttribute("error", "No se pudo eliminar el empleado, confirme que no este ligado a ningún equipo.");
                        }
                    } else {
                        request.setAttribute("error", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código inválido o inexistente.");
                }

                request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
            }        
        } else {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
    }
}
