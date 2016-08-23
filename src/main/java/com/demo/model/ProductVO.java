package com.demo.model;

import java.io.Serializable;

public class ProductVO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Integer BarCode;
	private String Name;
	private int Price;
	private String Description;
	private String Date;

	@Override
	public String toString() {
		return "EmployeeVO [BarCode=" + BarCode + ", Name=" + Name
				+ ", Price=" + Price + ", Description=" + Description +
				", Date=" + Date +"]";
	}

	public Integer getBarCode() {
		return BarCode;
	}

	public void setBarCode(Integer barCode) {
		BarCode = barCode;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}


}