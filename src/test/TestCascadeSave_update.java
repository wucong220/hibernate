package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;
import bean.Product;

public class TestCascadeSave_update {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Category c = (Category) s.get(Category.class, 5);
		
		Product p1 = new Product();
		p1.setName("product_501");
		Product p2 = new Product();
		p2.setName("product_502");
		Product p3 = new Product();
		p3.setName("product_503");

		c.getProducts().add(p1);
		c.getProducts().add(p2);
		c.getProducts().add(p3);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	public static void main4delete(String[] args) {
		  SessionFactory sf = new Configuration().configure().buildSessionFactory();
	        Session s = sf.openSession();
	        s.beginTransaction();
	        Category c = (Category) s.get(Category.class, 3);
	        s.delete(c);
	        s.getTransaction().commit();
	        s.close();
	        sf.close();
	}
}
