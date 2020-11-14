package ru.job4j.di;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInput {
	private List<String> inputList = new ArrayList<String>();
	
	public void input() {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		boolean next = true;
		while (next) {
			input = scanner.next().trim();
			if (input.equalsIgnoreCase("exit")) {
				next = false;
			} else {
				inputList.add(input);
			}
		}
	}
	
	public List<String> get(){
		return inputList;
	}
}
