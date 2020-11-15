package ru.job4j.accident.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem repository;
    private List<AccidentType> types = new ArrayList<>();

    public AccidentControl(AccidentMem repository) {
        this.repository = repository;
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", types);
        return "accident/create";
    }
    
    @GetMapping("/update")
    public String edit(@RequestParam Integer id ,Model model) {
    	model.addAttribute("accident", repository.findById(id));
    	model.addAttribute("types", types);
    	return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
    	repository.add(accident);
        return "redirect:/";
    }
}
