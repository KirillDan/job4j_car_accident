package ru.job4j.accident.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private AtomicInteger ids = new AtomicInteger(0);
    private HashMap<Integer, String> types = new HashMap<Integer, String>();
    private HashMap<Integer, String> rules = new HashMap<Integer, String>();
    
    public AccidentMem() {
    	types.put(1, "Две машины");
        types.put(2, "Машина и человек");
        types.put(3, "Машина и велосипед");
        rules.put(1, "Статья. 1");
        rules.put(2, "Статья. 2");
        rules.put(3, "Статья. 3");
	}

	public void add(Accident value) {
		if (value.getType() != null) {
			Integer id = value.getType().getId();
			String name = this.types.get(id);
			value.getType().setName(name);
		}
    	if (value.getId() == 0) {
    		value.setId(this.ids.incrementAndGet());
            this.accidents.put(ids.get(), value);
    	} else {
    		this.accidents.put(value.getId(), value);
    	}
    }
	
	public void add(Accident value, String[] ruleIds) {
		for (String strId : ruleIds) {
			Integer id = Integer.valueOf(strId);
			value.addRule(Rule.of(id, this.rules.get(id)));
		}
		this.add(value);
	}
    
    public void updeteAccidentType(Integer id, AccidentType accidentType) {
    	this.accidents.get(id).setType(accidentType);
    }
    
    public Accident findById(Integer id) {
    	return this.accidents.get(id);
    }
    
    public Collection<Accident> getAll() {
        return this.accidents.values();
    }
}
