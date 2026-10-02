package com.footballapp.football_league_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.entities.Match;
import com.footballapp.football_league_app.repositories.MatchRepository;
import com.footballapp.football_league_app.repositories.TeamRepository;

@SpringBootApplication
public class FootballLeagueAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballLeagueAppApplication.class, args);
    }

     @Bean
    CommandLineRunner testMatch(
            TeamRepository teamRepository,
            MatchRepository matchRepository) {

        return args -> {

            Team homeTeam = teamRepository.findById(1L)
                    .orElseThrow();

            Team awayTeam = teamRepository.findById(2L)
                    .orElseThrow();

            Match match = new Match(
                    homeTeam,
                    awayTeam,
                    2,
                    1
            );

            Match savedMatch = matchRepository.save(match);

            System.out.println(
                    "Saved match: "
                    + savedMatch.getHomeTeam().getName()
                    + " "
                    + savedMatch.getHomeScore()
                    + " - "
                    + savedMatch.getAwayScore()
                    + " "
                    + savedMatch.getAwayTeam().getName()
            );
        };
    }
}
