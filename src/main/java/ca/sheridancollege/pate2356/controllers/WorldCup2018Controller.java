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
    DatabaseAccess db; //variable to assign the database class

    ModelAndView mv;

    String sortNumber = "0"; //variable to determine which way to sort the table


    //Controller to lead to the home page
    @GetMapping("/")
    public String index(){
        return "/home";
    }

    //Controller to save the team to a database
    @PostMapping("/saveTeam")
    public String processTeam(@ModelAttribute Team team, @RequestParam String continent){
        team.setContinent(continent); //setting continent from a drop down list
        db.insertTeam(team.getTeamName(), team.getContinent(), team.getGamesPlayed(), team.getGamesWon(), team.getGamesDrawn(), team.getGamesLost());
        return "redirect:addTeam";
    }

    //Controller to go to the add team page
    @GetMapping("/addTeam")
    public String processNewTeamForm(Model model){
        model.addAttribute("team", new Team()); //creating a new team object to be used as model
        return "/addTeam";
    }

    //Controller to go to the display results page.
    @GetMapping("/displayResults")
    public String displayResults(Model model){
        //If statements that will determine conditions for which way the table should be sorted
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


    //Controller to delete the team by its hidden ID
    @GetMapping("/deleteTeamById/{id}")
    public String deleteTeamById(@PathVariable Long id){
        db.deleteTeamById(id);
        return "redirect:/deleteTeam";
    }

    //Controller to go to the delete team page
    @GetMapping("/deleteTeam")
    public String deleteTeam(Model model){
        model.addAttribute("team", db.getTeams()); //added an attribute that will display the database table
        return "/deleteTeam";
    }

    //Controller to go to the edit team page
    @GetMapping("/editTeam")
    public String editTeam(Model model){
        model.addAttribute("team", db.getTeams());
        return "/editTeam";
    }

    //Controller that will modify the team based on user input
    @GetMapping("/modifyTeam")
    public String modifyTeam(@ModelAttribute Team team, Model model, @RequestParam String continent){
        team.setContinent(continent);
        db.editTeamById(team);
        model.addAttribute("team", team);
        return "redirect:/editTeam";
    }

    //Controller that will select which team needs to be edited
    @GetMapping("/editTeamById/{id}")
    public String editTeamById(@PathVariable Long id, Model model){
        Team team;
        team = db.getTeamById(id).get(0);
        model.addAttribute("team", team);
        return "/editTeamForm";
    }

    //Controller to assign a value to global variable for sorting
    @GetMapping("/sortTeam")
    public String sortTeam(Model model, @RequestParam String sort){
        sortNumber = sort;
        return "redirect:displayResults";
    }
}
