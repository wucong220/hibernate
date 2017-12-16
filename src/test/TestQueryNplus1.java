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
		 * ���ʹ��query��list�����ͻ� ���ӻ��棬ֱ�Ӵ����ݿ��ж�ȡ��
		 * 
		 * hibernateʹ��iterator��ʵ��N+1����ͨ��Query��iterator����������������id�����
		 * Ȼ����ͨ��it.next()��ѯÿһ�����������������ٻ����У���ֱ�Ӵӻ����л�ȡ������ʹ����� ���л�ȡ��
		 * N+1�е�1������ָֻ����ID��Sql��䣬Nָ��������ٻ������Ҳ�����Ӧ�����ݣ��͵����ݿ� ��ȥ��
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
