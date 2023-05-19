package hu.webuni.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Address save(Address address) {
		if (address.getAddressId() != null)
			return null;
		return addressRepository.save(address);
	}

	public Address findById(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return address;
	}

}
