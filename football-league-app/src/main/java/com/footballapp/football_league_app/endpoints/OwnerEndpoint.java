package com.footballapp.football_league_app.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.Owner;
import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.repositories.OwnerRepository;
import com.footballapp.football_league_app.repositories.TeamRepository;

import com.footballapp.league.GetOwnerRequest;
import com.footballapp.league.GetOwnerResponse;
import com.footballapp.league.AddOwnerRequest;
import com.footballapp.league.AddOwnerResponse;
import com.footballapp.league.ChangeOwnerRequest;
import com.footballapp.league.ChangeOwnerResponse;

@Endpoint
public class OwnerEndpoint {
    
    private static final String NAMESPACE_URI = "http://footballapp.com/league";

    private final OwnerRepository ownerRepository;
    private final TeamRepository teamRepository;

    public OwnerEndpoint(OwnerRepository ownerRepository, TeamRepository teamRepository) {
        this.ownerRepository = ownerRepository;
        this.teamRepository = teamRepository;
    }

    @PayloadRoot(
        namespace = NAMESPACE_URI,
        localPart = "getOwnerRequest"
    )
    @ResponsePayload
    public GetOwnerResponse getOwner(
            @RequestPayload GetOwnerRequest request) {

        // Retrieve the owner by team ID from the repository
        Owner owner = ownerRepository.findByTeamId(request.getTeamId());

        GetOwnerResponse response = new GetOwnerResponse();

        // Map the retrieved owner to the SOAP response
        if (owner != null) {
            com.footballapp.league.Owner soapOwner =
                    new com.footballapp.league.Owner();

            soapOwner.setId(owner.getId());
            soapOwner.setName(owner.getName());

            response.setOwner(soapOwner);
        }

        return response;
    }

    @PayloadRoot(
    namespace = NAMESPACE_URI,
    localPart = "addOwnerRequest"
    )
    @ResponsePayload
    public AddOwnerResponse addOwner(
            @RequestPayload AddOwnerRequest request) {

        Team team = teamRepository
                .findById(request.getTeamId())
                .orElseThrow();

        Owner owner = new Owner(
                request.getName(),
                team
        );

        Owner savedOwner = ownerRepository.save(owner);

        AddOwnerResponse response = new AddOwnerResponse();
        response.setOwnerId(savedOwner.getId());

        return response;
    }

    @PayloadRoot(
    namespace = NAMESPACE_URI,
    localPart = "changeOwnerRequest"
    )
    @ResponsePayload
    public ChangeOwnerResponse changeOwner(
            @RequestPayload ChangeOwnerRequest request) {

        Owner owner = ownerRepository
                .findById(request.getOwnerId())
                .orElseThrow();

        owner.setName(request.getName());

        Owner savedOwner = ownerRepository.save(owner);

        ChangeOwnerResponse response = new ChangeOwnerResponse();
        response.setOwnerId(savedOwner.getId());

        return response;
    }
}
