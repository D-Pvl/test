package main.java.customer.entity;

public class ProcuctResponceBonus {


private String productName;

private Integer price;

private Integer newPrice;

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public Integer getPrice() {
	return price;
}

public void setPrice(Integer price) {
	this.price = price;
}

public Integer getNewPrice() {
	return newPrice;
}

public void setNewPrice(Integer newPrice) {
	this.newPrice = newPrice;
}

public ProcuctResponceBonus(String productName, Integer price, Integer newPrice) {
	super();
	this.productName = productName;
	this.price = price;
	this.newPrice = newPrice;
}

public ProcuctResponceBonus() {
	super();
}



}
