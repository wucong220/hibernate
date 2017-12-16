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
		//load是延迟加载，只有当访问对象的属性时，才会执行SQL语句。
		//get不是延迟加载，不管是否会访问对象的属性，会马上执行SQL语句。
		
        Product p3 = (Product)s.get(Product.class, 500);
        System.out.println("p3="+p3);
        
        Product p4 = (Product)s.load(Product.class, 500);
        System.out.println("p3="+p4);
        
        //get访问不存在的对象会返回null
        //load访问不存在的对象会抛出异常
		
		s.getTransaction().commit();
		s.close();
		sf.close();

		
	}
}
