package main.java.customer.entity;

public class ProductTResponce {

	private String name;
	
	private String price;
	
	private CategoryT category;

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

	public CategoryT getCategory() {
		return category;
	}

	public void setCategory(CategoryT category) {
		this.category = category;
	}

	public ProductTResponce(String name, String price, CategoryT category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public ProductTResponce() {
		super();
	}
	
	
	
	
}
