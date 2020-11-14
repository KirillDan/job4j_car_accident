package ru.job4j.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartUI {
	@Autowired
    private Store store;
	@Autowired
    private ConsoleInput consoleInput;

    public void add(String value) {
        store.add(value);
    }
    
    public void addFromConsole() {
       this.consoleInput.input();
       List<String> inputList = this.consoleInput.get();
       for (String value : inputList) {
    	   store.add(value);
       }
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
