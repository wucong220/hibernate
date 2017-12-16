package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bean.Category_annotation;
import bean.Product_annotation;

public class TestAnnotationRelationships {
	public static void main_oneToMany(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		
		Product_annotation pa = new Product_annotation();
		Category_annotation ca = new Category_annotation();
		pa.setName("ﬂÎﬂÎ");
		ca.setName("∆§∆§œ∫");
		
		pa.setCategory(ca);
		s.save(ca);
		s.save(pa);
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		s.beginTransaction();
		Category_annotation ca = (Category_annotation) s.get(Category_annotation.class,2);

//      ¥¥Ω® 10∏ˆﬂÎﬂÎ
//		Product_annotation pa = new Product_annotation();
//		for(int i = 0 ;i<10;i++){
//		pa.setName("ﬂÎﬂÎ "+i);
//		s.save(pa);
//		pa = new Product_annotation();
//		}

//		Ω´’‚10∏ˆﬂÎﬂÎªØŒ™∆§∆§œ∫2∫≈¿‡
		Criteria c = s.createCriteria(Product_annotation.class);
		c.add(Restrictions.like ("name","%"+"ﬂÎﬂÎ"+"%"));
		List<Product_annotation> pas = (List<Product_annotation>) c.list();
//		for (Product_annotation pa : pas) {
//			pa.setCategory(ca);
//			s.save(pa);
//		}
		
		ca = (Category_annotation) s.get(Category_annotation.class,1);
		Set<Product_annotation> paset =new HashSet<>();
		paset.addAll(pas);
		ca.setProducts(paset);
		
		
		
		
		
		
		
		
		
		s.getTransaction().commit();
		s.close();
		sf.close();
	}
	
}
