package ca.sheridancollege.pate2356.database;

import ca.sheridancollege.pate2356.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorldCup2018DB {

    @Autowired
    NamedParameterJdbcTemplate jdbc;

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

    public List<Team> getTeams(){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void deleteTeamById(Long teamId){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "DELETE FROM team WHERE teamId = :teamId";
        namedParameters.addValue("teamId", teamId);

        int rowsAffected = jdbc.update(query, namedParameters);
        if (rowsAffected > 0)
            System.out.println("Team record was deleted successfully");
    }

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

    public List<Team> getTeamById(Long teamId){

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM team WHERE teamId =:teamId";
        namedParameters.addValue("teamId",teamId);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }
}
