package hu.webuni.transport.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "transport")
public class TransportConfigProperties {

	// HOTO config file: add JWT

	private List<DelayCharge> delayChargeList = new ArrayList<DelayCharge>();

	public List<DelayCharge> getDelayChargeList() {
		return delayChargeList;
	}

	public void setDelayChargeList(List<DelayCharge> delayChargeList) {
		this.delayChargeList = delayChargeList;
	}

	public static class DelayCharge {
		private int minutes;
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int min) {
			this.minutes = min;
		}

	}

}
