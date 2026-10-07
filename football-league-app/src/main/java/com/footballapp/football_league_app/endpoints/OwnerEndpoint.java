package com.footballapp.football_league_app.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.footballapp.football_league_app.entities.Owner;
import com.footballapp.football_league_app.repositories.OwnerRepository;

import com.footballapp.league.GetOwnerRequest;
import com.footballapp.league.GetOwnerResponse;

@Endpoint
public class OwnerEndpoint {
    
    private static final String NAMESPACE_URI = "http://footballapp.com/league";

    private final OwnerRepository ownerRepository;

    public OwnerEndpoint(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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
}
