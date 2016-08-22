package com.demo.model;

import java.io.Serializable;

public class EmployeeVO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Integer securitycode;
	private String firstName;
	private String lastName;
	private Integer phone;
	private String country;
	private String address;
	

	@Override
	public String toString() {
		return "EmployeeVO [Securitycode=" + securitycode + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + 
				", country=" + country + ", address=" + address + "]";
	}
	
	public Integer getSecuritycode() {
		return securitycode;
	}


	public void setSecuritycode(Integer securitycode) {
		this.securitycode = securitycode;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}



}