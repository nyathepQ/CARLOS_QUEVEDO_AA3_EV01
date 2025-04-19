package Servicios;
/**
 *
 * @author nyath
 */
import Clases.Equipo;
import Clases.Usuario;
import Clases.Usuarios_equipo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Utils.SessionHibernate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquipoManager {
    
    public boolean crearEquipo(Equipo equipo){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){ //abre session
            tx = session.beginTransaction(); //iniciar transacción
            
            
            //lista de usuarios_equipo
            List<Usuarios_equipo> usuariosEquipoList = new ArrayList<>();
            
            String[] miembros = {
                equipo.getLider(),
                equipo.getMiembro1(),
                equipo.getMiembro2()
            };
            
            //agregar miembros a la tabla usuarios_equipo
            for(int i = 0; i < miembros.length; i++){
                String empleados = miembros[i];
                if (empleados != null && !empleados.trim().isEmpty()){
                    //buscar usuario por id
                    Usuario us = session.get(Usuario.class, empleados);
                    if(us != null) {
                        Usuarios_equipo ue = new Usuarios_equipo();
                        ue.setEquipo(equipo);
                        ue.setUsuario(us);
                        ue.setEs_lider(i == 0); //el primer registro es lider
                        usuariosEquipoList.add(ue);
                    }
                }
                
            }
            
            equipo.setUsuariosEquipos(usuariosEquipoList);
            
            session.persist(equipo); //guarda el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //se revierte la transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Equipo buscarEquipo(int id_equipo) {
        Transaction tx = null;
        Equipo equipo = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //comienza la transacción
            
            //obtener equipo por id
            equipo = session.get(Equipo.class, id_equipo);
            
            if(equipo != null){
                //cargar usuarios del equipo desde lista usuariosEquipo
                List<Usuarios_equipo> usuarios = equipo.getUsuariosEquipos();
                
                //ciclo para setear miembros
                int contador = 0;
                for (Usuarios_equipo ue : usuarios){
                    String idUser = ue.getUsuario().getId_usuario(); //cargar id de usuarios desde usuario en usuario equipo
                    if (ue.getEs_lider() == true){
                        equipo.setLider(idUser);
                    } else {
                        contador++;
                        if(contador == 1){
                            equipo.setMiembro1(idUser);
                        } else if (contador == 2){
                            equipo.setMiembro2(idUser);
                        }
                    }
                }
            }
            
            tx.commit(); //confirmar la transacción
        } catch (Exception e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        
        return equipo;
    }
    
    public List<Equipo> getAllEquipo() {
        Transaction tx = null;
        List<Equipo> equipos = new ArrayList<>();
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            
            //obtener otods los equipos
            List<Equipo> lista = session.createQuery("From Equipo", Equipo.class).list();
            
            //ciclo para obtener miembros
            for(Equipo equ : lista) {
                //obtener los usuarios asociados al equipo
                List<Usuarios_equipo> usuarios = equ.getUsuariosEquipos();
                int contador = 0;
                
                for (Usuarios_equipo ue : usuarios) {
                    String idUsuario = ue.getUsuario().getId_usuario();
                    if(ue.getEs_lider() == true) {
                        equ.setLider(idUsuario);
                    } else {
                        contador++;
                        if (contador == 1){
                            equ.setMiembro1(idUsuario);
                        } else if (contador == 2){
                            equ.setMiembro2(idUsuario);
                        }
                    }
                }
                equipos.add(equ);
            }
            
            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        
        return equipos;
    }
    
    public boolean modificarEquipo(Equipo equipo) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            
            //obtener el equipo acutal desde la bd
            Equipo equipoExistente = session.get(Equipo.class, equipo.getId_equipo());
            if(equipoExistente == null){ //si hubo codigo o no existe retornar false
                return false;
            }
            
            //actualizar datos del equipo
            equipoExistente.setNombre_equipo(equipo.getNombre_equipo());
            equipoExistente.setUser_modifica(equipo.getUser_modifica());
            equipoExistente.setModificado_el(equipo.getModificado_el());
            
            //obtener los usuarios_equipo existentes
            List<Usuarios_equipo> usuariosActuales = equipoExistente.getUsuariosEquipos();
            
            //nuevos usuarios a establecer
            List<String> nuevosIds = Arrays.asList(
                equipo.getLider(),
                equipo.getMiembro1(),
                equipo.getMiembro2()
            );
            
            //ciclo para modificar usuarios actuales
            for(int i = 0; i < usuariosActuales.size(); i++){
                Usuarios_equipo ue = usuariosActuales.get(i);
                String nuevoId = nuevosIds.get(i);
                
                if(nuevoId != null && !nuevoId.isEmpty()){
                    Usuario usuario = session.get(Usuario.class, nuevoId);
                    ue.setUsuario(usuario);
                    ue.setEs_lider(i == 0 ? true : false);
                } else {
                    ue.setUsuario(null);
                }
                
                session.merge(ue);
            }
            
            session.merge(equipoExistente);
            tx.commit();
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarEquipo(int id_equipo) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            
            //obtener el equipo
            Equipo equipo = session.get(Equipo.class, id_equipo);
            if(equipo == null){//si no existe el registro return false
                return false;
            }
            
            //eliminar el equipo (hibernate elminara usuarios_equipo por cascada)
            session.remove(equipo);
            
            tx.commit();
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
