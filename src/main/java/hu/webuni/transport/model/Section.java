package hu.webuni.transport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Positive;

@Entity
public class Section {
	
	@Id
	@GeneratedValue
	private long id;
	
	private int number;
	
	@OneToOne(mappedBy = "section")
	private Milestone fromMilestone;
	
	@OneToOne(mappedBy = "section")
	private Milestone toMilestone;
	
	public Section() {
		super();
	}

	public Section(@Positive int number, Milestone fromMilestone, Milestone toMilestone) {
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
