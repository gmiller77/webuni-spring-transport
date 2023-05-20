package hu.webuni.transport.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	private Long milestoneId;

	@ManyToOne
	@JoinColumn(name ="addressId")
	private Address address;
	private LocalDateTime plannedTime;

	public Milestone() {
		super();
	}

	public Milestone(Address address, LocalDateTime plannedTime) {
		super();
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	@Override
	public String toString() {
		return "Milestone [milestoneId=" + milestoneId + ", address=" + address + ", plannedTime=" + plannedTime + "]";
	}

}
