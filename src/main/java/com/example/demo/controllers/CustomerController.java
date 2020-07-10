package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.services.ICustomerService;
import com.example.demo.services.IProductService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ICustomerService custService;
	
	@Autowired
	IProductService prodService;

	@GetMapping("/showMyProducts/{id}")//url address -> localhost:8080/customer/showMyProducts/1
	public String getShowMyProductsByCustId(@PathVariable(name="id")int id,Model model)
	{
		try {
			model.addAttribute("innerObjectProd",custService.getAllPurchasedProductsByCustId(id));
			model.addAttribute("innerObjectCustName", custService.selectOneCustomerById(id).getName());
			return "show-all-customer-products-page";//show-all-customer-products-page.html
			}
		catch(Exception e)
			{
				return "error";
			}
	}
	
	//showAllCustomers
	
	@GetMapping("/showAllCustomers")//url address -> localhost:8080/customer/showAllCustomers
	public String getShowAllCustomers(Model model)
	{try {
		model.addAttribute("innerObject", custService.selectAllCustomers());
		return "show-all-customers-page";//show-all-customers-page.html
		
		}
	catch(Exception e)
		{
			return "error";
		}
		
		
	}
	
	//insertNewCustomer or register
	
	@GetMapping("/register")//localhost:8080/customer/register
	public String getRegister(Customer customer)//Its empty customer
	{
		return"register-page";//register-page.html
	}
	
	@PostMapping("/register")
	public String postRegister(@Valid Customer customer, BindingResult result)//Its empty product
	{
		System.out.println(customer);
		if(result.hasErrors()) {
			return "register-page";
		}

		custService.register(customer.getName(), customer.getAge());;
		return"redirect:/customer/showAllCustomers";//show-all-customers-page.html
	}
	
	//buy products
	
	@GetMapping("/buy/{id}")//localhost:8080/customer/buy/1
	public String getBuyByCustId(@PathVariable(name="id")int id,Model model,Customer customer)
	{
	try {
			
			model.addAttribute("innerObjectCustName", custService.selectOneCustomerById(id).getName());
			model.addAttribute("allCustomerProducts", prodService.selectAllProducts());
			return "customer-buy";//customer-buy.html
			
		} 
	catch (Exception e) {
		e.printStackTrace();
			return "error";
		}
	}
	
	
	@PostMapping("/buy/{id}")
	public String postBuyByCustId(@PathVariable(name="id")int id, Customer customer)
	{
	for(Product prod:customer.getAllCustomerProducts())
		System.out.println(prod.getTitle()+" " + prod.getPrice());
	
	System.out.println(id);
	try {
		custService.buyProducts(customer.getAllCustomerProducts(), id);
		return "redirect:/customer/showMyProducts/"+id;
	} 
	catch (Exception e) {
	System.out.println(e);
		return "error";
	}	
	}
	
	
}
