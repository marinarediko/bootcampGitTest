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

import com.example.demo.models.Product;
import com.example.demo.services.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	IProductService prodService;

	@GetMapping("/showAllProducts")//url address -> localhost:8080/product/showAllProducts
	public String getShowAllProducts(Model model)
	{
		model.addAttribute("innerObject", prodService.selectAllProducts());	
		return "show-all-products-page";//show-all-product-page.html
		
	}
	
	@GetMapping("/saveTestingData")//localhost:8080/product/saveTestingData
	public String getsaveTestingData()
	{
		prodService.saveTestingData();
		return "hello-page";
	}
	
	
	@GetMapping("/showAllProducts/{id}")//url address -> localhost:8080/product/showOneProductParam?id=1
	public String getShowAllProductsId(@PathVariable(name = "id")int id, Model model)
	{
	try {
		model.addAttribute("innerObject",prodService.selectOneProductById(id));
		return("show-one-product-page");//show-one-product-page.html
		}
	catch(Exception e)
		{
			return "error";
		}
	}
	
	@GetMapping("/insertOneProduct")//url address -> localhost:8080/product/insertOneProduct
	public String getInsertOneProduct(Product product)//Its empty product
	{
		return"insert-one-product-page";//insert-one-product-page.html
	}
	
	@PostMapping("/insertOneProduct")//it will be called when SUBMIT button is pressed
	public String postInsertOneProduct(@Valid Product product, BindingResult result)//Its empty product
	{
		if(result.hasErrors()) {
			return "insert-one-product-page";
		}
		
		//prodService.insertNewProduct(product.getTitle(), product.getPrice());
		prodService.insertNewProductByObject(product);
		return"redirect:/product/showAllProducts";//insert-one-product-page.html
	}

	//update functionality
	@GetMapping("/updateProduct/{id}")//url address -> localhost:8080/product/updateProduct/{id}
	public String getUpdateProductById(@PathVariable(name = "id")int id, Model model,Product product)
	{
		try {
			Product productForUpdate = prodService.selectOneProductById(id);
			model.addAttribute("product",productForUpdate);
			return "update-one-product-page";//show-one-product-page.html
			}
		catch(Exception e)
			{
				return "error";
			}
	}
	
	@PostMapping("/updateProduct/{id}")//it will be called when SUBMIT button is pressed
	public String postUpdateProductById(@PathVariable(name = "id")int id,Product product)
	{
		System.out.println(product);
		System.out.println(id);
	
		prodService.updateProductObjectById(id, product);
		return"redirect:/product/showAllProducts";//insert-one-product-page.html
	}
	
	//delete
	@GetMapping("/deleteProduct/{id}")
	public String getDeleteProductById(@PathVariable(name = "id")int id, Model model)
	{
	try {
	
		model.addAttribute("innerObject",prodService.deleteProductById(id));
		return "redirect:/product/showAllProducts";//show-one-product-page.html
		}
	catch(Exception e)
		{
			return "error";
		}
	}
	
	
	
	@GetMapping("/gitTest")
	public String getGitTest()
	{
		return "hello-page";
		
	}
}
