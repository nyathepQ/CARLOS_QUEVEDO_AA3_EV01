package Servicios;
/**
 *
 * @author nyath
 */
import Clases.Servicio;
import Utils.SessionHibernate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicioManager {
    public boolean crearServicio(Servicio servicio){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transacción
            
            session.merge(servicio); //guarda el registro en la base de datos
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Servicio buscarServicio(int id_servicio) {
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Servicio.class, id_servicio); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Servicio> getAllServicio () {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Servicio", Servicio.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
    
    public boolean modificarServicio (Servicio servicio) {
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            session.merge(servicio); //modificar el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
    
    public boolean eliminarServicio (int id_servicio) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            //buscar cliente por codigo
            Servicio servicio = session.get(Servicio.class, id_servicio);
            
            if(servicio != null){
                session.remove(servicio); //elimina el registro
                tx.commit(); //confirma la transacción
                return true; //retornar true
            }
            
            //si el cliente no se encuentra
            return false;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
}
