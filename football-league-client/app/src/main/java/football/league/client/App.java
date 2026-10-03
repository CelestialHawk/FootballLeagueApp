package football.league.client;

/* import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse; */
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter something: ");
            String input = scanner.nextLine();
            System.out.println("You entered: " + input);
        }

        // SOAP request to retrieve all teams
        // Placeholder 
        /*
        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:league="http://footballapp.com/league">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <league:getTeamsRequest/>
                    </soapenv:Body>

                </soapenv:Envelope>
                """;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ws"))
                .header("Content-Type", "text/xml; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(soapRequest))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP Status: " + response.statusCode());
        System.out.println(response.body());
        */
    }
}
