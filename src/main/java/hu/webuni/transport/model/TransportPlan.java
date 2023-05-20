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
	private Long transportPlanId;

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

	public long getTransportPlanId() {
		return transportPlanId;
	}

	public void setTransportPlanId(long transportPlanId) {
		this.transportPlanId = transportPlanId;
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

	@Override
	public String toString() {
		return "TransportPlan [transportPlanId=" + transportPlanId + ", income=" + income + ", routeSections="
				+ routeSections + "]";
	}

}
