package ru.job4j.accident.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Accident {
	private Integer id;
    private String name;
    private String text;
    private String address; 
    private AccidentType type;
    private Set<Rule> rules;
	public Accident() {
	}
	public Accident(int id, String name, String text, String address) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AccidentType getType() {
		return type;
	}
	public void setType(AccidentType type) {
		this.type = type;
	}
	public Set<Rule> getRules() {
		return rules;
	}
	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
	public void addRule(Rule rule) {
		if (this.rules == null) {
			this.rules = new HashSet<Rule>();
		}
		this.rules.add(rule);
	}
	@Override
	public String toString() {
		return "Accident [id=" + id + ", name=" + name + ", text=" + text + ", address=" + address + ", type=" + type
				+ ", rules=" + rules + "]";
	}
	
}
