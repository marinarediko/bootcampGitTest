package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.repos.ICustomerRepo;
import com.example.demo.repos.IProductRepo;

@SpringBootApplication
public class MiniWebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniWebStoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(ICustomerRepo custRepo,IProductRepo prodRepo) {
		return (args) ->
		{
			Customer cust1 = new Customer("Michael", 27);
			Customer cust2 = new Customer("Ashley", 16);
			custRepo.save(cust1);
			custRepo.save(cust2);
			
			prodRepo.save(new Product("Apple",0.3f,cust1));
			prodRepo.save(new Product("Mango",2.0f,cust2));
			prodRepo.save(new Product("Cake",8.50f));
		};
	}

}
