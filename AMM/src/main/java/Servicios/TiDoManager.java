package Servicios;
/**
 *
 * @author nyath
 */
import Clases.Tipo_documento;
import Utils.SessionHibernate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TiDoManager {
    public boolean crearTipoDocumento(Tipo_documento tipo_documento){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transacción
            
            session.persist(tipo_documento); //guarda el registro en la base de datos
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Tipo_documento buscarTipoDocumento(int id_tipo_documento) {        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Tipo_documento.class, id_tipo_documento); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Tipo_documento> getAllTipoDocumento () {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Tipo_documento", Tipo_documento.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
    
    public boolean modificarTipoDocumento (Tipo_documento tipo_documento) {
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            session.merge(tipo_documento); //modificar el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
    
    public boolean eliminarTipoDocumento (int id_tipo_documento) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            //buscar cliente por codigo
            Tipo_documento ti_do = session.get(Tipo_documento.class, id_tipo_documento);
            
            if(ti_do != null){
                session.remove(ti_do); //elimina el registro
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
