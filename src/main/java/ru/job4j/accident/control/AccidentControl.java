package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem repository;

    public AccidentControl(AccidentMem repository) {
        this.repository = repository;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }
    
    @GetMapping("/update")
    public String edit(@RequestParam Integer id ,Model model) {
    	model.addAttribute("accident", repository.findById(id));
    	return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
    	repository.add(accident);
        return "redirect:/";
    }
}
