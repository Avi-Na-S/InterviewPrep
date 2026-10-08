package org.example.gpt;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class StringGptTest {

    @Test
    void nonRepeatedChar(){
        var result = new StringGpt().nonRepeatedChar("aviav");
        assertNotNull(result);
        assertEquals('i',result);
    }

    @Test
    void isAnagram() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var anagram =StringGpt.class.getDeclaredMethod("anagram",String.class,String.class);
        anagram.setAccessible(true);
        boolean res = (boolean)anagram.invoke(new StringGpt(),"listen","silent");
        assertTrue(res);
    }
}