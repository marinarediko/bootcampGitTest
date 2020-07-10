package com.example.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {

	boolean existsByTitleAndPrice(String title, float price);
	
	ArrayList<Product> findByPriceLessThan(float priceThreshold);

	Product findByTitleAndPrice(String title,float price);
	
	ArrayList<Product> findByCustomer(Customer customer);


	
	
}
