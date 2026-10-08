package org.example.leet.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerData {

  int id;
  String city;

  @Override
  public String toString(){
    return this.getId()+" "+this.getCity();
  }

}
