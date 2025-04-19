package Servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Clases.ConexionBD;
import Clases.Varios;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author nyath
 */
public class VariosManager {
    
    /*
    nombre_tabla = tipo_documento - tipo_limpieza - tipo_usuario
    col_name_id = id_tipoDocu - id_tipoLimp - id_tipoUsua
    */
    public boolean insertTipoVarios (String nombre_tabla, Varios tipo) {
        String sql = "INSERT INTO " + nombre_tabla + " (nombre_tipo, user_crea) VALUES (?, ?)";
        
        try (Connection cx = ConexionBD.getConnection();
            PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setString(1, tipo.getNombre());
            stat.setString(2, tipo.getUser_crea());
            
            int filas_afec = stat.executeUpdate();
            return filas_afec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Varios buscarTipoVarios (String nombre_tabla, String col_name_id, int codigo_b) {
        Connection cx = ConexionBD.getConnection();
        Varios vr = null;
        
        if(cx != null) {
            String sql = "SELECT * FROM " + nombre_tabla + " WHERE " + col_name_id + " = ?";
            
            try {
                PreparedStatement stat = cx.prepareStatement(sql);
                stat.setInt(1, codigo_b);
                ResultSet rs = stat.executeQuery();
                
                if(rs.next()) {
                    int codigo = rs.getInt(col_name_id);
                    String nombre = rs.getString("nombre_tipo");
                    String user_crea = rs.getString("user_crea");
                    Timestamp creado_el = rs.getTimestamp("creado_el");
                    String user_modifica = rs.getString("user_modifica");
                    Timestamp modificado_el = rs.getTimestamp("modificado_el");
                    
                    vr = new Varios(codigo, nombre, user_crea, creado_el, user_modifica, modificado_el);
                }
                
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return vr;
    }
    
    public Varios[] getAllTipoVarios (String nombre_tabla, String col_name_id){
        Connection cx = ConexionBD.getConnection();
        List<Varios> vr = new ArrayList<>();
        
        if(cx != null) {
            String sql = "SELECT * FROM " + nombre_tabla;
            
            
            try{
                PreparedStatement stat = cx.prepareStatement(sql);
                
                ResultSet rs = stat.executeQuery();
                
                while(rs.next()){
                    int codigo = rs.getInt(col_name_id);
                    String nombre = rs.getString("nombre_tipo");
                    String user_crea = rs.getString("user_crea");
                    Timestamp creado_el = rs.getTimestamp("creado_el");
                    String user_modifica = rs.getString("user_modifica");
                    Timestamp modificado_el = rs.getTimestamp("modificado_el");
                    Varios prov = new Varios(codigo, nombre, user_crea, creado_el, user_modifica, modificado_el);
                    
                    vr.add(prov);                    
                }
                
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return vr.toArray(new Varios[0]);
    }
    
    public boolean modificarTipoVarios (String nombre_tabla, String col_name_id, Varios tipo_docu){
        String sql = "UPDATE " + nombre_tabla + " SET nombre_tipo = ?, user_modifica = ?, modificado_el = ? WHERE " + col_name_id + " = ?";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setString(1, tipo_docu.getNombre());
            stat.setString(2, tipo_docu.getUser_modifica());
            stat.setTimestamp(3, tipo_docu.getModificado_el());
            stat.setInt(4, tipo_docu.getCodigo());
            
            int filas_afec = stat.executeUpdate();
            return filas_afec > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarTipoVarios (String nombre_tabla, String col_name_id, int codigo) {
        String sql = "DELETE FROM " + nombre_tabla + " WHERE " + col_name_id + " = ?";
        
        try (Connection cx = ConexionBD.getConnection();
                PreparedStatement stat = cx.prepareStatement(sql)){
            
            stat.setInt(1, codigo);
            int filas_afec = stat.executeUpdate();
            
            return filas_afec > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Object[][] toTableObject (Varios[] tipos){
        Object[][] datos = new Object[tipos.length][6];
        
        for (int i = 0; i < tipos.length; i++) {
            datos[i][0] = tipos[i].getCodigo();
            datos[i][1] = tipos[i].getNombre();
            datos[i][2] = tipos[i].getUser_crea();
            datos[i][3] = tipos[i].getCreado_el();
            datos[i][4] = tipos[i].getUser_modifica();
            datos[i][5] = tipos[i].getModificado_el();
        }
        
        return datos;
    }
}
