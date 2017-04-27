package com.retail.manager.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;
import com.retail.manager.exception.InvalidShopDetailsException;
import com.retail.manager.service.GoogleMapsApiService;
import com.retail.manager.service.ShopRepository;

@RestController
public class RetailManagerPostController {
	
	private final Logger log = LoggerFactory.getLogger(RetailManagerPostController.class);
			
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private GoogleMapsApiService mapsApiService;
	
	@SuppressWarnings("serial")
	@RequestMapping(value = "/shops", method = {RequestMethod.POST, RequestMethod.PUT } )
	public Map<String, Object> addShop(@RequestBody @Valid Shop shop, BindingResult bindingResult) throws InvalidShopDetailsException{
		Map<String, Object> result = new LinkedHashMap<>();
		if(bindingResult.hasErrors()){
			log.error("Validation error: " + Shop.VALIDATION_ERROR_MSG);
			throw new ValidationException(Shop.VALIDATION_ERROR_MSG);
		}
		try {
			Optional<Shop> oldShop = shopRepository.findByShopName(shop.getShopName());
			Shop savedShop = shopRepository.save(shop);
			log.info("Saved shop details to inventory.");
			GeoLocation location = mapsApiService.getGeoLocation(savedShop);
			savedShop.setLocation(location);
			shopRepository.save(savedShop);
			log.info("Updating shop details to inventory.");
			String statusMessage = oldShop.isPresent() ? "Current Version replaced Previous version successfully" : "Successfully saved shop details"; 
			result.put("message", statusMessage);
			result.put("shopName", savedShop.getShopName());
			result.put("shopAddress", new HashMap<String, String>(){{
				put("number", savedShop.getShopAddress().getNumber());
				put("postCode", savedShop.getShopAddress().getPostCode());
			}});
		} catch (InvalidShopDetailsException e) {
			throw e;
		}
		return result;
	}
}
