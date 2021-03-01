package ca.sheridancollege.pate2356.database;

import ca.sheridancollege.pate2356.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;


    //Function that will call a query to add team to database
    public void insertTeam(String teamName, String continent, Integer gamesPlayed, Integer gamesWon, Integer gamesDrawn, Integer gamesLost){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "INSERT INTO team(teamName, continent, gamesPlayed, gamesWon, gamesDrawn, gamesLost) VALUES(:teamName, :continent, :gamesPlayed, :gamesWon, :gamesDrawn, :gamesLost)";
        namedParameters.addValue("teamName", teamName);
        namedParameters.addValue("continent", continent);
        namedParameters.addValue("gamesPlayed", gamesPlayed);
        namedParameters.addValue("gamesWon", gamesWon);
        namedParameters.addValue("gamesDrawn", gamesDrawn);
        namedParameters.addValue("gamesLost", gamesLost);

        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was added successfully");
    }

    //Function that will call a query to list all the teams saved within the database
    public List<Team> getTeams(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    //Function that will call a query to delete the team row by its hidden ID
    public void deleteTeamById(Long teamId){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "DELETE FROM team WHERE teamId = :teamId";
        namedParameters.addValue("teamId", teamId);

        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was deleted successfully");
    }

    //Function that will call a query to edit the team.
    public void editTeamById(Team team){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE team SET teamName =:teamName, continent =:continent, gamesPlayed =:gamesPlayed, gamesWon =:gamesWon, gamesDrawn =:gamesDrawn, gamesLost =:gamesLost  WHERE teamId = :teamId";
        namedParameters.addValue("teamId", team.getTeamId());
        namedParameters.addValue("teamName", team.getTeamName());
        namedParameters.addValue("continent", team.getContinent());
        namedParameters.addValue("gamesPlayed", team.getGamesPlayed());
        namedParameters.addValue("gamesWon", team.getGamesWon());
        namedParameters.addValue("gamesDrawn", team.getGamesDrawn());
        namedParameters.addValue("gamesLost", team.getGamesLost());

        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was updated successfully");
    }

    //Function that will call a query to select a specific row according to the team ID
    public List<Team> getTeamById(Long teamId){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team WHERE teamId =:teamId";
        namedParameters.addValue("teamId",teamId);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    //Function that will call a query to sort the points column by points
    public List<Team> sortTeamByPoints(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team ORDER BY (gamesWon * 3 + gamesDrawn) DESC";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    //Function that will call a query to sort the points column by name
    public List<Team> sortTeamByName(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team ORDER BY (teamName)";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    //Function that will call a query to sort the points column by continent
    public List<Team> sortTeamByContinent(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team ORDER BY (continent)";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }
}
