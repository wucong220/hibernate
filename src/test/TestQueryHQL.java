package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;
import bean.Product;

public class TestQueryHQL {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		
		Category category = (Category)s.get(Category.class, 1);
		
		String name = "iphone";
		Query q = s.createQuery("from Product p where p.name like ?");
		q.setString(0,"%"+name+"%");//和PreparedStatement不一样这里是基0的
		List<Product> ps = q.list();
		for (Product product : ps) {
			product.setCategory(category);
			System.out.println(product.getName());
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
