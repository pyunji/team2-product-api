package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Color;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.ProductDetail;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductStock;
import com.mycompany.webapp.dto.Size;

@Mapper
public interface ProductDao {
	List<ProductList> getProductSampleList(String d1name);
	
	List<Product> getProductListByCategory(String d1name,String d2name, String d3name);
	
	List<String> getD1Names();
	
	//List<ProductDetail> getProductDetail(String pcode, String productcolor);
	int countAll();
	ProductDetail selectProductByPcolorId(String pcolorId);
	
	List<Color> selectColorsByPcolorId(String pcolorId);
	
	List<Size> selectSizesByPcolorId(String pcolorId);
	
	List<Product> selectWithItemsByPcolorId(String pcolorId);
	
	List<ProductStock> getStock(String pcolorId);
}
