package test;
  
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
public class TestC3P0ConnectionPool {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
  
        s.createQuery("from Category").list();
          
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
