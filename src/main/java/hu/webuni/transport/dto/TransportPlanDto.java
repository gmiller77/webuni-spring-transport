package hu.webuni.transport.dto;

import java.util.List;

public class TransportPlanDto {

	private Long transportPlanId;

	private long income;
	private List<SectionDto> routeSectionDtos;

	public TransportPlanDto() {
		super();
	}

	public TransportPlanDto(Long transportPlanId, long income, List<SectionDto> routeSectionDtos) {
		super();
		this.transportPlanId = transportPlanId;
		this.income = income;
		this.routeSectionDtos = routeSectionDtos;
	}

	public Long getTransportPlanId() {
		return transportPlanId;
	}

	public void setTransportPlanId(Long transportPlanId) {
		this.transportPlanId = transportPlanId;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public List<SectionDto> getRouteSectionDtos() {
		return routeSectionDtos;
	}

	public void setRouteSectionDtos(List<SectionDto> routeSectionDtos) {
		this.routeSectionDtos = routeSectionDtos;
	}

}
