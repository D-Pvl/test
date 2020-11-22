package main.java.customer.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.customer.entity.ProductT;
import main.java.customer.services.ProductServiceT;

@Path("/productT")
public class ProductControlerT {

	
	@POST
	@Path("/createProductT")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createProduct(ProductT product) {
		
		ProductServiceT.createProduct(product);
		
		return "product with name "+ product.getName()+" was created";
	}
	
	@PUT
	@Path("/updateProductT")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateProduct(@PathParam("id")Integer id,ProductT product) {
		
		
		
		ProductServiceT.updateProduct(id,product);
		
		
		return "updated succes";
		
	}
	
	@DELETE
	@Path("/deleteProductT/{id}")
	public String deleteProduct(@PathParam("id")Integer id) {
		
		ProductServiceT.deleteProduct(id);
		
		return "product was deleted";
		
		
	}
	
	
	
	@GET
	@Path("/getAllProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List <ProductT> gettAllProduct () {
	
		return ProductServiceT.getallproduct();
		
		
		
	}
	
	
	
}
