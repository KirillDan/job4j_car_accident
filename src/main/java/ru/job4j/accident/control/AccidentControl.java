package ru.job4j.accident.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService service;
    private List<AccidentType> types;
    private List<Rule> rules = new ArrayList<>();

    public AccidentControl(AccidentService service) {
        this.service = service;
        this.types = new ArrayList<>(this.service.getTypes());
        this.rules = new ArrayList<>(this.service.getRules());

    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/create";
    }
    
    @GetMapping("/update")
    public String edit(@RequestParam Integer id , Model model) {
    	model.addAttribute("accident", service.findById(id));
    	model.addAttribute("types", types);
    	model.addAttribute("rules", rules);
    	return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
    	String[] ids = req.getParameterValues("rIds");
    	service.save(accident, ids);
        return "redirect:/";
    }
}
