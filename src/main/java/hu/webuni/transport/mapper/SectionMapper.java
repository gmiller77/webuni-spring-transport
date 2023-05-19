package hu.webuni.transport.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapper {

	/*
	@Mapping(source = "fromMilestone", target = "fromMilestoneDto")
	@Mapping(source = "toMilestone", target = "toMilestoneDto")
	SectionDto sectionToDto(Section section);

	@InheritInverseConfiguration
	Section dtoToSection(SectionDto sectionDto);
	*/
}
