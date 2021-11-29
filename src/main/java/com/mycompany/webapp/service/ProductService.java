package com.mycompany.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductDao;
import com.mycompany.webapp.dto.CategoryDepth;
import com.mycompany.webapp.dto.Product;
import com.mycompany.webapp.dto.ProductList;
import com.mycompany.webapp.dto.ProductListView;

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
