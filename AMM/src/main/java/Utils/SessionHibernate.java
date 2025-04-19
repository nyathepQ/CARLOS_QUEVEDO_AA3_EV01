package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author nyath
 */
public class SessionHibernate {
    //obtener instancia de sessionfactory
    private static final SessionFactory sessionFactory;
    
    //intentarconstruir SessionFactory
    static {
        try {
            //leer la configuración por defecto en hibernate.cfg.xml y se construye
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    //Metodo para obtener la sesión
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
