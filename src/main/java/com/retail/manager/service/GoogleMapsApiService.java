package com.retail.manager.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;

@Service
public class GoogleMapsApiService {

	private final Logger log = LoggerFactory.getLogger(GoogleMapsApiService.class);
	
	@Value("${google.maps.uri}")
	private String googleMapsUri;
	
	@Value("${google.maps.apiKey}")
	private String googleMapsApiKey;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public GeoLocation getGeoLocation(Shop shop){
		GeoLocation result = null;
		//ResponseEntity<String> response = restTemplate.getForEntity(googleMapsUri, String.class, "1600+Amphitheatre+Parkway,+Mountain+View,+CA", googleMapsApiKey);
		ResponseEntity<String> response = restTemplate.getForEntity(googleMapsUri, String.class, shop.getShopFullAddress(), googleMapsApiKey);
		if (HttpStatus.OK == response.getStatusCode()) {
			result = getLatLonFromJson(result, response);
	    } else {
	        log.error(response.getBody()); 
	    }
		return result;
	}

	private GeoLocation getLatLonFromJson(GeoLocation result, ResponseEntity<String> response) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode data = mapper.readTree(response.getBody().toString());	
			for(JsonNode res: data.path("results")){
				JsonNode geo = res.path("geometry");
				JsonNode lat = geo.path("location").path("lat");
				JsonNode lon = geo.path("location").path("lng");
				result = new GeoLocation(lat.textValue(), lon.textValue());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return result;
	}
}
