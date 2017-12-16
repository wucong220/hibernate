package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestOptimisticLock_Without {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s1 = sf.openSession();
		Session s2 = sf.openSession();

		s1.beginTransaction();
		s2.beginTransaction();

		Product p1 = (Product) s1.get(Product.class, 3);
		System.out.println("��Ʒԭ���۸���: " + p1.getPrice());

		p1.setPrice(p1.getPrice() + 1000);

		Product p2 = (Product) s2.get(Product.class, 3);
		p2.setPrice(p2.getPrice() + 1000);

		s1.update(p1);
		s2.update(p2);

		s1.getTransaction().commit();
		s2.getTransaction().commit();

		Product p = (Product) s1.get(Product.class, 3);

		System.out.println("�������μ۸����Ӻ󣬼۸��Ϊ: " + p.getPrice());

		s1.close();
		s2.close();

		sf.close();

		/*
		 * <version name="version" column="ver" type="int"></version>
		 * ����һ��version�ֶΣ����ڰ汾��Ϣ���ơ�������ֹ����ĺ��Ļ��ơ�
		 * http://stepimage.how2j.cn/1831.png
		 */
	}

}