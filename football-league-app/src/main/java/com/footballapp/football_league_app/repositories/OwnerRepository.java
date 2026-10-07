package com.footballapp.football_league_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.footballapp.football_league_app.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    
}
