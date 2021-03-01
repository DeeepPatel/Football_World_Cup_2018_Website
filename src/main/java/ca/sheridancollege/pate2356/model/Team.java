package ca.sheridancollege.pate2356.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Team implements Serializable {


    //dont forget to add team image
    private Long teamId;
    @NonNull
    private String teamName;
    private String continentList[] = {"Africa", "Asia", "Oceania", "Europe", "North America", "South America"};
    private String continent;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer gamesDrawn;
    private Integer gamesLost;

}
