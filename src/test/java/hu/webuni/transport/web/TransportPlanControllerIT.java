package hu.webuni.transport.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.webuni.transport.dto.DelayDto;
import hu.webuni.transport.dto.LoginDto;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.TransportPlanRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportPlanControllerIT {

	private static final String BASE_URI = "/api/transportPlans";

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;

	LoginDto badUserDto = new LoginDto("user", "user");
	LoginDto transportMgrDto = new LoginDto("transport_mgr", "transport");
	LoginDto addressMgrDto = new LoginDto("address_mgr", "address");

	// below DelayDtos are operable with the initial data ...
	// inserted in the 'insertTestData()' method
	// in 'InitDBService.java'
	DelayDto validDelayDto_onPlan1_20min = new DelayDto(2L, 20);
	DelayDto validDelayDto_onPlan1_40min = new DelayDto(2L, 40);
	DelayDto validDelayDto_onPlan1_70min = new DelayDto(2L, 70);
	DelayDto validDelayDto_onPlan1_130min = new DelayDto(2L, 130);
	
	DelayDto invalidDelayDto_onPlan1 = new DelayDto(10L, 70);	//milestone ID '10' is not part of Plan '1' 

	private String jwt;

	@Test
	void testDifferentUserLogins() throws Exception {
		webTestClient.post()
			.uri("/api/login")
			.bodyValue(badUserDto)
			.exchange().expectStatus()
			.isForbidden();

		webTestClient.post()
			.uri("/api/login")
			.bodyValue(addressMgrDto)
			.exchange()
			.expectStatus()
			.isOk();

		webTestClient.post()
			.uri("/api/login")
			.bodyValue(transportMgrDto)
			.exchange()
			.expectStatus()
			.isOk();
	}

	// Auth user can post valid DelayDto data
	@Test
	void testThatOnlyAuthorizedUserCanPostDelays() throws Exception {

		// AddressManager CAN NOT submit transporDelay request
		jwt = doLogin(addressMgrDto);
		registerDelay(1L, validDelayDto_onPlan1_40min)
			.expectStatus()
			.isForbidden();

		jwt = doLogin(transportMgrDto);
		registerDelay(1L, validDelayDto_onPlan1_40min)
			.expectStatus()
			.isOk();
	}
	
	@Test
	void testInvalidDelayRequests() throws Exception {

		jwt = doLogin(transportMgrDto);

		// valid DelayDto but not existing TransportPlan ID 404 :)
		registerDelay(404L, validDelayDto_onPlan1_40min)
			.expectStatus()
			.isBadRequest();
			
		// invalid DelayDto: milestone is not part of plan
		registerDelay(1L, invalidDelayDto_onPlan1)
			.expectStatus()
			.isBadRequest();
	}

	@Test
	void testThatPlanIncomeIsProperlyReducedOnDelay() throws Exception {

		jwt = doLogin(transportMgrDto);
		double tolerance = 0.01; // Specify the desired tolerance level
		
		Long incomeBeforeDelay = getCurrentIncome(1L);
		registerDelay(1L, validDelayDto_onPlan1_20min);	// 20 minutes delay -> zero charge 
		Long incomeAfterDelay = getCurrentIncome(1L);
		assertThat(incomeBeforeDelay).isEqualTo(incomeAfterDelay);
		
		setBackToDefaultIncome(1L, incomeBeforeDelay);
		
		registerDelay(1L, validDelayDto_onPlan1_40min);	// 40 minutes delay -> 10% charge, as defined in APPLICATION.YML 
		incomeAfterDelay = getCurrentIncome(1L);
		assertThat(incomeBeforeDelay*0.9, closeTo(incomeAfterDelay, tolerance));
		
		setBackToDefaultIncome(1L, incomeBeforeDelay);
		registerDelay(1L, validDelayDto_onPlan1_70min);	// 70 minutes delay -> 15% charge, as defined in APPLICATION.YML 
		incomeAfterDelay = getCurrentIncome(1L);
		assertThat(incomeBeforeDelay*0.85, closeTo(incomeAfterDelay, tolerance));
		
		setBackToDefaultIncome(1L, incomeBeforeDelay);
		registerDelay(1L, validDelayDto_onPlan1_130min);	// 130 minutes delay -> 20% charge, as defined in APPLICATION.YML 
		incomeAfterDelay = getCurrentIncome(1L);
		assertThat(incomeBeforeDelay*0.8, closeTo(incomeAfterDelay, tolerance));
		
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

	private ResponseSpec registerDelay(Long transportPlanId, DelayDto delayDto) {
		String path = BASE_URI + "/" + transportPlanId.toString() + "/delay";
		return webTestClient.post()
				.uri(path).headers(headers -> headers.setBearerAuth(jwt))
				.bodyValue(delayDto)
				.exchange();
	}
	
	public Long getCurrentIncome (Long id) {
		return transportPlanRepository.findById(id).get().getIncome();
	}
	
	private void setBackToDefaultIncome(long id, Long incomeBeforeDelay) {
		TransportPlan plan = transportPlanRepository.findById(id).get();
		plan.setIncome(incomeBeforeDelay);
		transportPlanRepository.save(plan);
	}

}
