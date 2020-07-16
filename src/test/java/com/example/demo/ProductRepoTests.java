package com.example.demo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Product;
import com.example.demo.repos.IProductRepo;
@DataJpaTest
public class ProductRepoTests {

	@Autowired
	IProductRepo prodRepo;
	
	@Test
	public void testInsertInRepo() {
		prodRepo.save(new Product("Milk",1.7f));
		assertTrue(prodRepo.existsByTitleAndPrice("Milk", 1.7f));
	}
	
	@Test
	public void testUpdateInRepo() {
		prodRepo.save(new Product("Bread",2.5f));
		Product productFromDB= prodRepo.findByTitleAndPrice("Bread", 2.5f);
		productFromDB.setPrice(0.70f);
		prodRepo.save(productFromDB);
		
		assertTrue(prodRepo.existsByTitleAndPrice("Bread", 0.70f));
		Product productFromDBAfterUpdate = prodRepo.findByTitleAndPrice("Bread", 0.70f);
		assertEquals(0.70f, productFromDBAfterUpdate.getPrice());
		
		
	}
	
	@Test
	public void testDeleteInRepo() {
		prodRepo.save(new Product("Sausage",1.5f));
		
		Product productFromDB =prodRepo.findByTitleAndPrice("Sausage",1.5f);
		
		prodRepo.delete(productFromDB);
		assertFalse(prodRepo.existsByTitleAndPrice("Sausage",1.5f));
		
	}
	
	
	
	
	
	
	
}
