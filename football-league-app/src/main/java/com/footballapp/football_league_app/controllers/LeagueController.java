package com.footballapp.football_league_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.footballapp.football_league_app.services.LeagueService;
import com.footballapp.football_league_app.services.TeamMatchService;

@Controller
public class LeagueController {

    private final LeagueService leagueService;
    private final TeamMatchService teamMatchService;

    public LeagueController(LeagueService leagueService, TeamMatchService teamMatchService) {
        this.leagueService = leagueService;
        this.teamMatchService = teamMatchService;
    }

    @GetMapping("/league")
    public String league(Model model) {

        model.addAttribute(
                "table",
                leagueService.getLeagueTable()
        );

        return "league";
    }

    @GetMapping("/team/{id}")
    public String team(
        @PathVariable Long id,
        Model model) {

    model.addAttribute(
            "matches",
            teamMatchService.getLast10MatchesForWeb(id)
    );

    return "team";
}

}
