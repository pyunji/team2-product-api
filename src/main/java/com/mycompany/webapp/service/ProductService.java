package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dto.Color;
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
	
	public List<ProductListView> getProductListByCategory(String d1name,String d2name,String d3name){
		
		List<Product> list = productDao.getProductListByCategory(d1name,d2name,d3name);
		TreeMap<String,ProductListView> tm = new TreeMap<>();
		
		for(Product p : list) {
			if(tm.containsKey(p.getPcommonid())) {
				ProductListView plist = tm.get(p.getPcommonid());
				plist.getImgs().add(p.getImg1());
				plist.getColor_imgs().add(p.getColor_img());
				
				tm.put(p.getPcommonid(), plist);
			}else {
				ProductListView plist = new ProductListView(p.getPname(),p.getBname(),p.getPprice(),
						new ArrayList<String>(),new ArrayList<String>(),p.getD1name(),p.getD2name(),p.getD3name());
				plist.getImgs().add(p.getImg1());
				plist.getColor_imgs().add(p.getColor_img());
				
				tm.put(p.getPcommonid(), plist);
			}
		}
		
		List<ProductListView> result = new ArrayList<>();
		for(String key : tm.keySet()) {
			result.add(tm.get(key));
		}
		
		return result;
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
	//public List<ProductDetail> getProductDetail(String pcode, String productcolor){
	//	return productDao.getProductDetail(pcode, productcolor);
	//}
}
