package com.footballapp.football_league_app.endpoints;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.Match;
import com.footballapp.football_league_app.services.TeamMatchService;
import com.footballapp.league.GetTeamMatchesRequest;
import com.footballapp.league.GetTeamMatchesResponse;

@Endpoint
public class TeamMatchEndpoint {

    private static final String NAMESPACE_URI =
            "http://footballapp.com/league";

    private final TeamMatchService teamMatchService;

    public TeamMatchEndpoint(TeamMatchService teamMatchService) {
        this.teamMatchService = teamMatchService;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "getTeamMatchesRequest"
    )
    @ResponsePayload
    public GetTeamMatchesResponse getTeamMatches(
            @RequestPayload GetTeamMatchesRequest request) {

        List<Match> matches =
                teamMatchService.getLast10Matches(
                        request.getTeamId()
                );

        GetTeamMatchesResponse response =
                new GetTeamMatchesResponse();

        for (Match match : matches) {

            GetTeamMatchesResponse.Match soapMatch =
                    new GetTeamMatchesResponse.Match();

            soapMatch.setId(match.getId());

            soapMatch.setHomeTeamId(
                    match.getHomeTeam().getId()
            );

            soapMatch.setHomeTeamName(
                    match.getHomeTeam().getName()
            );

            soapMatch.setAwayTeamId(
                    match.getAwayTeam().getId()
            );

            soapMatch.setAwayTeamName(
                    match.getAwayTeam().getName()
            );

            soapMatch.setHomeScore(
                    match.getHomeScore()
            );

            soapMatch.setAwayScore(
                    match.getAwayScore()
            );

            response.getMatch().add(soapMatch);
        }

        return response;
    }
}
