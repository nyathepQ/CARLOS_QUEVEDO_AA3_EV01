package Servicios;

import Clases.Tipo_usuario;
import Utils.SessionHibernate;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author nyath
 */


public class TiUsManager {
    public Tipo_usuario buscarTipoUsuario(int id_tipo_usuario) {        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Tipo_usuario.class, id_tipo_usuario); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Tipo_usuario> getAllTipoUsuario () {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Tipo_usuario", Tipo_usuario.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
}
