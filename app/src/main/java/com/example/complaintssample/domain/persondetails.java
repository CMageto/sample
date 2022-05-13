package com.example.complaintssample.domain;

public class persondetails {
	public persondetails (){

	}
	String phone,code;

	public persondetails(String phone, String code) {
		this.phone = phone;
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
