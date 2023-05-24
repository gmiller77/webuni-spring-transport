package hu.webuni.transport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

	// TODO fix JWT auth for DELETE address
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADDRESS_MGR')")
	public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id) {
		addressService.delete(id);
		return ResponseEntity.ok(null);
	}

	// TODO fix JWT auth for POST address
	@PostMapping
	@PreAuthorize("hasAuthority('ADDRESS_MGR')")
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		System.out.println(addressDto.toString());
		return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
	}

	// TODO fix JWT auth for PUT address
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADDRESS_MGR')")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
		if (addressDto == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data in the BODY.");
		if (addressDto.getAddressId() != null && addressDto.getAddressId() != id)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"AddressId field exists but DIFFERS from URL 'id' pathvariable.");
		addressDto.setAddressId(id);
		Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));
		return ResponseEntity.ok(addressMapper.addressToDto(updatedAddress));
	}

	/*
	 * TODO: JWT function to POST / DELETE / PUT methods
	 */
	
//	TODO check if DTO is empty	
	@PostMapping("/search")
	public ResponseEntity<List<AddressDto>> getByParams(@RequestBody AddressDto addressDto, Pageable page) {

		if (addressDto == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search BODY is NULL");

		boolean hasValidData = hasValidData(addressDto.getCountryISOCode(), addressDto.getCity(),
				addressDto.getZipCode(), addressDto.getStreet());

		if (!hasValidData)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is BODY, but NO valuable data for searching.");

		Page<Address> addressListPaged = addressService.findAddressByExample(addressMapper.dtoToAddress(addressDto), page);
		List<AddressDto> listAddress = addressMapper.addressesToDtos(addressListPaged.getContent());

		HttpHeaders headers = new HttpHeaders();
	    headers.add("X-Total-Count", Long.toString(addressListPaged.getTotalElements()));
		
		return new ResponseEntity<>(listAddress, headers, HttpStatus.OK);
	}

	public static boolean hasValidData(String str1, String str2, String str3, String str4) {
		return isNotBlank(str1) || isNotBlank(str2) || isNotBlank(str3) || isNotBlank(str4);
	}

	private static boolean isNotBlank(String str) {
		return str != null && !str.trim().isEmpty();
	}
}