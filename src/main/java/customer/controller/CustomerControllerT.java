package main.java.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import main.java.customer.entity.CostumerT;
import main.java.customer.services.CostumerServiceT;

@Path("/customerT")
public class CustomerControllerT {

	@POST
	@Path("/createT")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCustomer(CostumerT costumerT) {

		CostumerServiceT.createCostumerT(costumerT);

		return "Customer with name " + costumerT.getFirstName() + " was created";
	}

	
	@PUT
	@Path("/updateT/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateCustomer(@PathParam("id")Integer id,CostumerT costumerT) {
		
		
		CostumerServiceT.updateCostumer(id,costumerT);
		
		
		return "User with name "+ costumerT.getFirstName() + " was updated";
	}

	@DELETE
	@Path("/deleteT/{id}")
    public String deleteCustomer(@PathParam("id")Integer id) {
		
		CostumerServiceT.deleteUser(id);
		
		return "user with id " +id+ " was deleted";
	}
	
	
}
