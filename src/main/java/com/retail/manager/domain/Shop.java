package com.retail.manager.domain;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author Atul
 *
 */
@Document(collection = "retailShops")
public class Shop {

	public static final String VALIDATION_ERROR_MSG = "Validation failed in saving shop details. Shop name or address are incorrect";
	public static final String NUMBER = "number";
	public static final String POST_CODE = "postCode";
	
	@Id
	@NotNull
	private String shopName;

	@NotNull
	private Map<String, String> shopAddress;

	private GeoLocation location;
	
	public Shop() {
	}

	public Shop(String shopName, Map<String, String> shopAddress) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
	}
	
	public Shop(String shopName, Map<String, String> shopAddress, GeoLocation location) {
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

	public Map<String, String> getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(Map<String, String> shopAddress) {
		this.shopAddress = shopAddress;
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}
	
	public String getShopFullAddress(){
		StringBuilder sb = new StringBuilder();  
		sb.append(shopAddress.get(POST_CODE));
		sb.append("+");
		sb.append(shopName);
		sb.append("+");
		sb.append(shopAddress.get(NUMBER));
		return sb.toString();  
	}
}
