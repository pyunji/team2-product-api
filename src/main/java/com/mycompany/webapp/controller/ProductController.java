package com.mycompany.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

	@Autowired ProductService productService;
	
	@RequestMapping("")
	public String content() {
		return "common/product";
	}
	
	
	@GetMapping("/list")
	public List<ProductList> getProductList() {
		List<ProductList> products = productService.getProductSampleList();
		return products;
	}
	
	@PostMapping("/list")
	public List<ProductList> showProductList(@RequestBody List<ProductList> products) {
		log.info("products = {}", products);
		return products;
	}
	
	@RequestMapping("/add")
	public String addProduct() {
		return "product/add";
	}
//	@RequestMapping("/create")
//	public String create() {
//		
//	}
}
