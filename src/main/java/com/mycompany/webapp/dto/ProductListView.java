package com.mycompany.webapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListView{
	String pname;
	String bname;
	int price;
	List<String> imgs;
	List<String> color_imgs;
	String d1name;
	String d2name;
	String d3name;
}
