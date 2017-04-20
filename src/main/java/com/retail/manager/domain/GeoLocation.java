package com.retail.manager.domain;

public class GeoLocation {

	private Double latitude;
	private Double longitude;
	
	public GeoLocation() {
	}
	
	public GeoLocation(Double latitude, Double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}
