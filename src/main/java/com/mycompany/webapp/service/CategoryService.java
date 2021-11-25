package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.CategoryDao;
import com.mycompany.webapp.dto.Category;

@Service
public class CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Resource
	private CategoryDao categoryDao;
	
	public List<Category> getCategoryD1Name(Category forD1Name){
		return categoryDao.selectCategoryD1Name(forD1Name);
	}
	
	public List<Category> getCategoryD2Name(Category forD2Name){
		return categoryDao.selectCategoryD2Name(forD2Name);
	}
	
	public List<Category> getCategoryD3Name(Category forD3Name){
		return categoryDao.selectCategoryD3Name(forD3Name);
	}
	
}
