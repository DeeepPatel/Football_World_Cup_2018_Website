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
}
