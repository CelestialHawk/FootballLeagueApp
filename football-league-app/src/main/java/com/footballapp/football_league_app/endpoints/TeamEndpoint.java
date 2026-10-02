package com.footballapp.football_league_app.endpoints;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.repositories.TeamRepository;

import com.footballapp.league.GetTeamsRequest;
import com.footballapp.league.GetTeamsResponse;

@Endpoint
public class TeamEndpoint {

    private static final String NAMESPACE_URI = "http://footballapp.com/league";

    private final TeamRepository teamRepository;

    public TeamEndpoint(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PayloadRoot(
        namespace = NAMESPACE_URI,
        localPart = "getTeamsRequest"
    )
    @ResponsePayload
    public GetTeamsResponse getTeams(
            @RequestPayload GetTeamsRequest request) {

        // Retrieve all teams from the repository
        List<Team> teams = teamRepository.findAll();

        GetTeamsResponse response = new GetTeamsResponse();

        // Map the retrieved teams to the SOAP response
        for (Team team : teams) {
            com.footballapp.league.Team soapTeam =
                    new com.footballapp.league.Team();

            soapTeam.setId(team.getId());
            soapTeam.setName(team.getName());

            response.getTeam().add(soapTeam);
        }

        return response;
    }
}
