package com.mycompany.webapp.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Category;
import com.mycompany.webapp.dto.CategoryDepth;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductListView;
import com.mycompany.webapp.service.CategoryService;
import com.mycompany.webapp.service.ProductService;
import com.mycompany.webapp.vo.Pager;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
	
	public static JSONObject categoryListJson = new JSONObject();
	
	public ProductController() {
		categoryListJson.put("result","fail");
	}
	
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
	
	@GetMapping("/list/{depth1}/{depth2}/{depth3}")
	public Map<String,Object> searchByCategory(@PathVariable String depth1, @PathVariable String depth2, @PathVariable String depth3,@RequestParam(defaultValue = "1") int pageNo) {
		log.info("실행");
		log.info(depth1+" "+depth2+" "+depth3+" "+pageNo);
		
		// 입력받은 대분류,중분류,소분류 정보를 parameter로 넘기기 위한 dto객체
		CategoryDepth categoryDepthDto = new CategoryDepth();
		categoryDepthDto.setDepth1(depth1);
		categoryDepthDto.setDepth2(depth2);
		categoryDepthDto.setDepth3(depth3);

		// 페이징 처리를 위해 조건에 맞는 상품전체 개수 조회
		int totalProduct = productService.getProductNum(categoryDepthDto);
		
		// Pager객체에 parameter값으로 (표시할 상품수, 표시할 페이지 그룹수, 전체 상품개수, 페이지번호)를 넣는다.
		Pager pager = new Pager(12,5,totalProduct,pageNo);
		categoryDepthDto.setPager(pager);
		List<Map> products = productService.getProductsByCategory(categoryDepthDto); 
		//List<Prd> list = boardService.getBoards(pager);
		Map<String,Object> map = new HashMap<>();
		map.put("products", products);
		map.put("pager", pager);
		map.put("totalProduct", totalProduct);
		log.info(map.toString());
		return map;
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
	
	@Resource
	CategoryService categoryService;
	
	@GetMapping(value="getCategoryList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getCategoryList(HttpSession session) {
		if(!categoryListJson.get("result").equals("success")){
			
			Category tmp = new Category();
			
			List<Category> categoryD1Name = categoryService.getCategoryD1Name(tmp);
			
			JSONArray FirstArray = new JSONArray();
			
			for(Category n : categoryD1Name) {
				n.setd1Name(tmp.getd1Name());
				
			
			List<Category> categoryD2Name = categoryService.getCategoryD2Name(n);
			
			JSONArray jsonArray = new JSONArray();
			
			for(Category m : categoryD2Name) {
				m.setd1Name(n.getd1Name());
				
				JSONObject tmpObject = new JSONObject();
				
				List<Category> categoryD3Name = categoryService.getCategoryD3Name(m);
				
				JSONArray tmpArray = new JSONArray();
				int idx = 0;
				
				for(Category s : categoryD3Name) {
					tmpArray.put(idx, s.getd3Name());
					idx += 1;
				}
				
				tmpObject.put(m.getd2Name(), tmpArray);
				jsonArray.put(tmpObject);
				
			}
			categoryListJson.put("result", "success");
			
			categoryListJson.put("category", jsonArray);
		
			
		}
		}
		String json = categoryListJson.toString();
		
		return json;		
	}

//	@RequestMapping("/create")
//	public String create() {
//		
//	}
}
