package hu.webuni.transport.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class AddressDto {

	private long id;
	
	@Size(min = 2, max = 2)
	private String countryISOCode;
	@NotBlank
	private String cityName;
	@NotBlank
	private String streetName;
	@NotBlank
	private String zipCode;
	@Positive
	private int houseNumber;
	
	private double latitude;
	private double longitude;
	
	
	public AddressDto() {
		super();
	}

	public AddressDto(String countryISOCode, String cityName, String streetName, String zipCode, int houseNumber,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
