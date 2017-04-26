package com.retail.manager.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ShopAddress implements Serializable {

	private String number;
	private String postCode;
	private Shop shop;
	
	public ShopAddress() {
	}
	
	public ShopAddress(String number, String postCode) {
		this.number = number;
		this.postCode = postCode;
	}	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Id
    @OneToOne
    @JoinColumn(name = "shop_id")
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
