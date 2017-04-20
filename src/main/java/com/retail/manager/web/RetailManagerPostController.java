package com.retail.manager.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;
import com.retail.manager.service.GoogleMapsApiService;
import com.retail.manager.service.ShopRepository;

@RestController
public class RetailManagerPostController {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private GoogleMapsApiService mapsApiService;
	
	@SuppressWarnings("serial")
	@RequestMapping(value = "/shops", method = {RequestMethod.POST, RequestMethod.PUT } )
	public Map<String, Object> addShop(@RequestBody @Valid Shop shop, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			throw new ValidationException(Shop.VALIDATION_ERROR_MSG);
		}
		
		Shop savedShop = shopRepository.save(shop);
		
		// maps api - get lat lon update shop with lat lon
		
		GeoLocation location = mapsApiService.getGeoLocation(savedShop);
		
		savedShop.setLocation(location);
		
		shopRepository.save(savedShop);
		
		return new HashMap<String, Object>(){{
			put("shopName", savedShop.getShopName());
			put("shopAddress", savedShop.getShopAddress());
		}};
	}
	
	@RequestMapping(value = "/shops2", method = RequestMethod.GET)
	public String addShops(){
		
		//System.out.println(shopRepository.findByShopName("test").getShopName());
		
		//System.out.println(shopRepository.findByShopName("test").getShopAddress().getNumber());
		//System.out.println(shopRepository.findByShopName("test").getShopAddress().getPostCode());
		
		return null;//shopRepository.findByShopName("ABC").getShopName();
	}
}
