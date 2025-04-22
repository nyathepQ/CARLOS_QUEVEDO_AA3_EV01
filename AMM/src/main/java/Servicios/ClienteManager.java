package Servicios;
/**
 *
 * @author nyath
 */
import Clases.Cliente;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Utils.SessionHibernate;
import org.hibernate.query.Query;

public class ClienteManager {
    
    public boolean crearCliente(Cliente cliente){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transacción
            
            session.persist(cliente); //guarda el registro en la base de datos
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Cliente buscarCliente(int id_cliente) {        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Cliente.class, id_cliente); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Cliente> getAllCliente() {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Cliente", Cliente.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
    
    public boolean modificarCliente(Cliente cliente) {
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            session.merge(cliente); //modificar el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
    
    public boolean eliminarCliente(int id_cliente) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            //buscar cliente por codigo
            Cliente cliente = session.get(Cliente.class, id_cliente);
            
            if(cliente != null){
                session.remove(cliente); //elimina el registro
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
    
    public Cliente getLast(){
        Cliente ultimo = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery(
                "FROM Cliente ORDER BY creado_el DESC", Cliente.class);
            query.setMaxResults(1);
            ultimo = query.uniqueResult();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return ultimo;
    }
}
