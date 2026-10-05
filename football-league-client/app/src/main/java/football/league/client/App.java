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
                System.out.println("Available commands:");
                System.out.println("1. View all teams");
                System.out.println("2. Add Match Information");
                System.out.println("3. Exit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine();

                switch (command) {

                    case "1":
                        List<Team> teams = leagueClient.getTeams();
                        System.out.println();
                        System.out.println("Teams:");
                        for (int i = 0; i < teams.size(); i++) {
                            System.out.println(
                                (i +1) + ". " + teams.get(i).getName()
                            );
                        }
                        break;

                    case "2":
                        System.out.print("Enter home team ID: ");
                        long homeTeamId = Long.parseLong(scanner.nextLine());
                        System.out.print("Enter away team ID: ");
                        long awayTeamId = Long.parseLong(scanner.nextLine());
                        System.out.print("Enter home score: ");
                        int homeScore = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter away score: ");
                        int awayScore = Integer.parseInt(scanner.nextLine());

                        leagueClient.addMatch(
                            homeTeamId, 
                            awayTeamId, 
                            homeScore, 
                            awayScore);
                        break;

                    case "3":
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        }
    }
}
