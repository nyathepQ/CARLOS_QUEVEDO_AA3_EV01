package controladores;

import Clases.Cliente;
import Clases.Usuario;
import Servicios.ClienteManager;
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
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session == null) { // Si no existe sesión regresar al login
            response.sendRedirect("../index.jsp?error=Sesion+expirada");
            return;        
        }
        
        String accion = request.getParameter("accion");
        ClienteManager manager = new ClienteManager();

        if("buscar".equals(accion)){ //botón buscar
            String id_str = request.getParameter("id_cliente"); //obtener el valor del input id

            try {
                if(id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error", "Debe seleccionar un código para buscar");
                } else { //si existe registro
                    int id = Integer.parseInt(id_str); //convertir a int
                    Cliente cliente = manager.buscarCliente(id); //buscar registro
                    request.setAttribute("mensaje", "Equipo encontrado: " + id); //mensaje de exito
                    request.setAttribute("cliente", cliente); //valores para llenar formulario
                }
            } catch (NumberFormatException e){
                request.setAttribute("error", "Código invalido o inexistente");
            }

            request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
        }

        if("crear/modificar".equals(accion)){ //Botón crear/modificar
            String id_str = request.getParameter("id_cliente");

                try {
                    Usuario user = (Usuario) session.getAttribute("usuario"); //Obtener usuario de la sesión
                    Cliente cliente = new Cliente();
                    boolean idExist = id_str != null && !"NA".equals(id_str);
                    int id = idExist ? Integer.parseInt(id_str) : 0; //si la id existe, volverla int, si no, id = 0
                    
                    //asígnar valores a cliente con el valor de los inputs
                    cliente.setNombre_cliente(request.getParameter("nombre_cliente"));
                    cliente.setApellido_cliente(request.getParameter("apellido_cliente"));
                    cliente.setDireccion_cliente(request.getParameter("direccion_cliente"));
                    cliente.setTelefono_cliente(request.getParameter("telefono_cliente"));
                    cliente.setCorreo_cliente(request.getParameter("correo_cliente"));
                    cliente.setObservacion_cliente(request.getParameter("observacion_cliente"));

                    if(id == 0) { //si no existia id, crear
                        cliente.setUser_crea(user.getNombre_usuario());
                        boolean creation = manager.crearCliente(cliente);
                        if (creation){ //si el registro fue creado
                            request.setAttribute("mensaje", "Cliente creado exitosamente");
                            request.setAttribute("cliente", manager.getLast()); //ultimo registro en formulario
                        } else {
                            request.setAttribute("error", "No se pudo crear el cliente");
                        }
                    } else { //si existia id, modificar
                        //manager de utilidades de date-time
                        TimeUtils timeU = new TimeUtils();
                        //asignar valores de modificación
                        cliente.setId_cliente(id);
                        cliente.setUser_modifica(user.getNombre_usuario());
                        cliente.setModificado_el(timeU.getNowTime()); //modificado en momento actual
                        boolean modificacion = manager.modificarCliente(cliente); //modificar
                        if (modificacion){ //si el registro fue modificado
                            request.setAttribute("mensaje", "Cliente modificado exitosamente");
                            request.setAttribute("cliente", cliente); //mostrar el formulario con los valores del registro modificado
                        } else {
                            request.setAttribute("error", "No se pudo modificar el cliente");
                        }
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código invalido o inexistente");
                }

            request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
        }

        if("mostrar".equals(accion)){ //Botón mostrar
            List<Cliente> clientes = manager.getAllCliente(); //obtener todos los registros

            request.setAttribute("clientes", clientes); //guardar atributo para siguiente pagina
            request.getRequestDispatcher("Pages/listadoClientes.jsp").forward(request, response); //cambiar a pagina de tabla
        }

        if("eliminar".equals(accion)){ //Botón eliminar
            String id_str = request.getParameter("id_cliente"); //Obtener id de inputs
            try {
                if (id_str == null || "NA".equals(id_str)) {
                    request.setAttribute("error", "Debe seleccionar un código para eliminar");
                } else { //si hay un valor en id
                    int id = Integer.parseInt(id_str);

                    boolean eliminado = manager.eliminarCliente(id);
                    if (eliminado) {
                        request.setAttribute("mensaje", "Cliente eliminado exitosamente");
                        request.setAttribute("cliente", null); // limpiar formulario
                    } else {
                        request.setAttribute("error", "No se pudo eliminar el cliente, confirme que no este ligado a ningún servicio");
                    }
                }

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Código inválido o inexistente");
            }

            request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
        }
    }
}
