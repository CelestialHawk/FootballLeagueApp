package com.footballapp.football_league_app.DTO;

import com.footballapp.football_league_app.entities.Match;

public class TeamMatchDTO {

    private Match match;
    private String result;

    public TeamMatchDTO(Match match, String result) {
        this.match = match;
        this.result = result;
    }

    public Match getMatch() {
        return match;
    }

    public String getResult() {
        return result;
    }
}
