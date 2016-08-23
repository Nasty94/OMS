package com.demo.model;

import java.io.Serializable;

public class OrderVO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Integer ordernr;
	private Integer convprice;
	private String trandate;
	private Integer barcode;
	private String name;
	private Integer price;
	private String description;
	private String date;
	
	@Override
	public String toString() {
		return "ProductVO [ Order nr=" + ordernr + ", Converted price=" + convprice + ","
				+ ", Transaction date=" + trandate + ", BarCode=" + barcode + ", Name=" + name
				+ ", Price=" + price + ", Description=" + description +
				", Date=" + date +"]";
	}
	
	public Integer getOrdernr() {
		return ordernr;
	}
	public void setOrdernr(Integer ordernr) {
		this.ordernr = ordernr;
	}
	public Integer getConvprice() {
		return convprice;
	}
	public void setConvprice(Integer convprice) {
		this.convprice = convprice;
	}
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	public Integer getBarcode() {
		return barcode;
	}
	public void setBarcode(Integer barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
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
