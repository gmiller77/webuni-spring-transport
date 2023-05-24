package hu.webuni.transport.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "transport")
public class TransportConfigProperties {

	private JwtData jwt = new JwtData();

	public static class JwtData {
		private String issuer;
		private String secret;
		private String alg;
		private Duration duration;

		public String getIssuer() {
			return issuer;
		}

		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public String getAlg() {
			return alg;
		}

		public void setAlg(String alg) {
			this.alg = alg;
		}

		public Duration getDuration() {
			return duration;
		}

		public void setDuration(Duration duration) {
			this.duration = duration;
		}

	}

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

	public JwtData getJwt() {
		return jwt;
	}

	public void setJwt(JwtData jwt) {
		this.jwt = jwt;
	}

}
