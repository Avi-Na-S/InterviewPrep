package org.example.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import io.netty.handler.codec.http.HttpMethod;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;


public class ApiTest {

  private static final Mustache mus = new DefaultMustacheFactory().compile("tested/request.json");
  private static final Mustache xml = new DefaultMustacheFactory().compile("tested/request.xml");

  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    getRequestJson();
  }

  public static void getRequestJson() throws ExecutionException, InterruptedException, IOException {

    Request request = new RequestBuilder().setUrl(
            "https://bloomberg-market-and-financial-news.p.rapidapi.com/market/auto-complete")
        //.setProxyServer(new ProxyServer.Builder("",1212).build())
        .setMethod(HttpMethod.GET.toString())
        .addQueryParam("query", "Wego")
        .addHeader("X-RapidAPI-Key", "e7bed9c7d7msh3163804799ce0d2p1d5e65jsn01df5ff918c7")
        .addHeader("X-RapidAPI-Host", "bloomberg-market-and-financial-news.p.rapidapi.com").build();

    DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setConnectTimeout(
        20000).build();
    Response response = new DefaultAsyncHttpClient(config).executeRequest(request)
        .get();

    JsonNode node = new ObjectMapper().readTree(
        response.getResponseBodyAsStream());

    System.out.println(node);

    //System.out.println(mus.execute(new StringWriter(), Map.of("test", "hired")).toString());
  }

  public static void getRequestXml() throws ExecutionException, InterruptedException, IOException {

    Request requets = new RequestBuilder().setUrl(
            "https://www.dataaccess.com/webservicesserver/NumberConversion.wso")
        .setMethod(HttpMethod.POST.name()).setHeader(
            "Content-Type", "text/xml; charset=utf-8")
        .setBody(xml.execute(new StringWriter(), Map.of("number", 500)).toString()).build();

    Response response = new DefaultAsyncHttpClient().executeRequest(requets).get();
    Document a = Jsoup.parse(response.getResponseBodyAsStream(),
        StandardCharsets.UTF_8.name(), "", Parser.xmlParser());
  }
}
