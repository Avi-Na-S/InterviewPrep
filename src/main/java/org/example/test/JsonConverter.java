package org.example.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class JsonConverter {

  public static void main(String[] args) throws JsonProcessingException {

    System.out.println(jsonString());
  }

  public static List<JsonNode> jsonString() throws JsonProcessingException {
    String a  = "{\"browsers\":{\"firefox\":{\"name\":\"Firefox\",\"pref_url\":\"about:config\",\"releases\":{\"1\":{\"release_date\":\"2004-11-09\",\"status\":\"retired\",\"engine\":\"Gecko\",\"engine_version\":\"1.7\"},\"2\":{\"release_date\":\"2004-11-09\",\"status\":\"retired\",\"engine\":\"Gecko\",\"name\":\"1.7\"}}}}}";
    JsonNode node = new ObjectMapper().readTree(a);
    Iterator<Entry<String, JsonNode>> nodes = node.fields();
    while (nodes.hasNext()){
      Entry<String, JsonNode> n = nodes.next();
     // System.out.println(nodes.next() + "----"+nodes.next());
      System.out.println(n.getKey() +"--"+n.getValue());
    }

    return node.findValues("name");
  }
}
