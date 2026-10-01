package com.footballapp.football_league_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.footballapp.football_league_app.entities.Team;
import com.footballapp.football_league_app.repositories.TeamRepository;

@SpringBootApplication
public class FootballLeagueAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballLeagueAppApplication.class, args);
    }


    //run database input on boot to test connection to database and test repository
    //confirmed works
    /* @Bean
    CommandLineRunner loadData(TeamRepository teamRepository) {
        return args -> {

            if (teamRepository.count() == 0) {
                teamRepository.save(new Team("Cardiff City"));
                
            }
        };
    } */
}
