package com.mycompany.webapp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Stock;

@Mapper
public interface StockDao {
	Stock select(Stock stock);
	int update(Stock stock);
}
