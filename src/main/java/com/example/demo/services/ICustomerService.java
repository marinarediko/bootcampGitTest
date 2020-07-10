package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;

public interface ICustomerService {
	
		
		//register
		boolean register(String name,int age);
		
		//get all
		ArrayList<Product> getAllPurchasedProductsByCustId(int id) throws Exception;

		//buy
		boolean buyProducts(Collection<Product> purchasedProducts,int id) throws Exception;
		
		//select one
		Customer selectOneCustomerById(int id) throws Exception;
		
		ArrayList<Customer> selectAllCustomers();

		void insertNewCustomerByObject(@Valid Customer customer);


		
}
