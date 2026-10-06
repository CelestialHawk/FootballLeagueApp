package com.footballapp.football_league_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.footballapp.football_league_app.entities.Match;
import com.footballapp.football_league_app.repositories.MatchRepository;

@Service
public class TeamMatchService {

    private final MatchRepository matchRepository;

    public TeamMatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getLast10Matches(Long teamId) {

        return matchRepository
                .findTop10ByHomeTeamIdOrAwayTeamIdOrderByIdDesc(
                        teamId,
                        teamId
                );
    }
}
