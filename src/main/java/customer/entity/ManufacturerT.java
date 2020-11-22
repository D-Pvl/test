package main.java.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="manufacturer_test")
public class ManufacturerT {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private AddressT addressT;


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


	public AddressT getAddressT() {
		return addressT;
	}


	public void setAddressT(AddressT addressT) {
		this.addressT = addressT;
	}


	public ManufacturerT(String name, AddressT addressT) {
		super();
		this.name = name;
		this.addressT = addressT;
	}


	public ManufacturerT() {
		super();
	}
	
	
	
	
	
}
