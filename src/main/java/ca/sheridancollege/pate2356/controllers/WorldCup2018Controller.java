package ca.sheridancollege.pate2356.controllers;

import ca.sheridancollege.pate2356.database.WorldCup2018DB;
import ca.sheridancollege.pate2356.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorldCup2018Controller {

    @Autowired
    WorldCup2018DB db;

    ModelAndView mv;

    @GetMapping("/")
    public String index(){
        return "/home";
    }

    //Works
    @PostMapping("/saveTeam")
    public String processTeam(@ModelAttribute Team team){
        db.insertTeam(team.getTeamName(), team.getContinent(), team.getGamesPlayed(), team.getGamesWon(), team.getGamesDrawn(), team.getGamesLost());
        return "redirect:displayResults";
    }

    //Works
    @GetMapping("/addTeam")
    public String processNewTeamForm(Model model){
        model.addAttribute("team", new Team());
        return "/addTeam";
    }

    //Works
    @GetMapping("/displayResults")
    public String displayResults(Model model){
        model.addAttribute("team", db.getTeams());
        return "/displayResults";
    }

}
