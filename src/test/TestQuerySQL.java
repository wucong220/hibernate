package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestQuerySQL {
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();
		s.beginTransaction();

		String name = "iphone";
		
		String sql = "select * from product_ p where p.name like '%"+name+"%'";
		
		Query q= s.createSQLQuery(sql);
		List<Object[]> list= q.list();
		for (Object[] os : list) {
			for (Object filed: os) {
				System.out.print(filed+"\t");
			}
			System.out.println();
		}
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
}
