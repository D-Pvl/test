package main.java.customer.services;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.Address;
import main.java.customer.entity.Manufactorer;
import main.java.customer.entity.Product;

public class ProductService {

	public static SessionFactory factory;

	public static Session init() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(main.java.customer.entity.Product.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Address.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Customer.class);
		cfg.addAnnotatedClass(main.java.customer.entity.Manufactorer.class);
		cfg.addAnnotatedClass(main.java.customer.entity.ShoppingCard.class);
		cfg.configure();

		factory = cfg.configure().buildSessionFactory();
		Session session = factory.openSession();
		return session;
	}

	public static void createProduct(Product product) {
		// TODO Auto-generated method stub

		Session session = init();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Address address = new Address(product.getManufactorer().getAddress().getCity(),
					product.getManufactorer().getAddress().getStreet(),
					product.getManufactorer().getAddress().getZipCode());
			session.save(address);

			Manufactorer m = checkManufacturer(product.getManufactorer().getName());
			
			
			if(m == null) {
				
				Manufactorer man = new Manufactorer(product.getManufactorer().getName(), address);
				session.save(man);
				product.setManufactorer(man);
			}else {
				product.setManufactorer(m);
			}
			
		

			session.save(product);
			
			
			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	private static Manufactorer checkManufacturer(String name) {
	
		
		Session session = init ();
		Transaction tx = null;
		
		try {
			
			session=factory.openSession();
			tx=session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.customer.entity.Manufactorer m WHERE m.name=:name");
			query.setParameter("name", name);
			
		Manufactorer m = (Manufactorer) query.getResultList().get(0);
		

		tx.commit();
		session.close();
		return m;
			
			
		} catch (Exception e) {
		tx.rollback();
		System.out.println(e);
		return null;
		}
	}

}
