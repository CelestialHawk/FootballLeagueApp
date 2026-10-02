package com.footballapp.football_league_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.footballapp.football_league_app.entities.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
    
}
    