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
		//openSession()��ѯ����Ҫ�������н���
		
		s1.close();
		s2.close();
		sf.close();
		//OpenSession ÿ�ζ���õ�һ���µ�Session��������S1==S2�᷵��false
	}
}
