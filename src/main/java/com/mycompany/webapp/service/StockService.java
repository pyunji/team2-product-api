package com.mycompany.webapp.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.dao.StockDao;
import com.mycompany.webapp.dto.Stock;
import com.mycompany.webapp.exception.OutOfStockException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockService {
	
	@Resource private StockDao stockDao;
	
	@Transactional
	public void updateStock(List<Stock> stocks, String operator) {
		for(Stock stock : stocks) {
			log.info("실행");
			log.info("stock = " + stock);
			Stock oldStock = stockDao.select(stock);
			log.info("oldStock: " +  oldStock);
			int oldQuantity = oldStock.getQuantity();
			log.info("oldQuantity: " + oldQuantity);
			int newQuantity = 0;
			if (operator == "-") {
				newQuantity = oldQuantity - stock.getQuantity();
				if (newQuantity < 0) {
					throw new OutOfStockException("outOfStock");
				}
			} else if (operator == "+") {
				newQuantity = oldQuantity + stock.getQuantity();
			}
			log.info("newQuantity: " + newQuantity);
			Stock newStock = new Stock(stock.getPstockid(), newQuantity);
			stockDao.update(newStock);
		}
	}
}
