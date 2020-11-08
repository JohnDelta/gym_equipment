package com.john_deligiannis.gym_equipment.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.john_deligiannis.gym_equipment.config.HibernateUtil;
import com.john_deligiannis.gym_equipment.entities.Offers;
import com.john_deligiannis.gym_equipment.entities.Orders;
import com.john_deligiannis.gym_equipment.entities.OrdersItems;
import com.john_deligiannis.gym_equipment.entities.Products;
import com.john_deligiannis.gym_equipment.entities.Users;
import com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer;

public class Queries {

	public static List<Offers> loadOffers() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Offers> query = session.createQuery("SELECT c FROM offers c", Offers.class);
	   
		return query.getResultList();
	}
	
	public static Offers loadOffer(long offersId) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
		Offers offer = null;
		
		try {
			TypedQuery<Offers> query = session.createQuery(
		    		"SELECT c FROM offers c WHERE c.offersId=:offersId",
		    		Offers.class
		    );
		    query.setParameter("offersId", offersId);
		    
		    offer = query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
	    	    
		return offer;
	}
	
	public static List<Products> loadProducts() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Products> query = session.createQuery("SELECT c FROM products c", Products.class);
	    
		return query.getResultList();
	}
		
	public static Products loadProduct(Long productsId) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Products> query = session.createQuery("SELECT c FROM products c WHERE c.productsId = :productsId", Products.class);
	    query.setParameter("productsId", productsId);
	    
		return query.getSingleResult();
	}
	
	public static List<ProductsAndTheirOffer> loadProductsAndTheirOffer() {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<ProductsAndTheirOffer> query = session.createQuery(
	    		"SELECT NEW com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer("
	    		+ " p.productsId AS productsId, p.title AS title,"
	    		+ " p.description AS description, p.price AS price, o.price AS offerPrice,"
	    		+ " p.photo1 AS photo1, p.photo2 AS photo2, p.quantity AS quantity)"
	    		+ " FROM products p LEFT JOIN offers o ON o.products = p", 
	    		ProductsAndTheirOffer.class
	    );

		return query.getResultList();
		
	}
	
	public static ProductsAndTheirOffer loadProductAndItsOffer(long productsId) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<ProductsAndTheirOffer> query = session.createQuery(
	    		"SELECT NEW com.john_deligiannis.gym_equipment.entities.dto.ProductsAndTheirOffer("
	    		+ " p.productsId AS productsId, p.title AS title,"
	    		+ " p.description AS description, p.price AS price, o.price AS offerPrice,"
	    		+ " p.photo1 AS photo1, p.photo2 AS photo2, p.quantity AS quantity)"
	    		+ " FROM products p LEFT JOIN offers o ON o.products = p"
	    		+ " WHERE p.productsId=:productsId", 
	    		ProductsAndTheirOffer.class
	    );
	    query.setParameter("productsId", productsId);

		return query.getSingleResult();
	}
	
	public static Users loadUserByUsername(String username) {
		
		Users user = null;
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Users> query = session.createQuery(
	    		"SELECT u FROM users u WHERE u.username = :username", 
	    		Users.class
	    );
	    query.setParameter("username", username);
		user = query.getSingleResult();
		if(user != null) {
			user.setPassword("");
		}
		
		return user;
	}
	
	public static Users loadUserRawByUsername(String username) {
		
		Users user = null;
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Users> query = session.createQuery(
	    		"SELECT u FROM users u WHERE u.username = :username", 
	    		Users.class
	    );
	    query.setParameter("username", username);
		user = query.getSingleResult();
		
		return user;
	}
	
	public static List<Orders> loadUsersOrders(Users users) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<Orders> query = session.createQuery(
	    		"SELECT o FROM orders o WHERE o.users = :users", 
	    		Orders.class
	    );
	    query.setParameter("users", users);
		
		return query.getResultList();
	}
	
	public static List<OrdersItems> loadUsersOrdersItems(Orders orders) {
		
		EntityManager session = HibernateUtil.getSessionFactory().createEntityManager();
	    TypedQuery<OrdersItems> query = session.createQuery(
	    		"SELECT o FROM ordersItems o WHERE o.orders = :orders", 
	    		OrdersItems.class
	    );
	    query.setParameter("orders", orders);
		
		return query.getResultList();
	}
	
}






