package com.footballapp.football_league_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.footballapp.football_league_app.services.LeagueService;

@Controller
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/league")
    public String league(Model model) {

        model.addAttribute(
                "table",
                leagueService.getLeagueTable()
        );

        return "league";
    }
}
