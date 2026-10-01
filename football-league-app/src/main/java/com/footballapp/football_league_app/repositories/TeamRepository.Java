package com.footballapp.football_league_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.footballapp.football_league_app.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}