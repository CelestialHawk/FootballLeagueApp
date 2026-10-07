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
                System.out.println("2. Retrieve Owner Information");
                System.out.println("3. Add a Team Owner");
                System.out.println("4. Exit");
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
                        List<Team> showTeams = leagueClient.getTeams();

                        System.out.println();

                        for (Team team : showTeams) {
                            System.out.println(team.getId() + ". " + team.getName());
                        }
                        
                        System.out.print("Enter team ID: ");
                        long teamId = Long.parseLong(scanner.nextLine());

                        String ownerResponse = leagueClient.getOwner(teamId);

                        System.out.println();
                        System.out.println(ownerResponse);
                         break; 

                    case "3":
                        List<Team> ownerTeams = leagueClient.getTeams();

                        System.out.println();
                        System.out.println("Select a team to add an owner:");

                        for (int i = 0; i < ownerTeams.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". " + ownerTeams.get(i).getName()
                            );
                        }

                        System.out.print("Team: ");
                        int teamChoice = Integer.parseInt(scanner.nextLine());

                        System.out.print("Owner name: ");
                        String ownerName = scanner.nextLine();

                        Team selectedTeam = ownerTeams.get(teamChoice - 1);

                        String addOwnerResponse = leagueClient.addOwner(
                                selectedTeam.getId(),
                                ownerName   
                        );

                        System.out.println();
                        System.out.println("Owner added successfully.");
                        System.out.println(addOwnerResponse);

                        break;

                    case "4":
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        }
    }
} 
