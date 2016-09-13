package com.demo.model;

import java.io.Serializable;

public class ProductVO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Integer barcode;
	private String name;
	private Integer price;
	private String description;
	private String date;

	@Override
	public String toString() {
		return "ProductVO [BarCode=" + barcode + ", Name=" + name
				+ ", Price=" + price + ", Description=" + description +
				", Date=" + date +"]";
	}

	public Integer getBarcode() {
		return barcode;
	}

	public void setBarcode(Integer Barcode) {
		this.barcode = Barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}