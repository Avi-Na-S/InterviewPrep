package org.example.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RailWay {

    public static void main(String[] args) {

      Map<String, List<String>> routes = new HashMap<>();

      routes.put("Chennai", List.of("Bangalore", "Delhi"));
      routes.put("Bangalore", List.of("Delhi","Mumbai"));
      routes.put("Delhi", List.of("Mumbai"));
      routes.put("Mumbai", List.of("Goa"));
      routes.put("Goa", List.of("Bangalore"));

      findRoute(routes, "Chennai", "Mumbai");
    }

    static void findRoute(Map<String, List<String>> routes,
        String source,
        String destination) {

      Queue<List<String>> queue = new LinkedList<>();

      Set<String> visited = new HashSet<>();

      queue.offer(List.of(source));

      while (!queue.isEmpty()) {

        List<String> path = queue.poll();

        String current = path.get(path.size() - 1);

        if (current.equals(destination)) {

          System.out.println(path);

          return;

        }

        if (!visited.add(current)) {

          continue;

        }

        for (String next : routes.getOrDefault(current,
            Collections.emptyList())) {

          List<String> newPath = new ArrayList<>(path);

          newPath.add(next);

          queue.offer(newPath);

        }

      }

      System.out.println("No Route Found");

    }



}


