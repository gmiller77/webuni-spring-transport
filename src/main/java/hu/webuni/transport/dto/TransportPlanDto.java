package hu.webuni.transport.dto;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class TransportPlanDto {

	private long id;
	
	private long income;
	private List<SectionDto> routeSections;
	
	public TransportPlanDto() {
		super();
	}
	public TransportPlanDto(Long income, List<SectionDto> routeSections) {
		super();
		this.income = income;
		this.routeSections = routeSections;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public List<SectionDto> getRouteSections() {
		return routeSections;
	}
	public void setRouteSections(List<SectionDto> routeSections) {
		this.routeSections = routeSections;
	}
	
	
}
