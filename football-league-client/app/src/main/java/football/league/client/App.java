package football.league.client;

import java.util.Scanner;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        LeagueClient leagueClient = new LeagueClient();

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {

                System.out.println();
                System.out.println("======= Championship League Client =======");
                System.out.println();
                System.out.println("Available commands:");
                System.out.println("1. Add Match Information");
                System.out.println("2. Exit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine();

                switch (command) {

                    case "1":

                        List<Team> addTeams = leagueClient.getTeams();

                        System.out.println();

                        System.out.println("Select home team:");

                        for (int i = 0; i < addTeams.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". " + addTeams.get(i).getName()
                            );
                        }

                        System.out.print("Home team: ");
                        int homeChoice = Integer.parseInt(scanner.nextLine());

                        System.out.println();
                        System.out.println("Select away team:");

                        for (int i = 0; i < addTeams.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". " + addTeams.get(i).getName()
                            );
                        }

                        System.out.print("Away team: ");
                        int awayChoice = Integer.parseInt(scanner.nextLine());

                        System.out.print("Home score: ");
                        int homeScore = Integer.parseInt(scanner.nextLine());

                        System.out.print("Away score: ");
                        int awayScore = Integer.parseInt(scanner.nextLine());

                        Team homeTeam = addTeams.get(homeChoice - 1);
                        Team awayTeam = addTeams.get(awayChoice - 1);

                        String response = leagueClient.addMatch(
                                homeTeam.getId(),
                                awayTeam.getId(),
                                homeScore,
                                awayScore
                        );

                        System.out.println();
                        System.out.println("Match added successfully.");
                        System.out.println(response);

                        break;

                    case "2":
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        }
    }
} 
