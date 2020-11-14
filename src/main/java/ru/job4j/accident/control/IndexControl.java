package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.job4j.accident.dto.User;
import ru.job4j.accident.repository.UserStore;

@Controller
public class IndexControl {
	private UserStore store;
	
    public IndexControl(UserStore store) {
		this.store = store;
	}

	@GetMapping("/")
    public String index(Model model) {
    	this.store.add(new User(1, "firstname", "lastname", "gender", "description"));
    	model.addAttribute("table", store.getAll());
        return "index";
    }
}
