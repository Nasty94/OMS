package com.demo.model;

import java.io.Serializable;

public class CountryVO implements Serializable 
{
	private static final long serialVersionUID = 1L; 
	private String name;
	private String currency;
	
	@Override
	public String toString() {
		return "CountryVO [Name=" + name + ", Currency=" + currency
				+  "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
