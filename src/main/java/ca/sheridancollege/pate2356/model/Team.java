package ca.sheridancollege.pate2356.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Team implements Serializable {

    //dont forget to add team image
    private Long teamId;
    @NonNull
    private String teamName;
    private String continent;
    private Integer numGamesPlayed;
    private Integer numWon;
    private Integer numDrawn;
    private Integer numLost;

}
