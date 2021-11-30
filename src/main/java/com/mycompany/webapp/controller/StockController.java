package com.mycompany.webapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Stock;
import com.mycompany.webapp.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/stock")
public class StockController {
	private static String mid;
	
	@Resource StockService stockService;
	
	@RequestMapping("/reduce")
	public void reduceStock(/*HttpServletRequest request, */@RequestBody List<Stock> stocks) {
		log.info("실행");
		log.info("stocks = {}", stocks);
		stockService.updateStock(stocks, "-");
	}
}
