package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestSession_OpenSession {
	public static void main(String[] args) {
		SessionFactory sf =  new Configuration().configure().buildSessionFactory();
		
		Session s1 = sf.openSession();
		Session s2 = sf.openSession();
		
		System.out.println(s1==s2);
		
		s1.get(Product.class, 5);
		//openSession()查询不需要在事物中进行
		
		s1.close();
		s2.close();
		sf.close();
		//OpenSession 每次都会得到一个新的Session对象，所以S1==S2会返回false
	}
}
