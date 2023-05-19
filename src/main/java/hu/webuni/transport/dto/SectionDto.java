package hu.webuni.transport.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class SectionDto {

	private Long sectionId;

	@PositiveOrZero
	private int number;

	private MilestoneDto fromMilestoneDto;
	private MilestoneDto toMilestoneDto;

	public SectionDto() {
		super();
	}


	public SectionDto(Long sectionId, @PositiveOrZero int number, MilestoneDto fromMilestoneDto,
			MilestoneDto toMilestoneDto) {
		super();
		this.sectionId = sectionId;
		this.number = number;
		this.fromMilestoneDto = fromMilestoneDto;
		this.toMilestoneDto = toMilestoneDto;
	}


	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public MilestoneDto getFromMilestoneDto() {
		return fromMilestoneDto;
	}

	public void setFromMilestoneDto(MilestoneDto fromMilestoneDto) {
		this.fromMilestoneDto = fromMilestoneDto;
	}

	public MilestoneDto getToMilestoneDto() {
		return toMilestoneDto;
	}

	public void setToMilestoneDto(MilestoneDto toMilestoneDto) {
		this.toMilestoneDto = toMilestoneDto;
	}

}
