package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestModify {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Product p = (Product)s.get(Product.class, 6);
		p.setName("iphone-modified");
		s.update(p);
		
		s.getTransaction().commit();
		
		s.close();
		sf.close();
	}
}
