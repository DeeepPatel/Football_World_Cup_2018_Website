package ca.sheridancollege.pate2356.controllers;

import ca.sheridancollege.pate2356.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorldCup2018Controller {

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("team", new Team());

        return "addTeam";
    }

    @PostMapping("/addTeam")
    public String processTeam(@ModelAttribute Team team){

        return "addTeam";
    }


}
