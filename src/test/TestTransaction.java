package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestTransaction {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		Product p = (Product) s.get(Product.class, 2);
		s.delete(p);

		Product p2 = (Product) s.get(Product.class, 3);
		p2.setName("���ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ���Ƴ��ȳ���30���ַ�����Ϊ��Ʒ����");
		s.update(p2);

		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
