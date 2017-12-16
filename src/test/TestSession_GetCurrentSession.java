package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Product;

public class TestSession_GetCurrentSession {
	static Session f1;
	static Session f2;
	
	public static void main(String[] args) throws InterruptedException {
		final SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s1 = sf.getCurrentSession();
		Session s2 = sf.getCurrentSession();

		System.out.println("相同线程:"+(s1 == s2));

		s1.close();
		//s2.close(); 。
		//sf.close();
		//getCurrentSession是所有操作都必须放在事务中进行，并且提交事务后，session就自动关闭，不能够再进行关闭
		//如果是在同一个线程中（比如主线程），每次获取都是相同的Session。
		//getCurrentSession所有操作都必须放在事物中不然会出错；
		Thread t1 = new Thread(){
			public void run(){
				System.out.println(sf);
				f1 = sf.getCurrentSession();
			}
		};
		t1.start();
		
		Thread t2 = new Thread(){
			public void run(){
				System.out.println(sf);
				f2 = sf.getCurrentSession();
			}
		};
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("不同线程；"+(f1==f2));
		
		s1 = sf.getCurrentSession();
		
		s1.beginTransaction();
		s1.get(Product.class, 5);
		s1.getTransaction().commit();
		s1.close();
		//在事务关闭后，试图关闭session,就会报session已经关闭的异常
		sf.close();
	}
}
