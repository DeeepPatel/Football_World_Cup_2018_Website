package ca.sheridancollege.pate2356.controllers;

import ca.sheridancollege.pate2356.database.DatabaseAccess;
import ca.sheridancollege.pate2356.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WorldCup2018Controller {

    @Autowired
    DatabaseAccess db;

    ModelAndView mv;

    String sortNumber = "0";


    @GetMapping("/")
    public String index(){
        //mv.addObject("team", new Team());
        return "/home";
    }

    //Works
    @PostMapping("/saveTeam")
    public String processTeam(@ModelAttribute Team team, @RequestParam String continent){
        team.setContinent(continent);
        db.insertTeam(team.getTeamName(), team.getContinent(), team.getGamesPlayed(), team.getGamesWon(), team.getGamesDrawn(), team.getGamesLost());
        return "redirect:addTeam";
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
        if (sortNumber.equals("points")){
            model.addAttribute("team", db.sortTeamByPoints());
        }
        else if (sortNumber.equals("continent")){
            model.addAttribute("team", db.sortTeamByContinent());
        }
        else if (sortNumber.equals("name")){
            model.addAttribute("team", db.sortTeamByName());
        }
        else{
            model.addAttribute("team", db.getTeams());
        }
        return "displayResults";
    }



    @GetMapping("/deleteTeamById/{id}")
    public String deleteTeamById(@PathVariable Long id){
        db.deleteTeamById(id);
        return "redirect:/deleteTeam";
    }

    @GetMapping("/deleteTeam")
    public String deleteTeam(Model model){
        model.addAttribute("team", db.getTeams());
        return "/deleteTeam";
    }


    @GetMapping("/editTeam")
    public String editTeam(Model model){
        model.addAttribute("team", db.getTeams());
        return "/editTeam";
    }

    @GetMapping("/modifyTeam")
    public String modifyTeam(@ModelAttribute Team team, Model model, @RequestParam String continent){
        team.setContinent(continent);
        db.editTeamById(team);
        model.addAttribute("team", team);
        return "redirect:/editTeam";
    }

    @GetMapping("/editTeamById/{id}")
    public String editTeamById(@PathVariable Long id, Model model){
        Team team;
        team = db.getTeamById(id).get(0);
        model.addAttribute("team", team);
        return "/editTeamForm";
    }

    @GetMapping("/sortTeam")
    public String sortTeam(Model model, @RequestParam String sort){
        sortNumber = sort;
        return "redirect:displayResults";
    }


}
