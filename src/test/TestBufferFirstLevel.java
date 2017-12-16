package test;
  
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;
  
public class TestBufferFirstLevel {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
        System.out.println("log1");
        Category c1 = (Category)s.get(Category.class, 1);//第一次Session中没有对应的对象，有SQL语句
        System.out.println("log2");
        Category c2= (Category)s.get(Category.class, 1);//第二次Session中有对应的对象，没有SQL语句
        System.out.println("log3");        

        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
