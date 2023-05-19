package hu.webuni.transport.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private long id;
	
	private Long income;
	
	@OneToMany(mappedBy = "transportPlan")
	private List<Section> routeSections;
	
	public TransportPlan() {
		super();
	}
	public TransportPlan(Long income, List<Section> routeSections) {
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
	public List<Section> getRouteSections() {
		return routeSections;
	}
	public void setRouteSections(List<Section> routeSections) {
		this.routeSections = routeSections;
	}
	
	
}
