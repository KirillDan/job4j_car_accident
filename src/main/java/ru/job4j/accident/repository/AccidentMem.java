package ru.job4j.accident.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private int ids = 0;
    private HashMap<Integer, String> types = new HashMap<Integer, String>();
    
    public AccidentMem() {
    	types.put(1, "Две машины");
        types.put(2, "Машина и человек");
        types.put(3, "Машина и велосипед");
	}

	public void add(Accident value) {
		if (value.getType() != null) {
			Integer id = value.getType().getId();
			String name = this.types.get(id);
			value.getType().setName(name);
		}
    	if (value.getId() == null) {
    		value.setId(++ids);
            this.accidents.put(ids, value);
    	} else {
    		this.accidents.put(value.getId(), value);
    	}
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
