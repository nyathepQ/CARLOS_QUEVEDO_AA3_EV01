package Servicios;

/**
 *
 * @author nyath
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Clases.ConexionBD;
import Clases.Usuario;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    public boolean insertUsuario(Usuario user){
        String sql = "INSERT INTO usuario (id_tipoUsua, nombre_usuario, contrasena_usuario, id_tipoDocu, documento_usuario, nombres, apellidos, telefono_usuario, correo_usuario, user_crea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            stat.setInt(1, user.getTipo_user());
            stat.setString(2, user.getUser());
            stat.setString(3, user.getPassword());
            stat.setInt(4, user.getTipo_docu());
            stat.setString(5, user.getDocumento());
            stat.setString(6, user.getNombres());
            stat.setString(7, user.getApellidos());
            stat.setString(8, user.getTelefono());
            stat.setString(9, user.getEmail());
            stat.setString(10, user.getUser_crea());
            
            int filas_afec = stat.executeUpdate();
            return filas_afec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Usuario buscarUsuario (String codigo_b, String username) {
        Connection cx = ConexionBD.getConnection();
        Usuario us = null;
        
        if(cx != null) {
            String sql = "";
            int type = 0;
            if(!codigo_b.isEmpty() && !username.isEmpty()){
                type = 1;
                sql = "SELECT * FROM usuario WHERE id_usuario = ? AND nombre_usuario = ?";
            } else if (!codigo_b.isEmpty()){
                type = 2;
                sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            } else if (!username.isEmpty()){
                type = 3;
                sql = "SELECT * FROM usuario WHERE nombre_usuario = ?";
            }
            
            
            try {
                PreparedStatement stat = cx.prepareStatement(sql);
                if (type == 1){
                    stat.setString(1, codigo_b);
                    stat.setString(2, username);
                } else if (type == 2){
                    stat.setString(1, codigo_b);
                } else if (type == 3){
                    stat.setString(1, username);
                }                
                ResultSet rs = stat.executeQuery();
                
                if(rs.next()) {
                    String id_usuario = rs.getString("id_usuario");
                    int id_tipoUsua = rs.getInt("id_tipoUsua");
                    String nombre_usuario = rs.getString("nombre_usuario");
                    String contrasena_usuario = rs.getString("contrasena_usuario");
                    int id_tipoDocu = rs.getInt("id_tipoDocu");
                    String documento_usuario = rs.getString("documento_usuario");
                    String nombres = rs.getString("nombres");
                    String apellidos = rs.getString("apellidos");
                    String telefono_usuario = rs.getString("telefono_usuario");
                    String correo_usuario = rs.getString("correo_usuario");
                    String user_crea = rs.getString("user_crea");
                    Timestamp creado_el = rs.getTimestamp("creado_el");
                    String user_modifica = rs.getString("user_modifica");
                    Timestamp modificado_el = rs.getTimestamp("modificado_el");
                    
                    us = new Usuario(id_tipoUsua, nombre_usuario, contrasena_usuario, id_tipoDocu, documento_usuario, id_usuario, nombres, apellidos, telefono_usuario, correo_usuario, user_crea, creado_el, user_modifica, modificado_el);
                }
                
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return us;
    }
    
    public Usuario[] getAllUsuario () {
        Connection cx = ConexionBD.getConnection();
        List<Usuario> us = new ArrayList<>();
        
        if(cx != null) {
            String sql = "SELECT * FROM usuario";
            
            try {
                PreparedStatement stat = cx.prepareStatement(sql);
                
                ResultSet rs = stat.executeQuery();
                
                while(rs.next()) {
                    String id_usuario = rs.getString("id_usuario");
                    int id_tipoUsua = rs.getInt("id_tipoUsua");
                    String nombre_usuario = rs.getString("nombre_usuario");
                    String contrasena_usuario = rs.getString("contrasena_usuario");
                    int id_tipoDocu = rs.getInt("id_tipoDocu");
                    String documento_usuario = rs.getString("documento_usuario");
                    String nombres = rs.getString("nombres");
                    String apellidos = rs.getString("apellidos");
                    String telefono_usuario = rs.getString("telefono_usuario");
                    String correo_usuario = rs.getString("correo_usuario");
                    String user_crea = rs.getString("user_crea");
                    Timestamp creado_el = rs.getTimestamp("creado_el");
                    String user_modifica = rs.getString("user_modifica");
                    Timestamp modificado_el = rs.getTimestamp("modificado_el");
                    
                    Usuario temp = new Usuario(id_tipoUsua, nombre_usuario, contrasena_usuario, id_tipoDocu, documento_usuario, id_usuario, nombres, apellidos, telefono_usuario, correo_usuario, user_crea, creado_el, user_modifica, modificado_el);
                    us.add(temp);
                }
                
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return us.toArray(new Usuario[0]);
    }
    
    public boolean modificarUsuario (Usuario usuario) {
        String sql = "UPDATE usuario SET id_tipoUsua = ?, nombre_usuario = ?, contrasena_usuario = ?, id_tipoDocu = ?, documento_usuario = ?, nombres = ?, apellidos = ?, telefono_usuario = ?, correo_usuario = ?, user_modifica = ?, modificado_el = ? WHERE id_usuario = ?";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setInt(1, usuario.getTipo_user());
            stat.setString(2, usuario.getUser());
            stat.setString(3, usuario.getPassword());
            stat.setInt(4, usuario.getTipo_docu());
            stat.setString(5, usuario.getDocumento());
            stat.setString(6, usuario.getNombres());
            stat.setString(7, usuario.getApellidos());
            stat.setString(8, usuario.getTelefono());
            stat.setString(9, usuario.getEmail());
            stat.setString(10, usuario.getUser_modifica());
            stat.setTimestamp(11, usuario.getModificado_el());
            stat.setString(12, usuario.getCodigo());
            
            int filas_afec = stat.executeUpdate();
            return filas_afec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarUsuario (String codigo) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setString(1, codigo);
            
            int filas_afec = stat.executeUpdate();            
            return filas_afec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String UsuarioToString (Usuario user){
        return user.getNombres() + " " + user.getApellidos();
    }
    
    public Object[][] toTableObject (Usuario[] usuario){
        Object[][] datos = new Object[usuario.length][14];
        
        for (int i = 0; i < usuario.length; i++) {
            datos[i][0] = usuario[i].getCodigo();
            datos[i][1] = usuario[i].getTipo_user();
            datos[i][2] = usuario[i].getUser();
            datos[i][3] = usuario[i].getPassword();
            datos[i][4] = usuario[i].getTipo_docu();
            datos[i][5] = usuario[i].getDocumento();
            datos[i][6] = usuario[i].getNombres();
            datos[i][7] = usuario[i].getApellidos();
            datos[i][8] = usuario[i].getTelefono();
            datos[i][9] = usuario[i].getEmail();
            datos[i][10] = usuario[i].getUser_crea();
            datos[i][11] = usuario[i].getCreado_el();
            datos[i][12] = usuario[i].getUser_modifica();
            datos[i][13] = usuario[i].getModificado_el();
        }
        
        return datos;
    }
    
    public Usuario verificarCredencial(String user, String pass){
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena_usuario = ?";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setString(1, user);
            stat.setString(2, pass);
            ResultSet rs = stat.executeQuery();
            
            if(rs.next()){
                Usuario us = new Usuario();
                us.setUser(rs.getString("nombre_usuario"));
                us.setTipo_user(rs.getInt("id_tipoUsua"));
                us.setPassword(rs.getString("contrasena_usuario"));
                us.setNombres(rs.getString("nombres"));
                us.setApellidos(rs.getString("apellidos"));
                return us;
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }   
    
}
