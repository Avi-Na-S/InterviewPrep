package org.example.test;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public class AvgScore {

  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    String score = "Rakesh=20,Mukesh=10,Anu=10,Vikram=30,sachin=50,Mukesh=30,Anu=100,Vikram=90,Rakesh=30,Kishan=10,Sumit=5,Abhay=20,Rajesh=8,Sumit=30,Rajesh=100,Vikram=30,sachin=50,Mukesh=30,Anu=100,Vikram=90,Rakesh=30,Kishan=10";
    List<String> scorelist = Arrays.asList(score.split(","));
    System.out.println(
        scorelist.stream().map(data -> Arrays.asList(data.split("="))).collect(
            Collectors.groupingBy(d -> d.get(0),
                Collectors.averagingInt(d -> Integer.parseInt(d.get(1))))));

//      System.out.println(
//              scorelist.stream().map(data -> Arrays.asList(data.split("="))).collect(
//                      Collectors.groupingBy(d -> d.get(0))));

//    Response response = new DefaultAsyncHttpClient().executeRequest(
//        new RequestBuilder().setBody("").setUrl("").setMethod("").build()).get();
//
//    JsonNode a =new ObjectMapper().readTree(response.getResponseBodyAsStream());
//
//    Document ae = Jsoup.parse(response.getResponseBodyAsStream(),
//        StandardCharsets.UTF_8.name(), "", Parser.xmlParser());

  }
}


