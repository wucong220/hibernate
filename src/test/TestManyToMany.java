package test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;
import bean.User;

public class TestManyToMany {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		s.beginTransaction();

		//���������û�
		Set<User> users = new HashSet<>();
		for (int i = 0; i < 3; i++) {
			User u = new User();
			u.setName("user" + i);
			users.add(u);
			s.save(u);
		}
		
		
		//��Ʒ1���û�1��2��3����
		Product p1 = (Product)s.get(Product.class, 2);
		
		p1.setUsers(users);
		s.save(p1);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
