package controladores;

import Clases.Usuario;
import Servicios.TiDoManager;
import Servicios.TiUsManager;
import Servicios.UsuarioManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Utils.TimeUtils;
import java.util.List;

/**
 *
 * @author nyath
 */
public class EmpleadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion"); //Obtener botón
        UsuarioManager manager = new UsuarioManager();

        if("buscar".equals(accion)){ //Botón buscar
            String id_str = request.getParameter("id_usuario");

            try {
                if(id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error", "Debe seleccionar un código para buscar");
                } else {
                    Usuario empleado = manager.buscarUsuario(id_str); //Buscar registro por id
                    request.setAttribute("mensaje", "Empleado encontrado: " + id_str);
                    request.setAttribute("empleado", empleado);
                }
            } catch (NumberFormatException e){
                request.setAttribute("error", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
        }

        if("crear/modificar".equals(accion)){ //Botón crear/modificar
            String id_str = request.getParameter("id_usuario"); //Obtener id
            TiUsManager tuManager = new TiUsManager();
            TiDoManager tdManager = new TiDoManager();

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario"); //Obtener usuario de la sesión
                    Usuario empleado = new Usuario();
                    
                    empleado.setTipoUsuario(tuManager.buscarTipoUsuario(Integer.parseInt(request.getParameter("id_tipoUsua")))); //Obtener tipo usuario con la id del input
                    empleado.setNombre_usuario(request.getParameter("nombre_usuario"));
                    empleado.setContrasena_usuario(request.getParameter("contrasena_usuario"));
                    empleado.setTipoDocumento(tdManager.buscarTipoDocumento(Integer.parseInt(request.getParameter("id_tipoDocu")))); //Obtener tipo documento con la id del input
                    empleado.setDocumento_usuario(request.getParameter("documento_usuario"));
                    empleado.setNombres(request.getParameter("nombres"));
                    empleado.setApellidos(request.getParameter("apellidos"));
                    empleado.setTelefono_usuario(request.getParameter("telefono_usuario"));
                    empleado.setCorreo_usuario(request.getParameter("correo_usuario"));

                    if(id_str == null || "NA".equals(id_str)) { //Crear
                        empleado.setUser_crea(user.getNombre_usuario());
                        boolean creation = manager.crearUsuario(empleado);
                        if (!creation){
                            request.setAttribute("error", "No se pudo crear el empleado");
                        } else {
                            request.setAttribute("mensaje", "Empleado creado exitosamente");
                            request.setAttribute("empleado", null);
                        }
                    } else { //Modificar
                        TimeUtils timeU = new TimeUtils();
                        empleado.setUser_modifica(user.getNombre_usuario());                        
                        empleado.setModificado_el(timeU.getNowTime());
                        empleado.setId_usuario(id_str);
                        boolean modificacion = manager.modificarUsuario(empleado);                            
                        if (modificacion){
                            request.setAttribute("mensaje", "Empleado modificado exitosamente");
                            request.setAttribute("empleado", empleado);
                        } else {
                            request.setAttribute("error", "No se pudo modificar el empleado");
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
        }

        if("mostrar".equals(accion)){
            List<Usuario> empleados = manager.getAllUsuario();

            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("Pages/listadoEmpleados.jsp").forward(request, response);
        }

        if("eliminar".equals(accion)){
            String id_str =  request.getParameter("id_usuario");
            try {
                if (id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error", "Debe seleccionar un código para eliminar");
                } else {
                    boolean eliminado = manager.eliminarUsuario(id_str);
                    if (eliminado) {
                        request.setAttribute("mensaje", "Empleado eliminado exitosamente");
                        request.setAttribute("empleado", null); // limpiar formulario
                    } else {
                        request.setAttribute("error", "No se pudo eliminar el empleado, confirme que no este ligado a ningún equipo");
                    }
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Código inválido o inexistente");
            }

            request.getRequestDispatcher("Pages/empleados.jsp").forward(request, response);
        }
    }
}
