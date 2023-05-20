package hu.webuni.transport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	public Address update(Address address) {
		if(!addressRepository.existsById(address.getAddressId()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return addressRepository.save(address);
	}

}
