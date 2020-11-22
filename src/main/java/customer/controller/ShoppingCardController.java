package main.java.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.customer.entity.ShoppingCard;
import main.java.customer.services.ShopingCardService;

@Path("/buy")
public class ShoppingCardController {

	@GET
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public String init() {
		
		ShopingCardService.initService();
		
		return "Init";
	}

	@POST
	@Path("/cart/{customerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createShoppingCart(@PathParam("customerId")Integer customerId,ShoppingCard cart) {
		
		
		ShopingCardService.createCart(customerId,cart);
		
		
		
		
	}
	
	/*
	 * 
	 * Query za selektiranje po datum 
	 * 
	 * SELECT id, createdon, customer_id
	FROM public.shoppingcart WHERE shoppingcart.createdon BETWEEN '2020-09-29 22:05:08.99' AND '2020-10-01 21:05:08.99';
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
}
