package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.CategoryDepth;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.ProductList;

@Mapper
public interface ProductDao {
	List<ProductList> getProductSampleList(String d1name);
	
	List<Product> getProductListByCategory(String d1name,String d2name, String d3name);
	
	List<String> getD1Names();
	
	//List<ProductDetail> getProductDetail(String pcode, String productcolor);
	int countAll();
	
	int countByCategory(CategoryDepth categoryDepthDto);
	
	List<Map> getProductByCategory(CategoryDepth categoryDepthDto);
}
