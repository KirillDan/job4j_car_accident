package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.job4j.accident.repository.AccidentHibernate;

@Controller
public class IndexControl {
	private AccidentHibernate repository;
	
    public IndexControl(AccidentHibernate repository) {
		this.repository = repository;
	}

	@GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("table", repository.getAll());
        return "index";
    }
}
