package hu.webuni.transport.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.webuni.transport.dto.DelayDto;
import hu.webuni.transport.dto.LoginDto;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportPlanControllerIT {

	private static final String BASE_URI = "/api/transportPlans";

	@Autowired
	WebTestClient webTestClient;

	LoginDto badUserDto = new LoginDto("user", "user");
	LoginDto transportMgrDto = new LoginDto("transport_mgr", "transport");
	
	// below DelayDtos are operable with the initial data ...
	// inserted in the 'insertTestData()' method
	// in 'InitDBService.java'
	DelayDto validDelayDto_onPlan1_1 = new DelayDto(2L, 25);
	DelayDto validDelayDto_onPlan1_2 = new DelayDto(2L, 35);
	DelayDto validDelayDto_onPlan1_3 = new DelayDto(2L, 65);
	DelayDto invalidDelayDto_onPlan1_1 = new DelayDto(10L, 65);

	private String jwt;

	@Test
	void testDifferentUserLogins() throws Exception {
		webTestClient.post()
			.uri("/api/login")
			.bodyValue(badUserDto)
			.exchange()
			.expectStatus()
			.isForbidden();
		
		webTestClient.post()
			.uri("/api/login")
			.bodyValue(transportMgrDto)
			.exchange()
			.expectStatus()
			.isOk();
	}
	
	
	// Auth user can post valid DelayDto data
	@Test
	void testThatOnlyAuthUserCanPostDelays() throws Exception {

		jwt = doLogin(transportMgrDto);
		registerDelay(1L, validDelayDto_onPlan1_1)
		.expectStatus()
		.isOk();
		
		jwt = doLogin(transportMgrDto);
		registerDelay(1L, validDelayDto_onPlan1_2)
		.expectStatus()
		.isOk();
	}

	private String doLogin(LoginDto loginDto) {
		return webTestClient.post()
				.uri("/api/login")
				.bodyValue(loginDto)
				.exchange()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
	}

	private ResponseSpec registerDelay(Long transportPlanId, DelayDto delayDto ) {
		String path = BASE_URI + "/" + transportPlanId.toString() + "/delay";
		return webTestClient
				.post()
				.uri(path)
				.headers(headers -> headers.setBearerAuth(jwt))
				.bodyValue(delayDto)
				.exchange();
	}
}
