package com.example.demo;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import com.example.demo.models.Product;

public class ProductModelTests {
	
	Product p1=new Product("Milk",1.7f);
	Product p2=new Product("123",-2f);
	Product p3=new Product(null,0 );
	@Test
	public void testProductParametPositive() {
		assertEquals("Milk", p1.getTitle());
		assertEquals(1.7f, p1.getPrice());
	}
	
	@Test
	public void testProductTitlePattern() {
		assertTrue(p1.getTitle().matches("[a-zA-Z\s]+$"));
		assertFalse(p2.getTitle().matches("[a-zA-Z\s]+$"));
	}

	@Test
	public void testProductParametrsNegative() {
		assertEquals("", p2.getTitle());
		assertEquals(0, p2.getPrice());
	}
	
	@Test
	public void testProductParametrsNotNull() {
		assertEquals("", p3.getTitle());
	}
	

	@Test
	public void testProductParametrsNotEmpty() {
		//assertEquals("", p3.getTitle());
	}
	
	@Test
	public void testProductValidating() {
		ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
		
		Validator val=factory.getValidator();
		Set<ConstraintViolation<Product>> allValidationsNotWorkForObjP2 = val.validate(p2);
		assertEquals(2,allValidationsNotWorkForObjP2.size() );//bus 2 validacijas kludas
		
		
		Set<ConstraintViolation<Product>> allValidationsNotWorkForObjP1 = val.validate(p1);
		assertEquals(0,allValidationsNotWorkForObjP1.size() );//nebus validacijas kludas
		
		}
	
	
	
}
