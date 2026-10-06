package com.footballapp.football_league_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.footballapp.football_league_app.entities.Match;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findTop10ByHomeTeamIdOrAwayTeamIdOrderByIdDesc(
        Long homeTeamId,
        Long awayTeamId
    );
}
    