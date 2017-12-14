package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestDelete {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		s.beginTransaction();

		Product p = (Product) s.get(Product.class, 1);
		s.delete(p);

		s.getTransaction().commit();
		s.close();
		sf.close();

	}
}
