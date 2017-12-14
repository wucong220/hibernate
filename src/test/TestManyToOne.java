package test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import bean.Category;
import bean.Product;

public class TestManyToOne {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Category category = new Category();
		category.setName("c1");
		s.save(category);
		
		Product product = (Product)s.get(Product.class, 8);
		product.setCategory(category);
		s.update(product);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
