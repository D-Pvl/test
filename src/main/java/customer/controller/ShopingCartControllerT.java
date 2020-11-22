package main.java.customer.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.customer.entity.CategoryT;
import main.java.customer.entity.ProcuctResponceBonus;
import main.java.customer.entity.ProductT;
import main.java.customer.entity.ProductTResponce;
import main.java.customer.entity.ShopingCartT;
import main.java.customer.services.ShopingCartServiceT;

@Path("/buyT")
public class ShopingCartControllerT {

	@POST
	@Path("/createCart/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createShoppingCart(@PathParam("id") Integer id, ShopingCartT cart) {

		ShopingCartServiceT.createShopingCart(id, cart);

	}

	@DELETE
	@Path("/deleteProduct/{id}")
	public String deleteProduct(@PathParam("id") Integer Cartid, ProductT product) {

		ShopingCartServiceT.deleteProduct(Cartid, product);

		return "product was deleted from cart";
	}

	@GET
	@Path("/findCart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShopingCartT findCart(@PathParam("id") Integer id) {

		return ShopingCartServiceT.findCart(id);

	}

	@GET
	@Path("/getProductCategory/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductTResponce> allProductCategory(@PathParam("category") Integer id) {

		return ShopingCartServiceT.allProduct(id);

	}

	@GET
	@Path("/getProductByOrigin/{origin}")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<ProductT> allProductOrigin(@PathParam("origin") String origin) {

		return ShopingCartServiceT.allProductByOrigin(origin);

	}

	@GET
	@Path("/productBonus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProcuctResponceBonus> allProductBonus() {

		return ShopingCartServiceT.productBonus();

	}
	
	
	@POST
	@Path("/createCartById/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCartById(@PathParam("customerId")Integer customerId,List<Integer> productIds) {
		
		
		
	ShopingCartServiceT.createCartById(customerId,productIds);
	}
	
	
	
	
	
	
	

}
