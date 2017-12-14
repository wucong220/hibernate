package test;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;
import bean.Product;

public class TestOneToMany {
	public static void main(String[] args) {
		SessionFactory sf =  new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Category c = (Category)s.get(Category.class, 1);
		Set<Product> ps = c.getProducts();
		
		for (Iterator iterator = ps.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println(product.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
