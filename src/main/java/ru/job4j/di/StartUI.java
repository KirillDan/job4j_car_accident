package ru.job4j.di;

import java.util.List;

public class StartUI {

    private Store store;
    private ConsoleInput consoleInput;

    public StartUI(Store store, ConsoleInput consoleInput) {
        this.store = store;
        this.consoleInput = consoleInput;
    }

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
