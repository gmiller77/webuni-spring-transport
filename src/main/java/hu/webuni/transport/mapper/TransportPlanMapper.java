package hu.webuni.transport.mapper;

import org.mapstruct.Mapper;

import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

	TransportPlanDto transportPlanToDto(TransportPlan transportPlan);
	TransportPlan dtoToTransportPlan(TransportPlanDto transportPlan);
	
}
