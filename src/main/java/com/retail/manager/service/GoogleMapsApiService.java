package com.retail.manager.service;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.retail.manager.domain.GeoLocation;
import com.retail.manager.domain.Shop;

@Service
public class GoogleMapsApiService {

	private final Logger log = LoggerFactory.getLogger(GoogleMapsApiService.class);

	@Value("${google.maps.apiKey}")
	private String googleMapsApiKey;

	GeoApiContext context = null;

	@Autowired
	public void setContext() {
		context = new GeoApiContext().setApiKey(googleMapsApiKey);
	}

	public GeoLocation getGeoLocation(Shop shop) {
		GeoLocation result = null;
		try {
			GeocodingResult[] results = GeocodingApi.newRequest(context).address(shop.getShopFullAddress()).await();

			Validate.notEmpty(results);
			Validate.notNull(results[0].geometry);
			Validate.notNull(results[0].geometry.location);
			Validate.notNull(results[0].geometry.location.lat);
			Validate.notNull(results[0].geometry.location.lng);

			result = new GeoLocation(results[0].geometry.location.lat, results[0].geometry.location.lng);

		} catch (ApiException | InterruptedException | IOException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	public long getDistance(GeoLocation origin, GeoLocation destination){
		long result = Long.MAX_VALUE;
		try {
			DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
			        .origins(new LatLng(origin.getLatitude(), origin.getLongitude()))
			        .destinations(new LatLng(destination.getLatitude(), destination.getLongitude()))
			        .await();
			
			Validate.notNull(matrix);
			Validate.notEmpty(matrix.rows);
			Validate.notEmpty(matrix.rows[0].elements);
			Validate.notNull(matrix.rows[0].elements[0].distance);
			result = matrix.rows[0].elements[0].distance.inMeters;
		} catch (ApiException | InterruptedException | IOException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
}
