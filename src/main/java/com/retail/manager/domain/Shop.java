package com.retail.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class Shop implements Serializable {

	public static final String VALIDATION_ERROR_MSG = "Validation failed in saving shop details. Shop name or address are incorrect";
	public static final String NUMBER = "number";
	public static final String POST_CODE = "postCode";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String shopName;

	@NotNull
	private ShopAddress shopAddress;

	@Column(nullable = true)
	private GeoLocation location;
	
	public Shop() {
	}

	public Shop(String shopName, ShopAddress shopAddress) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}
	
	public Shop(String shopName, ShopAddress shopAddress, GeoLocation location) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.location = location;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "shop")
	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "shop")
	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}
	
	public String getShopFullAddress(){
		StringBuilder sb = new StringBuilder();  
		sb.append(shopAddress.getPostCode());
		sb.append("+");
		sb.append(shopName);
		sb.append("+");
		sb.append(shopAddress.getNumber());
		return sb.toString();  
	}
}
