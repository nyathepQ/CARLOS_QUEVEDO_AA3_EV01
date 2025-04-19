package Servicios;
/**
 *
 * @author nyath
 */
import Clases.Servicio;
import Clases.Usuario;
import Utils.SessionHibernate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioManager {
    public boolean crearUsuario(Usuario usuario){
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transacción
            
            session.persist(usuario); //guarda el registro en la base de datos
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false;
        }
    }
    
    public Servicio buscarUsuario(String id_usuario) {
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            return session.get(Servicio.class, id_usuario); //buscar el registro por id
        } catch (Exception e) {
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }
    
    public List<Usuario> getAllUsuario () {
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            //obtener y retornar todos los registro con Query HQL
            return session.createQuery("FROM Usuario", Usuario.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return List.of(); //Retorna una lista vacia si existe algun error
        }
    }
    
    public boolean modificarUsuario (Usuario usuario) {
        Transaction tx = null;
        
        try (Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            session.merge(usuario); //modificar el registro
            
            tx.commit(); //confirma la transacción
            return true;
        } catch (Exception e){
            if (tx != null) tx.rollback(); //revertir transacción si hay error
            e.printStackTrace();
            return false; //retornar falso si hay error
        }
    }
    
    public boolean eliminarUsuario (String id_usuario) {
        Transaction tx = null;
        
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            tx = session.beginTransaction(); //iniciar transaccion
            
            //buscar cliente por codigo
            Usuario usuario = session.get(Usuario.class, id_usuario);
            
            if(usuario != null){
                session.remove(usuario); //elimina el registro
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
    
    public Usuario verificarCredencial(String user, String pass){
        try(Session session = SessionHibernate.getSessionFactory().openSession()){
            //secuencia hql para buscar el registro
            String hql = "FROM USUARIO WHERE nombre_usuario = :user AND contrasena_usuario = :pass";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("user", user);
            query.setParameter("pass", pass);
            
            //retorna un unico resultado o null si no hay coincidencia
            return query.uniqueResult();            
        } catch (Exception e){
            e.printStackTrace();
            return null; //retornar null si hay error
        }
    }    
}
