package hu.webuni.transport.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.transport.dto.TransportPlanDto;
import hu.webuni.transport.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportPlanMapper {

//	@Mapping(source = "routeSections", target = "routeSectionDtos")
	@Mapping(target = "routeSectionDtos", ignore = true)
	TransportPlanDto transportPlanToDto(TransportPlan transportPlan);

	@InheritInverseConfiguration
//	@Mapping(source = "routeSectionDtos", target = "routeSections")
	@Mapping(target = "routeSections", ignore = true)
	TransportPlan dtoToTransportPlan(TransportPlanDto transportPlanDto);

}
