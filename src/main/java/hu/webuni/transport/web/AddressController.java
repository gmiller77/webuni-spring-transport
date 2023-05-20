package hu.webuni.transport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	//TODO JWT auth for DELETE address
	@DeleteMapping("/{id}")
	public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id) {
			addressService.delete(id);
		return ResponseEntity.ok(null);
	}
	
	//TODO JWT auth for POST address
	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		System.out.println(addressDto.toString());
		return addressMapper.addressToDto(addressService.save(addressMapper.DtoToAddress(addressDto)));
	}

	/*
	 * TODO: addreesController POST / DELETE / PUT w/ JWT
	 */

}
