package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Category;

public class TestBufferSecondLevel {
	
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();
		Category p1 = (Category) s.get(Category.class, 1);
		Category p2 = (Category) s.get(Category.class, 1);
		s.getTransaction().commit();
		s.close();
		Session s2 = sf.openSession();
		s2.beginTransaction();
		Category p3 = (Category) s2.get(Category.class, 1);

		s2.getTransaction().commit();
		s2.close();
		sf.close();
		//û�п��������������Ի�������SQL��䡣
		//�����˶��������ֻ��һ�����
	}
}
