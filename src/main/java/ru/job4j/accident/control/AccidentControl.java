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
import ru.job4j.accident.repository.AccidentJdbcTemplate;

@Controller
public class AccidentControl {
    private final AccidentJdbcTemplate repository;
    private List<AccidentType> types;
    private List<Rule> rules = new ArrayList<>();

    public AccidentControl(AccidentJdbcTemplate repository) {
        this.repository = repository;
        this.types = new ArrayList<>(this.repository.getTypes());
        this.rules = new ArrayList<>(this.repository.getRules());

    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/create";
    }
    
    @GetMapping("/update")
    public String edit(@RequestParam Integer id , Model model) {
    	model.addAttribute("accident", repository.findById(id));
    	model.addAttribute("types", types);
    	 model.addAttribute("rules", rules);
    	return "accident/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
    	String[] ids = req.getParameterValues("rIds");
    	repository.save(accident, ids);
        return "redirect:/";
    }
}
