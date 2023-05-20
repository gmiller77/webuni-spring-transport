package hu.webuni.transport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AddressDto {

	private Long addressId;

//	TODO addressDto validation messages
	@NotBlank(message = "Country ISO code can not be null or empty.")
	@Size(min = 2, max = 2, message = "country ISO code must be 2-character long")
	private String countryISOCode;
	@NotBlank(message = "City name must contain value.")
	private String city;
	@NotBlank(message = "ZIP code contain value.")
	private String zipCode;
	@NotBlank(message = "Street name must contain value.")
	private String street;
	@Positive(message = "House number must be a positive number.")
	private int houseNumber;

	private double latitude;
	private double longitude;

	public AddressDto() {
		super();
	}

	public AddressDto(String countryISOCode, String zipCode, String city, String street, int houseNumber,
			double latitude, double longitude) {
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
