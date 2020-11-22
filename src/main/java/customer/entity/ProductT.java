package main.java.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_test")
public class ProductT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer stock; //magacin //added
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public ManufacturerT getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(ManufacturerT manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public CategoryT getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(CategoryT categoryId) {
		this.categoryId = categoryId;
	}

	private String price;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private ManufacturerT manufacturerId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private CategoryT categoryId;
	
	private String Origin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ManufacturerT getManufacturerT() {
		return manufacturerId;
	}

	public void setManufacturerT(ManufacturerT manufacturerT) {
		this.manufacturerId = manufacturerT;
	}

	public CategoryT getCategoryT() {
		return categoryId;
	}

	public void setCategoryT(CategoryT categoryT) {
		this.categoryId = categoryT;
	}

	public String getOrigin() {
		return Origin;
	}

	public void setOrigin(String origin) {
		Origin = origin;
	}

	public ProductT(String name, String price, ManufacturerT manufacturerT, CategoryT categoryT, String origin,Integer stock) {
		super();
		this.name = name;
		this.price = price;
		this.manufacturerId = manufacturerT;
		this.categoryId = categoryT;
		this.Origin = origin;
		this.stock=stock;
		
	}

	public ProductT() {
		super();
	}
	
	
	
	
	
}
