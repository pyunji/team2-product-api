package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Category;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.service.CategoryService;
import com.mycompany.webapp.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
	
	public static JSONObject categoryListJson = new JSONObject();
	
	/*	public ProductController() {
			categoryListJson.put("result","fail");
		}*/
	
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
	
	@GetMapping(value = "/getCategoryList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getCategoryList(HttpSession session) {

		// 대분류 값 cLarge만 가지고 있는 상태
		// 처음 접속(세션에 아무런 값이 없을 때)했을 때
		//if (!categoryListJson.get("result").equals("success")) {
			
			// tmp는 아무것도 가지고 있지 않음
			Category tmp = new Category();
			
			JSONArray FirstArray = new JSONArray();
			//categoryLarge에는 대분류들이 저장되어 있음.
			List<Category> categoryLarge = categoryService.getCategoryD1Name(tmp);
			JSONArray tmp2Array = new JSONArray();
			for(Category n : categoryLarge) {
			
				tmp.setd1Name(n.getd1Name());
				JSONObject tmp2Object = new JSONObject();
				// tmp를 이용해서 중분류 리스트를 가져온다.
				List<Category> categoryMedium = categoryService.getCategoryD2Name(tmp);
				// 중분류를 담을 JSONArray 객체
				JSONArray jsonArray = new JSONArray();
	
				for (Category m : categoryMedium) {
					// m은 대분류와 중분류 값을 가지고 있는 상태
					m.setd1Name(tmp.getd1Name());
	
					JSONObject tmpObject = new JSONObject();
	
					// m을 이용해서 소분류 리스트를 가져온다.
					List<Category> categorySmall = categoryService.getCategoryD3Name(m);
					// 소분류를 담을 JSONArray 객체
					JSONArray tmpArray = new JSONArray();
					int idx = 0;
	
					for (Category s : categorySmall) {
						tmpArray.put(idx, s.getd3Name());
						idx += 1;
				}
				//각 중분류의 이름을 Key 소분류가 담긴Array를 Value로 tmpObject에 담는다.
				tmpObject.put(m.getd2Name(), tmpArray);
				//담은 내용을 jsonArray에 담아 배열 형태로 저장한다.
				jsonArray.put(tmpObject);
				log.info(tmpObject.toString());
				}
				tmp2Object.put(n.getd1Name(), jsonArray);
				tmp2Array.put(tmp2Object);
			}
			//categoryListJson.put("result", "success");
			// DB에서 값을 받아온 뒤 세션에 넣어준다.
			// session.setAttribute(cLarge, jsonArray);
			categoryListJson.put("catgories", tmp2Array);
			
		//}
//		jsonObject.put(cLarge, session.getAttribute(cLarge));
		String json = categoryListJson.toString();

		return json;
	}
	
	//@GetMapping("/productDetail")
	//public String productDetail(String pcode, String productcolor, Model model) {
		
	//}

//	@RequestMapping("/create")
//	public String create() {
//		
//	}
}
