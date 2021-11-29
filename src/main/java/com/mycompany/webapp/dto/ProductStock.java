package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class ProductStock {
	private String pstockid;
	private String pcolorid;
	private String scode;
	private int stock;
}