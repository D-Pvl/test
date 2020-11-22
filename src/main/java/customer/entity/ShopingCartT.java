package main.java.customer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shoping_test")
public class ShopingCartT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(targetEntity = main.java.customer.entity.ProductT.class, cascade = CascadeType.ALL) 
	@JoinTable(name = "shopping_cart_productT", joinColumns = {
			@JoinColumn(name = "shopping_cart_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private List <ProductT> products;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id")
	private CostumerT costumerId;
	
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProductT> getProducts() {
		return products;
	}

	public void setProducts(List<ProductT> products) {
		this.products = products;
	}

	public CostumerT getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(CostumerT costumerId) {
		this.costumerId = costumerId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ShopingCartT(List<ProductT> products, CostumerT costumerId, Date createdOn) {
		super();
		this.products = products;
		this.costumerId = costumerId;
		this.createdOn = createdOn;
	}

	public ShopingCartT() {
		super();
	}

	
	
	
	
}
