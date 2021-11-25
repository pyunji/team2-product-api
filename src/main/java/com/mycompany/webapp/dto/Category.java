package com.mycompany.webapp.dto;

public class Category {
	private String d1Name;
	private String d2Name;
	private String d3Name;
	
	public Category() {
		
	}
	
	public Category(String d1Name, String d2Name, String d3Name) {
		this.d1Name = d1Name;
		this.d2Name = d2Name;
		this.d3Name = d3Name;
	}

	public String getd1Name() {
		return d1Name;
	}

	public void setd1Name(String d1Name) {
		this.d1Name = d1Name;
	}

	public String getd2Name() {
		return d2Name;
	}

	public void setd2Name(String d2Name) {
		this.d2Name = d2Name;
	}

	public String getd3Name() {
		return d3Name;
	}

	public void setd3Name(String d3Name) {
		this.d3Name = d3Name;
	}
	

}
