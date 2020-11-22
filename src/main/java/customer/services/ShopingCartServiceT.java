package main.java.customer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.customer.entity.CategoryT;
import main.java.customer.entity.CostumerT;
import main.java.customer.entity.ProcuctResponceBonus;
import main.java.customer.entity.ProductT;
import main.java.customer.entity.ProductTResponce;
import main.java.customer.entity.ShopingCartT;

public class ShopingCartServiceT {

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

	public static void createShopingCart(Integer id, ShopingCartT cart) {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			CostumerT ct = session.get(CostumerT.class, id);

			if (ct == null) {

				System.out.println("costumer does not exist");

			}

			Date d = new Date(System.currentTimeMillis());

			cart.setCreatedOn(d);
			cart.setCostumerId(ct);

			List<ProductT> prod = getProduct(cart.getProducts());

			cart.setProducts(prod);
			session.save(cart);
			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	private static List<ProductT> getProduct(List<ProductT> products) {
		Session session = init();
		Transaction tx = null;

		List<ProductT> res = new ArrayList<>();
		try {
			tx = session.beginTransaction();

			for (ProductT productT : products) {

				Query q = session.createQuery("FROM main.java.customer.entity.ProductT p WHERE p.name=:name");
				q.setParameter("name", productT.getName());

				ProductT p = (ProductT) q.getResultList().get(0);

				res.add(p);
			}

			tx.commit();
			session.close();
			return res;
		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
			return null;
		}

	}

	public static void deleteProduct(Integer Cartid, ProductT product) {

		Session session = init();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Query shopingCart = session.createQuery("FROM main.java.customer.entity.ShopingCartT s WHERE s.id=:id");
			shopingCart.setParameter("id", Cartid);
			ShopingCartT sct = (ShopingCartT) shopingCart.getResultList().get(0);

			Query productQuery = session.createQuery("FROM main.java.customer.entity.ProductT p WHERE p.id=:id");
			productQuery.setParameter("id", product.getId());

			ProductT p = (ProductT) productQuery.getResultList().get(0);

			if (sct.getProducts().contains(p)) {
				sct.getProducts().remove(p);
				session.update(sct);
			} else {
				System.out.println("product is not in shoping cart");
			}

			tx.commit();
			session.close();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

	}

	public static ShopingCartT findCart(Integer id) {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			Query query = session.createQuery("FROM ShopingCartT s WHERE s.id=:id");
			query.setParameter("id", id);

			ShopingCartT sct1 = (ShopingCartT) query.getResultList().get(0);
			// ShopingCartT sct = session.get(ShopingCartT.class, id);

			tx.commit();
			session.close();
			return sct1;
		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
		}

		return null;

	}

	public static List<ProductTResponce> allProduct(Integer id) {

		List<ProductTResponce> result = new ArrayList<ProductTResponce>();
		Session session = init();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			Query queryCat = session.createQuery("FROM CategoryT c WHERE c.id=:id");
			queryCat.setParameter("id", id);

			CategoryT ct = (CategoryT) queryCat.getResultList().get(0);

			Query query = session.createQuery("FROM ProductT p WHERE p.categoryId=:id");

			query.setParameter("id", ct);

			List<ProductT> products = query.getResultList();

			for (ProductT product : products) {

				ProductTResponce responce = new ProductTResponce();
				responce.setName(product.getName());
				responce.setPrice(product.getPrice());
				responce.setCategory(product.getCategoryT());

				result.add(responce);
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

	public static List<ProductT> allProductByOrigin(String origin) {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			Query query = session.createQuery("FROM ProductT p WHERE p.Origin=:origin");
			query.setParameter("origin", origin);

			List<ProductT> result = query.getResultList();

			tx.commit();
			session.close();
			return result;

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
			return null;
		}

	}

	public static List<ProcuctResponceBonus> productBonus() {

		Session session = init();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();

			String origin = "Macedonia";
			Query query = session.createQuery("FROM ProductT p WHERE p.Origin=:origin");
			query.setParameter("origin", origin);
			List<ProductT> products = query.getResultList();
			List<ProcuctResponceBonus> result = new ArrayList<>();

			for (ProductT product : products) {

				ProcuctResponceBonus response = new ProcuctResponceBonus();
				String priceAsString = product.getPrice();
				String lastPriceString = priceAsString.substring(0, priceAsString.length() - 1);
				int price = Integer.parseInt(lastPriceString);
				double test = price * 0.18;
				double priceWithDiscount = (price - test);
				int priceInteger = (int) priceWithDiscount;

				response.setProductName(product.getName());
				response.setPrice(price);
				response.setNewPrice(priceInteger);

				result.add(response);

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

	public static Response createCartById(Integer customerId, List<Integer> productIds) {   //ne rabote
		Session session = init();
		Transaction tx = null;

		ShopingCartT sct = new ShopingCartT();

		try {

			tx = session.beginTransaction();

			List<ProductT> allProducts = new ArrayList<>();

			CostumerT ct = session.get(CostumerT.class, customerId);

			if (ct == null) {
				return Response.noContent().build();
			}

			sct.setCreatedOn(new Date(System.currentTimeMillis()));
			sct.setCostumerId(ct);
			HashMap<Integer, Integer> map = new HashMap<>();

			Integer i = 0;

			for (Integer productId : productIds) {

				if (map.containsKey(productId)) {
					map.put(productId, i++);
				} else {

					map.put(productId, i++);

				}
			}

			for (Integer productId : map.keySet()) {

				ProductT p = session.get(ProductT.class, productId);
				p.setStock(p.getStock() - map.values().size());

				if (p.getStock() < 0) {
					System.out.println("Out of stock");

				}

				session.update(p);

			}

			sct.setProducts(allProducts);
			session.save(sct);
			tx.commit();
			session.close();
			return Response.ok(sct, MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			tx.rollback();
			System.out.println(e);
			return Response.notModified().build();
		}

	}

}
