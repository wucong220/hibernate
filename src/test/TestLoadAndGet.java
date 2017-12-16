package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestLoadAndGet {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();
		System.out.println("log1");
		Product p = (Product) s.get(Product.class, 1);
		System.out.println("log2");
		Product p2 = (Product) s.load(Product.class, 2);
		System.out.println("log3");
		System.out.println(p2.getName());
		System.out.println("log4");
		//load���ӳټ��أ�ֻ�е����ʶ��������ʱ���Ż�ִ��SQL��䡣
		//get�����ӳټ��أ������Ƿ����ʶ�������ԣ�������ִ��SQL��䡣
		
        Product p3 = (Product)s.get(Product.class, 500);
        System.out.println("p3="+p3);
        
        Product p4 = (Product)s.load(Product.class, 500);
        System.out.println("p3="+p4);
        
        //get���ʲ����ڵĶ���᷵��null
        //load���ʲ����ڵĶ�����׳��쳣
		
		s.getTransaction().commit();
		s.close();
		sf.close();

		
	}
}
