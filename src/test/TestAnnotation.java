package test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import bean.Product_annotation;

public class TestAnnotation {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Product_annotation p_a = new Product_annotation();
		p_a.setId(666);
		p_a.setName("Ð¡Íõ°Ëµ°");
		p_a.setPrice(66.66f);
		s.save(p_a);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
