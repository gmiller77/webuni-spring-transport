package hu.webuni.transport.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class DelayDto {
	
	@PositiveOrZero (message = "Incorrect value: 'milestoneId' must be zero a positive number.")
	private Long milestoneId;
	
	@PositiveOrZero (message = "Incorrect value: 'delayMinutes' must be zero a positive number.")
	private Integer delayMinutes;

	public DelayDto() {
		super();
	}

	public DelayDto(
			@PositiveOrZero(message = "Incorrect value: 'milestoneId' must be zero a positive number.") Long milestoneId,
			@PositiveOrZero(message = "Incorrect value: 'delayMinutes' must be zero a positive number.") Integer delayMinutes) {
		super();
		this.milestoneId = milestoneId;
		this.delayMinutes = delayMinutes;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Integer getDelayMinutes() {
		return delayMinutes;
	}

	public void setDelayMinutes(Integer delayMinutes) {
		this.delayMinutes = delayMinutes;
	}
	
}
