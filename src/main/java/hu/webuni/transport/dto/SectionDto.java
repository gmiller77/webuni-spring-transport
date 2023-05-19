package hu.webuni.transport.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class SectionDto {
	
	private long id;
	
	@PositiveOrZero
	private int number;
	
	private MilestoneDto fromMilestone;
	private MilestoneDto toMilestone;
	
	public SectionDto() {
		super();
	}

	public SectionDto(@Positive int number, MilestoneDto fromMilestone, MilestoneDto toMilestone) {
		super();
		this.number = number;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public MilestoneDto getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(MilestoneDto fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public MilestoneDto getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(MilestoneDto toMilestone) {
		this.toMilestone = toMilestone;
	}
	

}
