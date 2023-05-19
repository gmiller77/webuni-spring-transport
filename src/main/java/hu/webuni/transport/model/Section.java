package hu.webuni.transport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long sectionId;

	private int number;

	@OneToOne()
	@JoinColumn(name = "fromMilestoneId", referencedColumnName = "milestoneId")
	private Milestone fromMilestone;
	@OneToOne()
	@JoinColumn(name = "toMilestoneId", referencedColumnName = "milestoneId")
	private Milestone toMilestone;

	@ManyToOne
	private TransportPlan transportPlan;

	public Section() {
		super();
	}

	public Section(int number, Milestone fromMilestone, Milestone toMilestone) {
		super();
		this.number = number;
		this.fromMilestone = fromMilestone;
		this.toMilestone = toMilestone;
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

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

}
