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
	private String zipCode;
	private String city;
	private String street;
	private int houseNumber;
	private double latitude;
	private double longitude;

//	@OneToOne ???
//	Milestone milestone

	public Address() {
		super();
	}

	public Address(String countryISOCode, String zipCode, String city, String street, int houseNumber, double latitude,
			double longitude) {
		super();
		this.countryISOCode = countryISOCode;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
