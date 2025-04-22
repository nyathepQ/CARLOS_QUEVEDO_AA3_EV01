package Servicios;

import Clases.Tipo_limpieza;
import Utils.SessionHibernate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author nyath
 */


public class TiLiManager {
    public boolean crearTipoLimpieza(Tipo_limpieza tipo_limpieza){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transacción
            
            session.persist(tipo_limpieza); //guarda el registro en la base de datos
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Tipo_limpieza buscarTipoLimpieza(int id_tipo_limpieza) {
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Tipo_limpieza.class, id_tipo_limpieza); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Tipo_limpieza> getAllTipoLimpieza () {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Tipo_limpieza", Tipo_limpieza.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
    
    public boolean modificarTipoLimpieza (Tipo_limpieza tipo_limpieza) {
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            session.merge(tipo_limpieza); //modificar el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
    
    public boolean eliminarTipoLimpieza (int id_tipo_limpieza) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            //buscar cliente por codigo
            Tipo_limpieza ti_li = session.get(Tipo_limpieza.class, id_tipo_limpieza);
            
            if(ti_li != null){
                session.remove(ti_li); //elimina el registro
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
    
    public Tipo_limpieza getLast(){
        Tipo_limpieza ultimo = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()) {
            Query<Tipo_limpieza> query = session.createQuery(
                "FROM Tipo_limpieza ORDER BY creado_el DESC", Tipo_limpieza.class);
            query.setMaxResults(1);
            ultimo = query.uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return ultimo;
    }
}
