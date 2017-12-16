package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;

public class TestLazyLoadRelationship {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();
		Category c = (Category) s.get(Category.class, 1);
		
		System.out.println("log1");
		System.out.println(c.getProducts());
		System.out.println("log1");

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}

/*执行15行的时候，只会查询Category表的信息，不会查询product_表

只有在执行18行，通过category取products的时候，才会进行对product_表的查询 */