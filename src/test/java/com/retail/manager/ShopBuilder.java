package com.retail.manager;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;
import com.retail.manager.domain.ShopAddress;

/**
 * @author Atul
 *
 */
public class ShopBuilder {

	private String shopName;
	private ShopAddress shopAddress;
	private GeoLocation geoLocation;
	
	public ShopBuilder shopName(String shopName) {
		this.shopName = shopName;
		return this;
	}

	public ShopBuilder shopAddress(String number, String postCode) {
		this.shopAddress = new ShopAddress(number, postCode);
		return this; 
	}
	
	public ShopBuilder geoLocation(Double lat, Double lng) {
		this.geoLocation = new GeoLocation(lat, lng);
		return this;
	}

	public Shop build(){
		return new Shop(shopName, shopAddress, geoLocation);
	}
	
	@SuppressWarnings("serial")
	public String buildAsJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		Map<String, Object> obj = new HashMap<String, Object>(){{
			if(shopName != null) put("shopName", shopName);
			if(shopAddress != null) put("shopAddress", shopAddress);
		}};
		
		return mapper.writeValueAsString(obj);
	}
	
}
