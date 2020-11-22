package main.java.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="costumer_test")
public class CostumerT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private AddressT addressT;

	public CostumerT(String firstName, String lastName, Integer age, AddressT addressT) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addressT = addressT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public AddressT getAddressT() {
		return addressT;
	}

	public void setAddressT(AddressT addressT) {
		this.addressT = addressT;
	}

	public CostumerT() {
		super();
	}
	
	
	
	
}
