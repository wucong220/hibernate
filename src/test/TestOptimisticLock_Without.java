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
		System.out.println("产品原本价格是: " + p1.getPrice());

		p1.setPrice(p1.getPrice() + 1000);

		Product p2 = (Product) s2.get(Product.class, 3);
		p2.setPrice(p2.getPrice() + 1000);

		s1.update(p1);
		s2.update(p2);

		s1.getTransaction().commit();
		s2.getTransaction().commit();

		Product p = (Product) s1.get(Product.class, 3);

		System.out.println("经过两次价格增加后，价格变为: " + p.getPrice());

		s1.close();
		s2.close();

		sf.close();

		/*
		 * <version name="version" column="ver" type="int"></version>
		 * 增加一个version字段，用于版本信息控制。这就是乐观锁的核心机制。
		 * http://stepimage.how2j.cn/1831.png
		 */
	}

}
