package hu.webuni.transport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long addressId;

	private String countryISOCode;
	private String cityName;
	private String streetName;
	private String zipCode;
	private int houseNumber;
	private double latitude;
	private double longitude;

//	@OneToOne ???
//	Milestone milestone
	
	public Address() {
		super();
	}

	public Address(String countryISOCode, String cityName, String streetName, String zipCode, int houseNumber,
			double latitude, double longitude) {
		super();
		this.countryISOCode = countryISOCode;
		this.cityName = cityName;
		this.streetName = streetName;
		this.zipCode = zipCode;
		this.houseNumber = houseNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountryISOCode() {
		return countryISOCode;
	}

	public void setCountryISOCode(String countryISOCode) {
		this.countryISOCode = countryISOCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
