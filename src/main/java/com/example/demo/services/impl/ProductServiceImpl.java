package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.enums.ProductType;
import com.example.demo.repos.ICustomerRepo;
import com.example.demo.repos.IProductRepo;
import com.example.demo.services.IProductService;

@Service

public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepo prodRepo;
	
	@Autowired
	ICustomerRepo custRepo;
	
	@Override
	public Product selectOneProductById(int id) throws Exception {
		if(id > 0 )
		{
			if(prodRepo.existsById(id))
			{
				return prodRepo.findById(id).get();		
			}
		}
		throw new Exception("Id is not correct and there is not product in the system");
	}

	@Override
	public ArrayList<Product> selectAllProducts() {
		return (ArrayList<Product>) prodRepo.findAll();
	}

	@Override
	public boolean insertNewProduct(String title, float price) {
	if(prodRepo.existsByTitleAndPrice(title, price))//write a statement by our own with correct parametrs
	{
		return false;
		
	}
	Product prod = new Product(title,price);
	prodRepo.save(prod);
	return true;		
	
	}

	@Override
	public boolean insertNewProductByObject(Product product) {
		
		if(prodRepo.existsByTitleAndPrice(product.getTitle(), product.getPrice()))//write a statement by our own with correct parametrs
		{
			return false;
			
		}
		Product prod = new Product(product.getTitle(), product.getPrice());
		prodRepo.save(prod);
		return true;		
		
		}

	@Override
	public boolean updateProductById(int id, String title, float price) {
	if(id>0) {
		if(prodRepo.existsById(id))//write a statement by our own with correct parametrs
		{
			Product productToUpdate = prodRepo.findById(id).get();
			productToUpdate.setTitle(title);
			productToUpdate.setPrice(price);
			prodRepo.save(productToUpdate);
		}
		return true;		
	}
	return false;
		}
		


	@Override
	public boolean updateProductObjectById(int id, Product product) {
		if(id > 0 )
		{
			if(prodRepo.existsById(id))//write a statement by our own with correct parametrs
			{
				Product productToUpdate = prodRepo.findById(id).get();
				productToUpdate.setTitle(product.getTitle());
				productToUpdate.setPrice(product.getPrice());
				prodRepo.save(productToUpdate);
			}
			return true;
			
			
		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		if(id > 0 )
		{
			if(prodRepo.existsById(id))//write a statement by our own with correct parametrs
			{	
				prodRepo.deleteById(id);
			}
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Product> selectProductsWherePriceLessThan(float price) {
		ArrayList<Product> productsWithPriceThanThreshold =prodRepo.findByPriceLessThan(price);
		if(productsWithPriceThanThreshold!=null)
		return productsWithPriceThanThreshold;
		
		//TODO throw Exception
		return new ArrayList<>();
	}

	@Override
	public void saveTestingData() {
		
		Customer cust1 = new Customer("Michael", 27);
		Customer cust2 = new Customer("Ashley", 16);
		custRepo.save(cust1);
		custRepo.save(cust2);
		
		prodRepo.save(new Product("Apple",0.3f,cust1));
		prodRepo.save(new Product("Mango",2.0f,cust2));
		prodRepo.save(new Product("Cake",8.50f));
		
	}
	
	

	
}
