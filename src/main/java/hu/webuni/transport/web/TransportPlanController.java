package hu.webuni.transport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.transport.dto.DelayDto;
import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.mapper.TransportPlanMapper;
import hu.webuni.transport.service.TransportPlanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	private static final String HAS_AUTHORITY_ROLE_TRANSPORT_MANAGER = "hasAuthority('ROLE_TransportManager')";

	@Autowired
	TransportPlanService transportPlanService;

	@Autowired
	TransportPlanMapper transportPlanMapper;

	@GetMapping
	public List<TransportPlanDto> getAll() {
		List<TransportPlanDto> allTransportPlanDtos = transportPlanMapper
				.transportPlansToDtos(transportPlanService.findAll());
		return allTransportPlanDtos;
	}

	// HOTO add JWT auth
	@PostMapping("/{id}/delay")
	@PreAuthorize(HAS_AUTHORITY_ROLE_TRANSPORT_MANAGER)
//	@PreAuthorize("hasRole('TransportManager')")
	public void registerDelay(@PathVariable long id, @Valid @RequestBody DelayDto delayDto) {

		transportPlanService.registerDelay(id, delayDto);
	}
}
