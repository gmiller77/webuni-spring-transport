package hu.webuni.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.repository.AddressRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Address findById(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return address;
	}

	// TODO DECONFLICT delete Exception "500 - Internal Server Error" - foreign key
	@Transactional
	public void delete(Long id) {
		addressRepository.deleteById(id);
	}

	@Transactional
	public Address save(Address address) {
		if (address == null || address.getAddressId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return addressRepository.save(address);
	}

	@Transactional
	public Address update(Address address) {
		if (!addressRepository.existsById(address.getAddressId()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return addressRepository.save(address);
	}

	public Page<Address> findAddressByExample(Address example, Pageable page) {

		String countryISOCode = example.getCountryISOCode();
		String city = example.getCity();
		String zipCode = example.getZipCode();
		String street = example.getStreet();

		Specification<Address> spec = Specification.where(null);

		if (StringUtils.hasText(countryISOCode))
			spec = spec.and(AddressSpecifications.hasCountryISOCode(countryISOCode));
		if (StringUtils.hasText(city))
			spec = spec.and(AddressSpecifications.hasCity(city));
		if (StringUtils.hasText(zipCode))
			spec = spec.and(AddressSpecifications.hasZipCode(zipCode));
		if (StringUtils.hasText(street))
			spec = spec.and(AddressSpecifications.hasStreet(street));

		return addressRepository.findAll(spec, page);
	}

	public List<Address> findAddressesByExample(Address example) {

		String countryISOCode = example.getCountryISOCode();
		String city = example.getCity();
		String zipCode = example.getZipCode();
		String street = example.getStreet();

		Specification<Address> spec = Specification.where(null);

		if (StringUtils.hasText(countryISOCode))
			spec = spec.and(AddressSpecifications.hasCountryISOCode(countryISOCode));
		if (StringUtils.hasText(city))
			spec = spec.and(AddressSpecifications.hasCity(city));
		if (StringUtils.hasText(zipCode))
			spec = spec.and(AddressSpecifications.hasZipCode(zipCode));
		if (StringUtils.hasText(street))
			spec = spec.and(AddressSpecifications.hasStreet(street));

		return addressRepository.findAll(spec);
	}

}
