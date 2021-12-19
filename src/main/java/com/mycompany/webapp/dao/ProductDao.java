package com.mycompany.webapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.CategoryDepth;
import com.mycompany.webapp.dto.Color;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.ProductDetail;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductStock;
import com.mycompany.webapp.dto.Size;
import com.mycompany.webapp.dto.SmryProduct;
import com.mycompany.webapp.dto.Stock;

@Mapper
public interface ProductDao {
	List<ProductList> getProductSampleList(String d1name);
	
	List<ProductList> getNewProductList(String d1name);
	
	List<ProductList> getBestProductList(String d1name);
	
	List<String> getD1Names();
	
	int countAll();

	ProductDetail selectProductByPcolorId(String pcolorId);
	
	List<Color> selectColorsByPcolorId(String pcolorId);
	
	List<Size> selectSizesByPcolorId(String pcolorId);
	
	List<Product> selectWithItemsByPcolorId(String pcolorId);
	
	List<ProductStock> getStock(String pcolorId);

	List<SmryProduct> selectSmryWithItemsByPcolorId(String pcolorId);
	int countByCategory(CategoryDepth categoryDepthDto);
	
	List<Map> getProductByCategory(CategoryDepth categoryDepthDto);
	
	int reduceStock(Stock stockToReduce);
}
