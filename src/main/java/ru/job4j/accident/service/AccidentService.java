package ru.job4j.accident.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Service
public class AccidentService {
	private AccidentMem repository;
	
	public AccidentService(AccidentMem repository) {
		this.repository = repository;
	}

	public void add(Accident accident) {
		this.repository.add(accident);
	}
	
	public Collection<Accident> getAll() {
		return this.repository.getAll();
	}
}
