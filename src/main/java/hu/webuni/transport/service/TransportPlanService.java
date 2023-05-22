package hu.webuni.transport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.config.TransportConfigProperties;
import hu.webuni.transport.config.TransportConfigProperties.DelayCharge;
import hu.webuni.transport.dto.DelayDto;
import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.model.Section;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.MilestoneRepository;
import hu.webuni.transport.repository.SectionRepository;
import hu.webuni.transport.repository.TransportPlanRepository;
import jakarta.validation.Valid;

@Service
public class TransportPlanService {

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	TransportConfigProperties config;

	@Transactional
	public void registerDelay(long transportPlanId, @Valid DelayDto delayDto) {

		Long milestoneId = delayDto.getMilestoneId();
		int delayMinutes = delayDto.getDelayMinutes();

		TransportPlan transportPlanToUpdate;

		if (milestoneRepository.findById(milestoneId).isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not existing milestone with ID: " + milestoneId);
		transportPlanToUpdate = transportPlanRepository.findById(transportPlanId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Not existing transportPlan with ID: " + transportPlanId));

		List<Section> routeSections = transportPlanToUpdate.getRouteSections();

		Integer foundSectionNumber = null;
		boolean isStart = false;
		boolean isEnd = false;

		Milestone fromMilestone = null;
		Milestone toMilestone = null;

		for (Section section : routeSections) {
			fromMilestone = section.getFromMilestone();
			toMilestone = section.getToMilestone();

			if (fromMilestone.getMilestoneId().equals(milestoneId)) {
				foundSectionNumber = section.getNumber();
				isStart = true;
			}
			if (toMilestone.getMilestoneId().equals(milestoneId)) {
				foundSectionNumber = section.getNumber();
				isEnd = true;
			}

			if (foundSectionNumber != null) {
				break;
			}
		}

		if (foundSectionNumber == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The referred milestone (Nr.: " + milestoneId
					+ ") is not part of transportPlan (ID: " + transportPlanId + ").");

		applyDelayToMilestone(milestoneId, delayMinutes);

		if (isStart && toMilestone != null) {
			applyDelayToMilestone(toMilestone.getMilestoneId(), delayMinutes);
		}

		if (isEnd) {
			Section nextSection = sectionRepository.findByNumberAndTransportPlan(foundSectionNumber + 1,
					transportPlanToUpdate);

			if (nextSection != null) {
				applyDelayToMilestone(nextSection.getFromMilestone().getMilestoneId(), delayMinutes);
			}
		}

		// TODO test out 5x different cases /versions/ for delay alternatives
		int reducePercent = calculateChargePercent(delayMinutes);
		
		Long income = transportPlanToUpdate.getIncome();
		Long updatedIncome = (long) (income * (100 - reducePercent) / 100.0);
		transportPlanToUpdate.setIncome(updatedIncome);
		System.out.println(	"TransportPlan: " + transportPlanId +
							" ** Milestone: " + milestoneId +
							" ** delay: " + delayMinutes + " mins" +
							" ** fine: -" + reducePercent + "%" +
							" ** original income: " + income +
							" ** reduced income: " + updatedIncome);
		transportPlanRepository.save(transportPlanToUpdate);
	}

	private int calculateChargePercent(int delayMinutes) {
		List<DelayCharge> delayChargeList = config.getDelayChargeList();

		Optional<DelayCharge> relevantDelayCharge = delayChargeList.stream()
                .filter(delayCharge -> delayCharge.getMinutes() <= delayMinutes)
                .max((c1, c2) -> Integer.compare(c1.getMinutes(), c2.getMinutes()));

        return relevantDelayCharge.map(DelayCharge::getPercent).orElse(0);
	}

	@Transactional
	private void applyDelayToMilestone(Long milestoneId, int delayMinutes) {
		Milestone milestoneToUpdate = milestoneRepository.findById(milestoneId).get();
		LocalDateTime originalPlannedDateTime = milestoneToUpdate.getPlannedTime();
		LocalDateTime delayedDatetime = originalPlannedDateTime.plusMinutes(delayMinutes);
		milestoneToUpdate.setPlannedTime(delayedDatetime);
		milestoneRepository.save(milestoneToUpdate);
	}

	public List<TransportPlan> findAll() {
		return transportPlanRepository.findAll();
	}

}
