package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class IndexControl {
	private AccidentMem repository;
	
    public IndexControl(AccidentMem repository) {
    	this.repository = repository;
		this.repository.add(new Accident(1, "name", "text", "address"));
	}

	@GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("table", repository.getAll());
        return "index";
    }
}
