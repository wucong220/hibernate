package test;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class TestQueryCount {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
 
        String name = "iphone";
        
        Query q =s.createQuery("select count(*) from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        long total= (Long) q.uniqueResult();
        System.out.println(total);
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
 
}
