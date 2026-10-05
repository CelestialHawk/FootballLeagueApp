package com.footballapp.football_league_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.footballapp.football_league_app.entities.LeagueTable;
import com.footballapp.football_league_app.entities.Match;
import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.repositories.MatchRepository;
import com.footballapp.football_league_app.repositories.TeamRepository;

@Service
public class LeagueService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public LeagueService(
            TeamRepository teamRepository,
            MatchRepository matchRepository) {

        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<LeagueTable> getLeagueTable() {

        List<Team> teams = teamRepository.findAll();
        List<Match> matches = matchRepository.findAll();

        List<LeagueTable> table = new ArrayList<>();

        for (Team team : teams) {

            LeagueTable entry = new LeagueTable(team);

            for (Match match : matches) {

                if (match.getHomeTeam().getId().equals(team.getId())) {

                    if (match.getHomeScore() > match.getAwayScore()) {
                        entry.addWin();
                    }
                    else if (match.getHomeScore() < match.getAwayScore()) {
                        entry.addLoss();
                    }
                    else {
                        entry.addDraw();
                    }
                }

                else if (match.getAwayTeam().getId().equals(team.getId())) {

                    if (match.getAwayScore() > match.getHomeScore()) {
                        entry.addWin();
                    }
                    else if (match.getAwayScore() < match.getHomeScore()) {
                        entry.addLoss();
                    }
                    else {
                        entry.addDraw();
                    }
                }
            }

            table.add(entry);
        }

        table.sort((a, b) -> 
            Integer.compare(b.getPoints(), a.getPoints()));
        
            for (int i = 0; i < table.size(); i++) {
                table.get(i).setPosition(i + 1);
            }
            
    return table;
    }
}