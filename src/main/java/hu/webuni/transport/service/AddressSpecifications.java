package hu.webuni.transport.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.model.Address_;

public class AddressSpecifications {
	public static Specification<Address> hasCountryISOCode(String countryISOCode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.countryISOCode), countryISOCode);
	}

	public static Specification<Address> hasCity(String city) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.city)), city.toUpperCase() + "%");
	}

	public static Specification<Address> hasZipCode(String zipCode) {
		return (root, cq, cb) -> cb.equal(root.get(Address_.zipCode), zipCode);
	}

	public static Specification<Address> hasStreet(String street) {
		return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.street)), street.toUpperCase() + "%");
	}

}
