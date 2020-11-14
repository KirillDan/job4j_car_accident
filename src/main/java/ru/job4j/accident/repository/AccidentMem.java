package ru.job4j.accident.repository;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import ru.job4j.accident.model.Accident;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private int ids = 0;
    

    public void add(Accident value) {
    	value.setId(++ids);
        this.accidents.put(ids, value);
    }

    public Collection<Accident> getAll() {
        return this.accidents.values();
    }
}
