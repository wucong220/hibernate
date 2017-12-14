package test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bean.Product;

public class TestQueryCriteria {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		String name = "iphone";
		
		Criteria c = s.createCriteria(Product.class);
		c.add(Restrictions.like("name","%"+name+"%"));
		List<Product> ps = c.list();
		for (Product product : ps) {
			System.out.println(product.getName());
		}
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
