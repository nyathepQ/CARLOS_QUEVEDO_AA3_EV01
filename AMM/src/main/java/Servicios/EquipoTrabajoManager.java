package Servicios;

import Clases.ConexionBD;
import Clases.EquipoTrabajo;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nyath
 */
public class EquipoTrabajoManager {
    
    public boolean insertEquipo(EquipoTrabajo equipo){
        String sql1 = "INSERT INTO equipo (nombre_equipo, user_crea) VALUES (?, ?)";
        String sql2 = "INSERT INTO usuarios_equipo (id_equipo, id_usuario, es_lider) VALUES (?, ?, ?)";
        
        
        try (Connection cx = ConexionBD.getConnection()){            
            cx.setAutoCommit(false);
            
            //insertar en tabla equipo
            try (PreparedStatement stat1 = cx.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)){
                stat1.setString(1, equipo.getNombre_equipo());
                stat1.setString(1, equipo.getUser_crea());
                
                stat1.executeUpdate();
                //obtener id del equipo generado
                int idEquipoGenerado;
                try (ResultSet rs = stat1.getGeneratedKeys()){
                    if(rs.next()){
                        idEquipoGenerado = rs.getInt(1);
                    } else {
                        cx.rollback();
                        System.err.println("Error al insertar equipo");
                        return false;

                    }
                }
                
                //insertar en usuarios_equipo
                try (PreparedStatement stat2 = cx.prepareStatement(sql2)){
                    //obtener miembros
                    String[] miembros = {equipo.getLider(), equipo.getMiembro1(), equipo.getMiembro2()};
                    //crear registros de los tres miembros, null si no esta ocupado el registro
                    for (int i = 0; i < 3; i++){
                        stat2.setInt(1, idEquipoGenerado);
                        
                        if(miembros[i] != null && !miembros[i].isEmpty()){
                            stat2.setString(2, miembros[i]);
                        } else {
                            stat2.setNull(2, Types.VARCHAR);
                        }
                        
                        stat2.setInt(3, i == 0 ? 1 : 0); //el primer registro es lider
                        stat2.addBatch();
                    }
                    
                    
                    stat2.executeBatch(); //Se ejecutan todos los inserts
                }
                
                cx.commit();
                return true;
            } catch (SQLException e) {
                cx.rollback();
                System.err.println("Error al crear equipo: " + e.getMessage());
                return false;
            }            
        } catch (SQLException e) {
            System.err.println("Error al crear equipo: " + e.getMessage());
            return false;
        }
    }
    
    public EquipoTrabajo buscarEquipo (int codigo_b) {
        Connection cx = ConexionBD.getConnection();
        EquipoTrabajo eq = null;
        
        if(cx != null) {
            String sql1 = "SELECT * FROM equipo WHERE id_equipo = ?";
            String sql2 = "SELECT id_equipo, id_usuario, es_lider FROM usuarios_equipo WHERE id_equipo = ?";
            
            try {
                //consulta tabla equipo
                PreparedStatement stat1 = cx.prepareStatement(sql1);                
                stat1.setInt(1, codigo_b);
                ResultSet rs1 = stat1.executeQuery();
                
                if(rs1.next()) {
                    eq = new EquipoTrabajo();
                    eq.setCodigo(codigo_b);
                    eq.setNombre_equipo(rs1.getString("nombre_equipo"));
                    eq.setUser_crea(rs1.getString("user_crea"));
                    eq.setCreado_el(rs1.getTimestamp("creado_el"));
                    eq.setUser_modifica(rs1.getString("user_modifica"));
                    eq.setModificado_el(rs1.getTimestamp("modificado_el"));
                    
                    //consulta tabla usuarios_equipo
                    PreparedStatement stat2 = cx.prepareStatement(sql2);
                    stat2.setInt(1, codigo_b);
                    ResultSet rs2 = stat2.executeQuery();
                    
                    int contador = 0;
                    
                    while(rs2.next()) {
                        String idUser = rs2.getString("id_usuario");
                        boolean esLider = rs2.getBoolean("es_lider");
                        
                        if(esLider){
                            eq.setLider(idUser);
                        } else {
                            contador++;
                            if (contador == 1){
                                eq.setMiembro1(idUser);
                            } else if (contador == 2) {
                                eq.setMiembro2(idUser);
                            }
                        }
                    }
                    
                    rs2.close();
                    stat2.close();
                }
                
                rs1.close();
                stat1.close();
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return eq;
    }
    
    public EquipoTrabajo[] getAllEquipo () {
        Connection cx = ConexionBD.getConnection();
        List<EquipoTrabajo> equi = new ArrayList<>();
        
        if(cx != null) {
            String sql1 = "SELECT * FROM equipo";
            String sql2 = "SELECT id_equipo, id_usuario, es_lider FROM usuarios_equipo WHERE id_equipo = ?";
            
            try {
                //consulta tabla equipo
                PreparedStatement stat1 = cx.prepareStatement(sql1);                
                
                ResultSet rs1 = stat1.executeQuery();
                
                while(rs1.next()) {
                    EquipoTrabajo temp = new EquipoTrabajo();
                    temp.setCodigo(rs1.getInt("id_equipo"));
                    temp.setNombre_equipo(rs1.getString("nombre_equipo"));
                    temp.setUser_crea(rs1.getString("user_crea"));
                    temp.setCreado_el(rs1.getTimestamp("creado_el"));
                    temp.setUser_modifica(rs1.getString("user_modifica"));
                    temp.setModificado_el(rs1.getTimestamp("modificado_el"));
                    
                    //consulta tabla usuarios_equipo
                    PreparedStatement stat2 = cx.prepareStatement(sql2);
                    stat2.setInt(1, temp.getCodigo());
                    ResultSet rs2 = stat2.executeQuery();
                    
                    int contador = 0;
                    
                    while(rs2.next()) {
                        String idUser = rs2.getString("id_usuario");
                        boolean esLider = rs2.getBoolean("es_lider");
                        
                        if(esLider){
                            temp.setLider(idUser);
                        } else {
                            contador++;
                            if (contador == 1){
                                temp.setMiembro1(idUser);
                            } else if (contador == 2) {
                                temp.setMiembro2(idUser);
                            }
                        }
                    }
                    
                    equi.add(temp);
                    rs2.close();
                    stat2.close();
                }
                
                rs1.close();
                stat1.close();
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return equi.toArray(new EquipoTrabajo[0]);
    }
    
    public boolean modificarEquipo (EquipoTrabajo equipo) {
        String sql1 = "UPDATE equipo SET nombre_equipo = ?, user_modifica = ?, modificado_el = ? WHERE id_equipo = ?";
        String sqlSelect = "SELECT id_usuarios_equipo FROM usuarios_equipo WHERE id_equipo = ?";
        String sql3 = "UPDATE usuarios_equipo SET id_usuario = ?, es_lider = ? WHERE id_usuarios_equipo = ?";
        
        
        try (Connection cx = ConexionBD.getConnection()){
            cx.setAutoCommit(false);
            
            //modificar tabla equipo
            try (PreparedStatement stat1 = cx.prepareStatement(sql1)){
                stat1.setString(1, equipo.getNombre_equipo());
                stat1.setString(2, equipo.getUser_modifica());
                stat1.setTimestamp(3, equipo.getModificado_el());
                stat1.setInt(4, equipo.getCodigo());
                stat1.executeUpdate();
            }
            
            //obtener id de los registros en usuarios_equipo
            List<Integer> registroUsuario = new ArrayList<>();
            try (PreparedStatement statSelect = cx.prepareStatement(sqlSelect)){
                statSelect.setInt(1, equipo.getCodigo());
                ResultSet rs = statSelect.executeQuery();
                while(rs.next()){
                    registroUsuario.add(rs.getInt("id_usuarios_equipo"));
                }
            }
            
            //preparar los datos nuevos
            List<String> nuevosUsuario = Arrays.asList (
                equipo.getLider(),
                equipo.getMiembro1(),
                equipo.getMiembro2()
            );
            
            //actualizar los registros de los miembros del equipo
            try (PreparedStatement stat3 = cx.prepareStatement(sql3)){
                for(int i = 0; i < registroUsuario.size(); i++){
                    int id_usuarios_equipo = registroUsuario.get(i);
                    
                    stat3.setString(1, nuevosUsuario.get(i));
                    stat3.setInt(2, i == 0 ? 1 : 0);//solo el primer registro es lider
                    stat3.setInt(3, id_usuarios_equipo);
                    stat3.addBatch();
                }
                
                stat3.executeBatch();
            }
            
            cx.commit();
            return true;            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarEquipo (int codigo_equipo) {
        String sql1 = "DELETE FROM equipo WHERE id_equipo = ?";
        String sql2 = "DELETE FROM usuarios_equipo WHERE id_equipo = ?";
        
        try (Connection cx = ConexionBD.getConnection()){
            cx.setAutoCommit(false);
            
            //eliminar las referencias en la tabla usuarios_equipo para evitar error
            try (PreparedStatement stat1 = cx.prepareStatement(sql2)){
                stat1.setInt(1, codigo_equipo);
                stat1.executeUpdate();
            }
            //eliminar equipo
            try (PreparedStatement stat2 = cx.prepareStatement(sql1)){
                stat2.setInt(1, codigo_equipo);
                stat2.executeUpdate();
            }
            
            cx.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Object[][] toTableObject (EquipoTrabajo[] equipo){
        Object[][] datos = new Object[equipo.length][9];
        
        for (int i = 0; i < equipo.length; i++) {
            datos[i][0] = equipo[i].getCodigo();
            datos[i][1] = equipo[i].getNombre_equipo();
            datos[i][2] = equipo[i].getLider();
            datos[i][3] = equipo[i].getMiembro1();
            datos[i][4] = equipo[i].getMiembro2();
            datos[i][5] = equipo[i].getUser_crea();
            datos[i][6] = equipo[i].getCreado_el();
            datos[i][7] = equipo[i].getUser_modifica();
            datos[i][8] = equipo[i].getModificado_el();
        }
        
        return datos;
    }
}
