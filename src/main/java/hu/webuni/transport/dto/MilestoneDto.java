package hu.webuni.transport.dto;

import java.time.LocalDateTime;

public class MilestoneDto {

	private Long milestoneId;

	private AddressDto addressDto;
	private LocalDateTime plannedTime;

	public MilestoneDto() {
		super();
	}

	public MilestoneDto(AddressDto addressDto, LocalDateTime plannedTime) {
		super();
		this.addressDto = addressDto;
		this.plannedTime = plannedTime;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

}
