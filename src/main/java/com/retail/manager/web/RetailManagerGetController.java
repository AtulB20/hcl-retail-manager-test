package com.retail.manager.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;
import com.retail.manager.exception.InvalidCoordinatesException;
import com.retail.manager.exception.NoNearbyShopsFoundException;
import com.retail.manager.service.GoogleMapsApiService;
import com.retail.manager.service.ShopRepository;

@RestController
public class RetailManagerGetController {

	private final Logger log = LoggerFactory.getLogger(RetailManagerGetController.class);
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private GoogleMapsApiService mapsApiService;
	
	@SuppressWarnings("serial")
	@RequestMapping(value = "/shops/lat/{latitude}/lng/{longitude}/nearest", method = {RequestMethod.GET } )
	public Map<String, Object> getShop(@PathVariable String latitude, @PathVariable String longitude) throws InvalidCoordinatesException, NoNearbyShopsFoundException {
		Map<String, Object> result = new LinkedHashMap<>();
		try {
			List<Shop> allShops = shopRepository.findAll();
			if(allShops.size() == 0) throw new NoNearbyShopsFoundException(); 
			log.info("Found " + allShops.size() + " in inventory");
			GeoLocation customerLocation = new GeoLocation(new Double(latitude), new Double(longitude));
			log.info("Validated customer geo location.");
			Map<Shop, Long> distances = allShops.stream()
					.filter(f -> f != null)
					.collect(Collectors.toMap(shop->shop, shop -> {return mapsApiService.getDistance(customerLocation, shop.getLocation());}));
			log.info("Calculating the distance between nearest shop.");
			Entry<Shop, Long> nearestShop = Collections.min(distances.entrySet(), Comparator.comparingDouble(Entry::getValue));
			if (nearestShop.getValue() == Long.MAX_VALUE) throw new NoNearbyShopsFoundException();
			log.info("Nearest shop is at distance " + nearestShop.getValue().toString() + " meters.");
			result.put("shopName", nearestShop.getKey().getShopName());
			result.put("shopAddress", new HashMap<String, String>(){{
				put("number", nearestShop.getKey().getShopAddress().getNumber());
				put("postCode", nearestShop.getKey().getShopAddress().getPostCode());
			}});
			result.put("location",  new HashMap<String, String>(){{
				put("latitude", nearestShop.getKey().getLocation().getLatitude().toString());
				put("longitude", nearestShop.getKey().getLocation().getLongitude().toString());
			}});
			result.put("distance", nearestShop.getValue().toString() + " meters" );
		} catch (NoNearbyShopsFoundException e) {
			log.error("No nearby shop found for input latitude: " + latitude + " longitude: " + longitude);
			throw e;
		} catch (InvalidCoordinatesException e) {
			log.error("Input coordinates are invalid: " + latitude + " " + longitude);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}
	
}
