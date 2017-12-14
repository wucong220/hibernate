package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestHibernate {
	public static void main(String[] args) {
		// ��ȡSessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		// ͨ��SessionFactory ��ȡһ��Session
		Session s = sf.openSession();

		// ��Session�����Ͽ���һ������
		s.beginTransaction();

		// ͨ������Session��save�����Ѷ��󱣴浽���ݿ�
		for (int i = 0; i < 10; i++) {
			Product p = new Product();
			p.setName("iphone"+i);
			// p.setPrice(7000);
			s.save(p);
		}

		// �ύ����
		s.getTransaction().commit();

		// �ر�Session
		s.close();

		// �ر�SessionFactory
		sf.close();
	}

}
