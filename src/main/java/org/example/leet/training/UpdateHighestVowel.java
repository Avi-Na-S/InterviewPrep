package org.example.leet.training;

import java.util.List;
import java.util.Objects;

public class UpdateHighestVowel {

    public static void main(String[] args) {
        String name = "helloeeeeeee Avinas, Good Morning";

        Character highestCar = null;
        int maxCount = 0;
        int len = name.length();
        Character prevChar = null;

        List<Character> vowel = List.of('e', 'o', 'a', 'i', 'u');
        for(Character c : vowel){
            int charCount = len-name.replace(c+"","").length();
            if(charCount>maxCount){
                maxCount = charCount;
                highestCar = c;
            }
            if(!c.equals('e') && !c.equals(highestCar)){
                name = name.replace(c,highestCar);
            }
        }

        System.out.println(name);

    }
}
