package hu.webuni.transport.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.transport.model.Address;
import hu.webuni.transport.model.Milestone;
import hu.webuni.transport.model.Section;
import hu.webuni.transport.model.TransportPlan;
import hu.webuni.transport.repository.AddressRepository;
import hu.webuni.transport.repository.MilestoneRepository;
import hu.webuni.transport.repository.SectionRepository;
import hu.webuni.transport.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;

@Service
public class InitDBService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	MilestoneRepository milestoneRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	TransportPlanRepository transportPlanRepository;

//	private List<Address> addresses = new ArrayList<>();
//	private List<Milestone> milestones = new ArrayList<>();
//	private List<Section> sections = new ArrayList<>();
//	private List<TransportPlan> transportPlans = new ArrayList<>();

	@Transactional
	public void clearDB() {
		addressRepository.deleteAll();
		milestoneRepository.deleteAll();
		sectionRepository.deleteAll();
		transportPlanRepository.deleteAll();
	}

	@Transactional
	public void insertTestData() {

		Address a01 = addressRepository
				.save(new Address("HU", "1201", "Budapest", "Dreher utca", 1, Double.valueOf(11), Double.valueOf(11)));

		Address a02 = addressRepository
				.save(new Address("HU", "1202", "Sopron", "Soproni köz", 2, Double.valueOf(22), Double.valueOf(22)));

		Address a03 = addressRepository
				.save(new Address("HU", "1203", "Miskolc", "Borsodi tér", 3, Double.valueOf(33), Double.valueOf(33)));

		Address a04 = addressRepository
				.save(new Address("HU", "1204", "Komló", "Maláta árok", 4, Double.valueOf(44), Double.valueOf(44)));

		Address a05 = addressRepository.save(
				new Address("DE", "11155", "München", "Lager Strasse", 5, Double.valueOf(55), Double.valueOf(55)));

		Address a06 = addressRepository
				.save(new Address("DE", "11166", "Bremen", "Dunkel Str.", 6, Double.valueOf(66), Double.valueOf(66)));

		Address a07 = addressRepository.save(
				new Address("DE", "11177", "Düsseldorf", "WeissBier Str.", 7, Double.valueOf(77), Double.valueOf(77)));

		Address a08 = addressRepository.save(
				new Address("DE", "11188", "Mainz", "German Pilsner Str.", 8, Double.valueOf(88), Double.valueOf(88)));

		Address a09 = addressRepository.save(
				new Address("GB", "12-200", "London", "Porter Street", 9, Double.valueOf(99), Double.valueOf(99)));

		Address a10 = addressRepository.save(new Address("GB", "12-201", "Edinburgh", "Scotch Ale Road", 10,
				Double.valueOf(12), Double.valueOf(21)));

		Address a11 = addressRepository.save(
				new Address("GB", "12-202", "Glasgow", "Stout Square", 11, Double.valueOf(22), Double.valueOf(22)));

		Address a12 = addressRepository.save(
				new Address("GB", "12-203", "Machester", "Brown Ale Str.", 12, Double.valueOf(13), Double.valueOf(31)));

		// TODO correct DateTimes for test calculations
		Milestone ms01 = milestoneRepository.save(new Milestone(a01, LocalDateTime.now().plusMinutes(100)));
		Milestone ms02 = milestoneRepository.save(new Milestone(a02, LocalDateTime.now().plusMinutes(200)));
		Milestone ms03 = milestoneRepository.save(new Milestone(a03, LocalDateTime.now().plusMinutes(300)));
		Milestone ms04 = milestoneRepository.save(new Milestone(a04, LocalDateTime.now().plusMinutes(400)));
		Milestone ms05 = milestoneRepository.save(new Milestone(a05, LocalDateTime.now().plusMinutes(150)));
		Milestone ms06 = milestoneRepository.save(new Milestone(a06, LocalDateTime.now().plusMinutes(250)));
		Milestone ms07 = milestoneRepository.save(new Milestone(a07, LocalDateTime.now().plusMinutes(350)));
		Milestone ms08 = milestoneRepository.save(new Milestone(a08, LocalDateTime.now().plusMinutes(450)));
		Milestone ms09 = milestoneRepository.save(new Milestone(a09, LocalDateTime.now().plusMinutes(600)));
		Milestone ms10 = milestoneRepository.save(new Milestone(a10, LocalDateTime.now().plusMinutes(700)));
		Milestone ms11 = milestoneRepository.save(new Milestone(a11, LocalDateTime.now().plusMinutes(800)));
		Milestone ms12 = milestoneRepository.save(new Milestone(a12, LocalDateTime.now().plusMinutes(900)));

		List<Section> sectionList = new ArrayList<Section>();

		TransportPlan plan1 = transportPlanRepository.save(new TransportPlan(1_000_000L, sectionList));
		sectionList.add(sectionRepository.save(new Section(0, ms01, ms02, plan1)));
		sectionList.add(sectionRepository.save(new Section(1, ms02, ms03, plan1)));
		sectionList.add(sectionRepository.save(new Section(2, ms03, ms04, plan1)));
		plan1.setRouteSections(sectionList);
		transportPlanRepository.save(plan1);
		sectionList.clear();

		TransportPlan plan2 = transportPlanRepository.save(new TransportPlan(2_000_000L, sectionList));
		sectionList.add(sectionRepository.save(new Section(0, ms05, ms06, plan2)));
		sectionList.add(sectionRepository.save(new Section(1, ms06, ms07, plan2)));
		sectionList.add(sectionRepository.save(new Section(2, ms07, ms08, plan2)));
		plan2.setRouteSections(sectionList);
		transportPlanRepository.save(plan2);
		sectionList.clear();

		TransportPlan plan3 = transportPlanRepository.save(new TransportPlan(3_000_000L, sectionList));
		sectionList.add(sectionRepository.save(new Section(0, ms09, ms10, plan3)));
		sectionList.add(sectionRepository.save(new Section(1, ms10, ms11, plan3)));
		sectionList.add(sectionRepository.save(new Section(2, ms11, ms12, plan3)));
		plan3.setRouteSections(sectionList);
		transportPlanRepository.save(plan3);

	}

	public void initDB() {
		clearDB();
		insertTestData();
	}

}
