package com.example.demo.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name="CustomerTable")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Cid")
	private int cId;
	
	@Column(name="Name")
	@Size(min = 3, max = 15)
	@Pattern(regexp="[a-zA-Z\s]+$", message = "Only letters and spaces allowed")
	private String name;
	
	@Column(name="Age")
	@Min(0)
	@Max(100)
	private int age;
	
	
	
	//LINKING TO PRODUCT
	@OneToMany(mappedBy="customer")// ar kuru mainigo sasaista(mappedBy)
	private Collection<Product> allCustomerProducts;
	
	
	
	
	public Customer()
	{
		
	}
	
	public ArrayList<Product> getAllCustomerProducts() {
		return (ArrayList<Product>) allCustomerProducts;
	}

	public void setAllCustomerProducts(Collection<Product> allCustomerProducts) {
		this.allCustomerProducts = allCustomerProducts;
	}
	
	public Customer(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getcId() {
		return cId;
	}

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", name=" + name + ", age=" + age + "]";
	}

}
