package org.example.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiTestTwo {

    // Reuse expensive objects
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {

        try {
            System.out.println("========== REST ==========");
            searchCompany(new SearchRequest("Wego"));

            System.out.println("\n========== SOAP ==========");
            numberToWords(500);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * REST GET Example
     */
    public static void searchCompany(SearchRequest request)
            throws IOException, InterruptedException {

        String url =
                "https://bloomberg-market-and-financial-news.p.rapidapi.com/market/auto-complete?query="
                        + URLEncoder.encode(request.query(), StandardCharsets.UTF_8);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Key", "YOUR_API_KEY")
                .header("X-RapidAPI-Host",
                        "bloomberg-market-and-financial-news.p.rapidapi.com")
                .GET()
                .build();

        HttpResponse<String> response =
                CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status : " + response.statusCode());

        JsonNode json = MAPPER.readTree(response.body());

        System.out.println(
                MAPPER.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(json));
    }

    /**
     * Example REST POST
     * (Uses Java object instead of Mustache)
     */
    public static void postJsonExample() throws Exception {

        LoginRequest request = new LoginRequest(
                "john",
                "password123"
        );

        String json = MAPPER.writeValueAsString(request);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com/api/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response =
                CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    /**
     * SOAP Example
     */
    public static void numberToWords(int number)
            throws IOException, InterruptedException {

        String xml = """
                <?xml version="1.0" encoding="utf-8"?>
                <soap:Envelope
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                    xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Body>
                        <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
                            <ubiNum>%d</ubiNum>
                        </NumberToWords>
                    </soap:Body>
                </soap:Envelope>
                """.formatted(number);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://www.dataaccess.com/webservicesserver/NumberConversion.wso"))
                .header("Content-Type", "text/xml; charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

        HttpResponse<String> response =
                CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status : " + response.statusCode());

        Document document = Jsoup.parse(
                response.body(),
                "",
                Parser.xmlParser());

        System.out.println(document.outerHtml());
    }

    /**
     * Request DTOs
     */
    public record SearchRequest(String query) {
    }

    public record LoginRequest(
            String username,
            String password
    ) {
    }
}
