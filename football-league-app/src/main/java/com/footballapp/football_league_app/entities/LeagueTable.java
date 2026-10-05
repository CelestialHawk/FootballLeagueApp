package com.footballapp.football_league_app.entities;

public class LeagueTable {

    private Team team;
    private int played;
    private int wins;
    private int draws;
    private int losses;
    private int points;

    public LeagueTable(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public int getPlayed() {
        return played;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    public void addWin() {
        played++;
        wins++;
        points += 3;
    }

    public void addDraw() {
        played++;
        draws++;
        points += 1;
    }

    public void addLoss() {
        played++;
        losses++;
    }
}
