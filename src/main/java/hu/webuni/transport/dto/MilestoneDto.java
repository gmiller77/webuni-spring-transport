package hu.webuni.transport.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class MilestoneDto {
	
	private long id;
	
	private AddressDto address;
	private LocalDateTime plannedTime;
	
	public MilestoneDto() {
		super();
	}
	public MilestoneDto(AddressDto address, LocalDateTime plannedTime) {
		super();
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}
	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}
	
	
	
}
