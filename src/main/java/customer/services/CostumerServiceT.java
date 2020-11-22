package main.java.customer.services;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.AddressT;
import main.java.customer.entity.CostumerT;

public class CostumerServiceT {

	public static SessionFactory factory;

	public static Session init() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(main.java.customer.entity.ProductT.class);
		cfg.addAnnotatedClass(main.java.customer.entity.AddressT.class);
		cfg.addAnnotatedClass(main.java.customer.entity.CostumerT.class);
		cfg.addAnnotatedClass(main.java.customer.entity.ManufacturerT.class);
		cfg.addAnnotatedClass(main.java.customer.entity.ShopingCartT.class);
		cfg.addAnnotatedClass(main.java.customer.entity.CategoryT.class);
		cfg.configure();

		factory = cfg.configure().buildSessionFactory();
		Session session = factory.openSession();
		return session;

	}

	public static void createCostumerT(CostumerT costumerT) {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			AddressT address = new AddressT(costumerT.getAddressT().getCity(), costumerT.getAddressT().getStreet(),
					costumerT.getAddressT().getZipCode());
			session.save(address);
			costumerT.setAddressT(address);

			session.save(costumerT);

			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	public static void updateCostumer(Integer id, CostumerT costumerT) {
		Session session = init();
		Transaction tx = null;
		
		try {
			
		tx=session.beginTransaction();
		
		Query query = session.createQuery("SELECT addressT FROM main.java.customer.entity.CostumerT c WHERE c.id=:id");
		query.setParameter("id", id);
		
		AddressT a = (AddressT) query.getResultList().get(0);
		
	a.setCity(costumerT.getAddressT().getCity());
		a.setStreet(costumerT.getAddressT().getStreet());
		a.setZipCode(costumerT.getAddressT().getZipCode());
		session.update(a);
		
		CostumerT costumerOld = session.get(CostumerT.class, id); 
	
		costumerOld.setFirstName(costumerT.getFirstName());
		costumerOld.setLastName(costumerT.getLastName());
		costumerOld.setAge(costumerT.getId());
		costumerOld.setAddressT(a);
			
		session.update(costumerOld);

		tx.commit();
		session.close();
		
		
		} catch (Exception e) {
		tx.rollback();
			System.out.println(e);
		}
		
	}

	public static void deleteUser(Integer id) {
		Session session = init();
		Transaction tx = null;
		
		try {
			tx=session.beginTransaction();
			
			
			Query query = session.createQuery("SELECT addressT FROM main.java.customer.entity.CostumerT c WHERE c.id=:id");
			query.setParameter("id", id);
			
			AddressT a = (AddressT) query.getResultList().get(0);
			
			session.delete(a);
			
			CostumerT costumerOld = session.get(CostumerT.class, id); 
			session.delete(costumerOld);
			
			tx.commit();
			session.close();
		} catch (Exception e) {
		tx.rollback();
		System.out.println(e);
		}
		
	}

}
