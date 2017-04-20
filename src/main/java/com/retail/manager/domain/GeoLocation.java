package com.retail.manager.domain;

public class GeoLocation {

	private String latitude;
	private String longitude;
	
	public GeoLocation() {
	}
	
	public GeoLocation(String latitude, String longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
