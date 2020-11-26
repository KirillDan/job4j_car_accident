package ru.job4j.accident.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

@Service
public class AccidentService {
	private final AccidentRepository accidentRepository;
	private final AccidentTypeRepository accidentTypeRepository;
	private final RuleRepository ruleRepository;
	
	public AccidentService(
			AccidentRepository accidentRepository, 
			AccidentTypeRepository accidentTypeRepository,
			RuleRepository ruleRepository) 
	{
		this.accidentRepository = accidentRepository;
		this.accidentTypeRepository = accidentTypeRepository;
		this.ruleRepository = ruleRepository;
		this.insertingAccidentType();
		this.insertingRule();
	}
	
	private void insertingAccidentType() {
		if (this.accidentTypeRepository.count() == 0) {
			this.accidentTypeRepository.save(AccidentType.of(1, "Две машины"));
			this.accidentTypeRepository.save(AccidentType.of(2, "Машина и человек"));
			this.accidentTypeRepository.save(AccidentType.of(3, "Машина и велосипед"));
		}
	}

	private void insertingRule() {
		if (this.ruleRepository.count() == 0) {
			this.ruleRepository.save(Rule.of(1, "Статья. 1"));
			this.ruleRepository.save(Rule.of(2, "Статья. 2"));
			this.ruleRepository.save(Rule.of(3, "Статья. 3"));
		}
	}
	
	public List<AccidentType> getTypes() {
		List<AccidentType> res = new ArrayList<>();
		this.accidentTypeRepository.findAll().forEach(res::add);
		return res;
	}
	
	public List<Rule> getRules() {
		List<Rule> res = new ArrayList<>();
		this.ruleRepository.findAll().forEach(res::add);
		return res;
	}
	
	public void add(Accident accident) {
		this.accidentRepository.save(accident);
	}
	
	public Accident findById(Integer id) {
		return this.accidentRepository.findById(id).get();
	}
	
	public void save(Accident accident, String[] ruleIds) {
		for (String stringId : ruleIds) {
			Integer id = Integer.valueOf(stringId);
			Rule rule = this.ruleRepository.findById(id).get();
			accident.addRule(rule);
		}
		this.accidentRepository.save(accident);
	}
	
	public Collection<Accident> getAll() {
		List<Accident> res = new ArrayList<>();
		accidentRepository.findAll().forEach(res::add);
		return res;
	}
}
