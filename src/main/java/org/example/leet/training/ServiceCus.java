package org.example.leet.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ServiceCus {

  public static void main(String[] args){
    List<CustomerData> customerDatas = new ArrayList<>();
    customerDatas.add(new CustomerData(1,"Chennai"));
    customerDatas.add(new CustomerData(2,"Chennai"));
    customerDatas.add(new CustomerData(3,"Bengalure"));
    String a = "Beeba";

    Set<CustomerData> filterData = customerDatas.stream()
        .filter(customerData -> "Chennai".equals(customerData.getCity())).collect(
            Collectors.toSet());

    System.out.println(filterData);
    String tes = a.toLowerCase();
    Optional<Character> test = tes.chars()
        .filter(c -> tes.indexOf(c) == tes.lastIndexOf(c)).mapToObj(c -> (char) c).findFirst();

    test.ifPresentOrElse(te->System.out.println(te),()->System.out.println("No such char"));
  }
}
