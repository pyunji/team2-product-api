package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductDao;

import com.mycompany.webapp.dto.Color;

import com.mycompany.webapp.dto.CategoryDepth;

import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.ProductDetail;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductListView;
import com.mycompany.webapp.dto.ProductStock;
import com.mycompany.webapp.dto.Size;

@Service
public class ProductService<ProuctListView> {
	@Resource private ProductDao productDao;
	
	public List<ProductList> getProductSampleList(String d1name){
		return productDao.getProductSampleList(d1name);
	}
	
	public List<ProductList> getNewProductList(String d1name){
		return productDao.getNewProductList(d1name);
	}
	
	public List<ProductList> getBestProductList(String d1name){
		return productDao.getBestProductList(d1name);
	}
	
	public Integer getTotalCount() {
		return productDao.countAll();
	}
	
	public List<String> getD1Names() {
		return productDao.getD1Names();
	}

	public ProductDetail getProductDetail(String pcolorId) {
		return productDao.selectProductByPcolorId(pcolorId); 
	}
	public List<Color> getColors(String pcolorId){
		return productDao.selectColorsByPcolorId(pcolorId);
	}
	
	public List<Size> getSizes(String pcolorId){
		return productDao.selectSizesByPcolorId(pcolorId);
	}
	
	public List<Product> getWithItems(String pcolorId) {
		return productDao.selectWithItemsByPcolorId(pcolorId);
	}
	
	public List<ProductStock> getStocks(String pcolorId){
		return productDao.getStock(pcolorId);
	}

	public int getProductNum(CategoryDepth categoryDepthDto) {
		return productDao.countByCategory(categoryDepthDto);
	}
	
	public List<Map> getProductsByCategory(CategoryDepth categoryDepthDto){
		return productDao.getProductByCategory(categoryDepthDto);

	}
	//public List<ProductDetail> getProductDetail(String pcode, String productcolor){
	//	return productDao.getProductDetail(pcode, productcolor);
	//}
}
