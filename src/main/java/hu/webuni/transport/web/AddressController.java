package hu.webuni.transport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.dto.AddressDto;
import hu.webuni.transport.mapper.AddressMapper;
import hu.webuni.transport.model.Address;
import hu.webuni.transport.service.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	AddressMapper addressMapper;

	@GetMapping
	public List<AddressDto> getAll() {
		List<AddressDto> allAddresses = addressMapper.addressesToDtos(addressService.findAll());
		return allAddresses;
	}

	@GetMapping("/{id}")
	public AddressDto getById(@PathVariable Long id) {
		Address address = addressService.findById(id);
		return addressMapper.addressToDto(address);
	}

	// TODO JWT auth for DELETE address
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id) {
		addressService.delete(id);
		return ResponseEntity.ok(null);
	}

	// TODO JWT auth for POST address
	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		System.out.println(addressDto.toString());
		return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
	}

	// TODO JWT auth for PUT address
	@PutMapping("/{id}")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
		if (addressDto == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No data in the BODY.");
		if (addressDto.getAddressId() != null && addressDto.getAddressId() != id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"AddressId field exists but DIFFERS from URL 'id' pathvariable.");
		addressDto.setAddressId(id);
		Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));
		return ResponseEntity.ok(addressMapper.addressToDto(updatedAddress));
	}

	/*
	 * TODO: addreesController POST / DELETE / PUT w/ JWT
	 */

}
