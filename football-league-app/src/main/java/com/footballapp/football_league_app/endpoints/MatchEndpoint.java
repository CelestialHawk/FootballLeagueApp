package com.footballapp.football_league_app.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.repositories.TeamRepository;
import com.footballapp.football_league_app.entities.Match;
import com.footballapp.football_league_app.repositories.MatchRepository;

import com.footballapp.league.AddMatchRequest;
import com.footballapp.league.AddMatchResponse;

@Endpoint
public class MatchEndpoint {

    private static final String NAMESPACE_URI = "http://footballapp.com/league";

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public MatchEndpoint(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @PayloadRoot(
        namespace = NAMESPACE_URI,
        localPart = "addMatchRequest"
    )
    @ResponsePayload
    public AddMatchResponse addMatch(
            @RequestPayload AddMatchRequest request) {

        Team homeTeam = teamRepository.findById(request.getHomeTeamId())
                .orElseThrow();
        Team awayTeam = teamRepository.findById(request.getAwayTeamId())
                .orElseThrow();

        Match match = new Match(
                homeTeam,
                awayTeam,
                request.getHomeScore(),
                request.getAwayScore()
        );

        Match savedMatch = matchRepository.save(match);

        AddMatchResponse response = new AddMatchResponse();
        response.setMatchId(savedMatch.getId());

        return response;

    }
}