package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductListView;
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
	public List<ProductList> getProductList(@RequestParam String d1name) {
		log.info("getProductList");
		List<ProductList> products = productService.getProductSampleList(d1name);
		log.info("products = {}", products);
		return products;
	}
	
	@GetMapping("/lists")
	public List<ProductListView> getProductListByCategory(@RequestParam String d1name,@RequestParam(required=false) String d2name,@RequestParam(required=false) String d3name) {
		log.info("getProductListByCategory");
		List<ProductListView> products = productService.getProductListByCategory(d1name,d2name,d3name);
		log.info("products = {}", products);
		return products;
	}
	
	@GetMapping("/d1names")
	public List<String> getD1Names() {
		List<String> d1names = productService.getD1Names();
		log.info("d1names = {}", d1names);
		return d1names;
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
