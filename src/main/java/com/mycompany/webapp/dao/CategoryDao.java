package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Category;
import com.mycompany.webapp.vo.CategoryVo;

@Mapper
public interface CategoryDao {
	public List<Category> selectCategoryD2Name(Category forD2Name);
	public List<Category> selectCategoryD3Name(Category forD3Name);
	public List<Category> selectCategoryD1Name(Category forD1Name);
	CategoryVo selectCategoryByPcolorId(String pcolorId);
}
