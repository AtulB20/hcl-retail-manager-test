package com.retail.manager.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;
import com.retail.manager.service.ShopRepository;
import com.retail.manager.utils.DistanceCalculator;

@RestController
public class RetailManagerGetController {

	public static final String UNIT = "K";
	
	@Autowired
	private ShopRepository shopRepository;
	
	@SuppressWarnings("serial")
	@RequestMapping(value = "/shops/lat/{latitude}/lng/{longitude}/nearest", method = {RequestMethod.GET } )
	public Map<String, Object> getShop(@PathVariable String latitude, @PathVariable String longitude){
		
		GeoLocation customerLocation = new GeoLocation(new Double(latitude), new Double(longitude));
		
		List<Shop> allShops = shopRepository.findAll();
		
		Map<Shop, Double> distances = allShops.stream()
				.filter(f -> f != null)
				.collect(Collectors.toMap(shop->shop, shop -> {return distance(customerLocation, shop.getLocation());}));
		
		Entry<Shop, Double> nearestShop = Collections.min(distances.entrySet(), Comparator.comparingDouble(Entry::getValue));
		
		return new HashMap<String, Object>(){{
			put("shopName", nearestShop.getKey().getShopName());
			put("shopAddress", nearestShop.getKey().getShopAddress());
			put("location", nearestShop.getKey().getLocation());
		}};
	}
	
	private double distance(GeoLocation source, GeoLocation destination) {
		return DistanceCalculator.distance(destination.getLatitude(), destination.getLongitude(), source.getLatitude(), source.getLongitude(), UNIT);
	}
}
