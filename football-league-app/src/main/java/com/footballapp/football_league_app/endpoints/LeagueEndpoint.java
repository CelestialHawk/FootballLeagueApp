package com.footballapp.football_league_app.endpoints;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.LeagueTable;
import com.footballapp.football_league_app.services.LeagueService;
import com.footballapp.league.GetLeagueTableRequest;
import com.footballapp.league.GetLeagueTableResponse;

@Endpoint
public class LeagueEndpoint {

    private static final String NAMESPACE_URI =
            "http://footballapp.com/league";

    private final LeagueService leagueService;

    public LeagueEndpoint(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PayloadRoot(
            namespace = NAMESPACE_URI,
            localPart = "getLeagueTableRequest"
    )
    @ResponsePayload
    public GetLeagueTableResponse getLeagueTable(
            @RequestPayload GetLeagueTableRequest request) {

        List<LeagueTable> table =
                leagueService.getLeagueTable();

        GetLeagueTableResponse response =
                new GetLeagueTableResponse();

        for (LeagueTable entry : table) {

            GetLeagueTableResponse.Team soapTeam =
                    new GetLeagueTableResponse.Team();

            soapTeam.setId(entry.getTeam().getId());
            soapTeam.setName(entry.getTeam().getName());
            soapTeam.setPlayed(entry.getPlayed());
            soapTeam.setWins(entry.getWins());
            soapTeam.setDraws(entry.getDraws());
            soapTeam.setLosses(entry.getLosses());
            soapTeam.setPoints(entry.getPoints());

            response.getTeam().add(soapTeam);
        }

        return response;
    }
}
