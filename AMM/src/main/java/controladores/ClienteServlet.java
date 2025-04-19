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

/**
 *
 * @author nyath
 */
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if(session != null) {
        
            String accion = request.getParameter("accion");
            ClienteManager manager = new ClienteManager();

            if("buscar".equals(accion)){
                String id_str = request.getParameter("id_cliente");
                

                try {
                    int id = Integer.parseInt(id_str);
                    Cliente cliente = manager.buscarCliente(id);

                    if(cliente != null) {
                        request.setAttribute("mensaje", "Equipo encontrado: " + id);
                        request.setAttribute("cliente", cliente);
                    } else {
                        request.setAttribute("error", "No se encontro el equipo");
                    }
                } catch (NumberFormatException e){
                    request.setAttribute("error", "Código de equipo invalido o inexistente");
                }

                request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
            }

            if("crear/modificar".equals(accion)){
                String id_Str = request.getParameter("id_cliente");

                    try {
                        Usuario user = (Usuario) session.getAttribute("usuario");                        
                        Cliente cliente = new Cliente();
                        boolean idExist = id_Str != null && !id_Str.isEmpty();
                        int id = idExist ? Integer.parseInt(id_Str) : 0;

                        cliente.setNombres(request.getParameter("nombre_cliente"));
                        cliente.setApellidos(request.getParameter("apellido_cliente"));
                        cliente.setDireccion(request.getParameter("direccion_cliente"));
                        cliente.setTelefono(request.getParameter("telefono_cliente"));
                        cliente.setEmail(request.getParameter("correo_cliente"));
                        cliente.setObservaciones(request.getParameter("observacion_cliente"));
                        
                        if(id == 0) {
                            cliente.setUser_crea(user.getUser());
                            boolean creation = manager.insertCliente(cliente);
                            if (creation){
                                request.setAttribute("mensaje", "Cliente creado exitosamente.");
                                request.setAttribute("cliente", null);
                            } else {
                                request.setAttribute("error", "No se pudo crear el cliente");
                            }
                        } else {
                            TimeUtils timeU = new TimeUtils();
                            cliente.setCodigo(id_Str);
                            cliente.setUser_modifica(user.getUser());
                            cliente.setModificado_el(timeU.getNowTime());
                            boolean modificacion = manager.modificarCliente(cliente);                            
                            if (modificacion){
                                request.setAttribute("mensaje", "Cliente modificado exitosamente.");
                                request.setAttribute("cliente", cliente);
                            } else {
                                request.setAttribute("error", "No se pudo modificar el cliente");
                            }
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("error", "Código de equipo invalido o inexistente");
                    }


                request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
            }

            if("mostrar".equals(accion)){
                Cliente[] clientes = manager.getAllCliente();

                request.setAttribute("clientes", clientes);
                request.getRequestDispatcher("Pages/listadoClientes.jsp").forward(request, response);
            }

            if("eliminar".equals(accion)){
                String id_str =  request.getParameter("id_cliente");
                try {
                    if (id_str != null && !id_str.isEmpty()) {
                        int id = Integer.parseInt(id_str);
                        
                        boolean eliminado = manager.eliminarCliente(id);
                        if (eliminado) {
                            request.setAttribute("mensaje", "Cliente eliminado exitosamente.");
                            request.setAttribute("cliente", null); // limpiar formulario
                        } else {
                            request.setAttribute("error", "No se pudo eliminar el cliente, confirme que no este ligado a ningún servicio");
                        }
                    } else {
                        request.setAttribute("error", "Debe ingresar un código para eliminar.");
                    }

                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Código inválido o inexistente.");
                }

                request.getRequestDispatcher("Pages/clientes.jsp").forward(request, response);
            }        
        } else {
            request.setAttribute("error", "Sesión expirada");
            response.sendRedirect("../index.jsp");
            return;
        }
    }
}
