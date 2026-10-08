package org.example.gpt;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer, Integer> {

  private final int capacity;

  public LRUCache(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  @Override
  protected boolean removeEldestEntry(
      Map.Entry<Integer, Integer> eldest) {
    return size() > capacity;
  }
}

class LRU{

  public static void main(String[] args) {
    LRUCache c = new LRUCache(5);

    c.put(1,5);
    c.put(2,3);
    c.put(3,4);
    c.put(4,7);
    c.put(5,8);
    c.put(1,9);
    c.put(6,4);

    System.out.println(c);


  }
}

