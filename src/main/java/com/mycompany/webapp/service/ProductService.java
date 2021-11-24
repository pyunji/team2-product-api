package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dto.ProductList;

@Service
public class ProductService {
	@Resource private ProductDao productDao;
	
	public List<ProductList> getProductSampleList(String d1name){
		return productDao.getProductSampleList(d1name);
	}
	
	public Integer getTotalCount() {
		return productDao.countAll();
	}
	
	public List<String> getD1Names() {
		return productDao.getD1Names();
	}
}
