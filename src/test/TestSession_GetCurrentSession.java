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

		System.out.println("��ͬ�߳�:"+(s1 == s2));

		s1.close();
		//s2.close(); ��
		//sf.close();
		//getCurrentSession�����в�����������������н��У������ύ�����session���Զ��رգ����ܹ��ٽ��йر�
		//�������ͬһ���߳��У��������̣߳���ÿ�λ�ȡ������ͬ��Session��
		//getCurrentSession���в�����������������в�Ȼ�����
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
		
		System.out.println("��ͬ�̣߳�"+(f1==f2));
		
		s1 = sf.getCurrentSession();
		
		s1.beginTransaction();
		s1.get(Product.class, 5);
		s1.getTransaction().commit();
		s1.close();
		//������رպ���ͼ�ر�session,�ͻᱨsession�Ѿ��رյ��쳣
		sf.close();
	}
}
