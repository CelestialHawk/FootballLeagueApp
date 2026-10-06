package com.footballapp.football_league_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.footballapp.football_league_app.DTO.TeamMatchDTO;
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

    public String getResult(Match match, Long teamId) {

        if (match.getHomeTeam().getId().equals(teamId)) {

            if (match.getHomeScore() > match.getAwayScore()) {
                return "WIN";
            }

            if (match.getHomeScore() < match.getAwayScore()) {
                return "LOSS";
            }

            return "DRAW";
        }

        else if (match.getAwayScore() > match.getHomeScore()) {
            return "WIN";
        }

        if (match.getAwayScore() < match.getHomeScore()) {
            return "LOSS";
        }

        return "DRAW";
    }

    public List<TeamMatchDTO> getLast10MatchesForWeb(Long teamId) {

        List<Match> matches = getLast10Matches(teamId);

        List<TeamMatchDTO> result = new ArrayList<>();

        for (Match match : matches) {

            String outcome = getResult(match, teamId);

            result.add(
                    new TeamMatchDTO(match, outcome)
            );
        }

        return result;
    }
}
