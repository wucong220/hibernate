package test;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;
 
public class TestLazyLoadAttribute {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
    
        //使用load加载对象的时候只有当访问这个对象的属性时，才会访问数据库。
        Product p = (Product)s.load(Product.class, 3);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");

        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
