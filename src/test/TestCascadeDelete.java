package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;

public class TestCascadeDelete {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Category c = (Category) s.get(Category.class, 5);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
