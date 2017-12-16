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
    
        //ʹ��load���ض����ʱ��ֻ�е�����������������ʱ���Ż�������ݿ⡣
        Product p = (Product)s.load(Product.class, 3);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");

        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
