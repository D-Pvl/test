package main.java.customer.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.AddressT;
import main.java.customer.entity.CategoryT;
import main.java.customer.entity.ManufacturerT;
import main.java.customer.entity.ProductT;

public class ProductServiceT {

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

	public static void createProduct(ProductT product) {

		Session session = init();
		Transaction tx = null;

		try {

			tx=session.beginTransaction();
			
			CategoryT c = new CategoryT(product.getCategoryT().getProductCategory());
			session.save(c);

			
			AddressT at = new AddressT(product.getManufacturerT().getAddressT().getCity(), product.getManufacturerT().getAddressT().getStreet(), product.getManufacturerT().getAddressT().getZipCode());
			session.save(at);
			ManufacturerT m = new ManufacturerT(product.getManufacturerT().getName(),at);
			session.save(m);

			product.setCategoryT(c);
			product.setManufacturerT(m);

			session.save(product);
			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	public static void updateProduct(Integer id, ProductT product) {
		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Query query = session
					.createQuery("SELECT manufacturerId FROM main.java.customer.entity.ManufacturerT m WHERE m.id=:id");
			query.setParameter("id", id);

			ManufacturerT mt = (ManufacturerT) query.getResultList().get(0);

			mt.setName(product.getName());
			mt.setAddressT(product.getManufacturerT().getAddressT());
			session.update(mt);

			Query queryCategory = session
					.createQuery("SELECT categoryId FROM main.java.customer.entity.ManufacturerT m WHERE m.id=:id");
			queryCategory.setParameter("id", id);

			CategoryT ct = (CategoryT) queryCategory.getResultList().get(0);
			ct.setProductCategory(product.getCategoryT().getProductCategory());
			session.update(ct);

			ProductT p = session.get(ProductT.class, id);

			p.setCategoryT(ct);
			p.setManufacturerT(mt);
			p.setName(product.getName());
			p.setOrigin(product.getOrigin());
			p.setPrice(product.getPrice());

			session.update(p);

			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	public static void deleteProduct(Integer id) {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Query query = session
					.createQuery("SELECT manufacturerId FROM main.java.customer.entity.ManufacturerT m WHERE m.id=:id");
			query.setParameter("id", id);

			ManufacturerT mt = (ManufacturerT) query.getResultList().get(0);

			session.delete(mt);

			Query queryCategory = session
					.createQuery("SELECT categoryId FROM main.java.customer.entity.ManufacturerT m WHERE m.id=:id");
			queryCategory.setParameter("id", id);

			CategoryT ct = (CategoryT) queryCategory.getResultList().get(0);

			session.delete(ct);

			ProductT p = session.get(ProductT.class, id);

			session.delete(p);

			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	public static List<ProductT> getallproduct() { // not finish

		Session session = init();
		Transaction tx = null;

		List<ProductT> result = new ArrayList<>();

		try {
			tx = session.beginTransaction();

			
			
			List<ProductT> products = session.createQuery("FROM main.java.customer.entity.ProductT").list();

			
		for (ProductT productT : products) {

				result.add(productT);
			}           

			tx.commit();
			session.close();
			return result;

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
			return null;
		}

	}

}
