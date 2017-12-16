package test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestQueryNplus1 {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		String name = "iphone";

		Query q = s.createQuery("from Product p where p.name like ?");
		q.setString(0, "%" + name + "%");

		/*
		 * 如果使用query的list方法就会 无视缓存，直接从数据库中读取。
		 * 
		 * hibernate使用iterator来实现N+1，先通过Query的iterator把所有满足条件的id查出来
		 * 然后再通过it.next()查询每一个对象：如果这个对象再缓存中，就直接从缓存中获取，否则就从数据 库中获取。
		 * N+1中的1，就是指只返回ID的Sql语句，N指的是如果再缓存中找不到对应的数据，就到数据库 中去查
		 */
		
		Iterator<Product> it = q.iterate();
		while (it.hasNext()) {
			Product p = it.next();
			System.out.println(p.getName());
		}

		s.getTransaction().commit();
		s.close();
		sf.close();

	}
}
