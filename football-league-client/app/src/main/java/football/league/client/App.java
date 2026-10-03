package football.league.client;

/* import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse; */
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        // Test add match on boot
        // Placeholder
        LeagueClient client = new LeagueClient();
        String response = client.addMatch(1, 2, 3, 2);
        System.out.println(response);
    }
}
