package football.league.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LeagueClient {

    private final HttpClient httpClient;

    public LeagueClient() {
        httpClient = HttpClient.newHttpClient();
    }
    
    // Get all teams via SOAP request to Spring
    public List<Team> getTeams() throws Exception {

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

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ws"))
                .header("Content-Type", "text/xml; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(soapRequest))
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        DocumentBuilderFactory factory = 
                DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = 
                factory.newDocumentBuilder();
        Document document = 
                builder.parse(
                    new InputSource(
                        new StringReader(response.body()
                    )
                )
            );
        NodeList teamNodes = 
                document.getElementsByTagNameNS(
                    "http://footballapp.com/league", 
                    "team"
                );
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < teamNodes.getLength(); i++) {
            Element teamElement = (Element) teamNodes.item(i);
            long id = Long.parseLong(teamElement.getElementsByTagNameNS("http://footballapp.com/league", "id").item(0).getTextContent());
            String name = teamElement.getElementsByTagNameNS("http://footballapp.com/league", "name").item(0).getTextContent();
            teams.add(new Team(id, name));
        }
        return teams;
    }

    // Add a match via SOAP request to Spring
    public String addMatch(
        long homeTeamId,
        long awayTeamId,
        int homeScore,
        int awayScore) throws Exception {

        String soapRequest = """
                <?xml version="1.0" encoding="UTF-8"?>
                <soapenv:Envelope
                    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:league="http://footballapp.com/league">

                    <soapenv:Header/>

                    <soapenv:Body>
                        <league:addMatchRequest>
                            <league:homeTeamId>%d</league:homeTeamId>
                            <league:awayTeamId>%d</league:awayTeamId>
                            <league:homeScore>%d</league:homeScore>
                            <league:awayScore>%d</league:awayScore>
                        </league:addMatchRequest>
                    </soapenv:Body>

                </soapenv:Envelope>
                """.formatted(
                    homeTeamId,
                    awayTeamId,
                    homeScore,
                    awayScore
                );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/ws"))
                .header("Content-Type", "text/xml; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(soapRequest))
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        return response.body();
    }
}