package day1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;



// Setting display name of the test class in test result using @DisplayName
@DisplayName("Day 1 of Junit5 Tests")
public class JUnit5_Intro {

    @DisplayName("Testing Numbers")
    @Test
    public void test(){
        System.out.println("Learning JUnit5");

        assertEquals(1,1,"Something is wrong");
        assertEquals(1,2,"Something is wrong");
    }

    //add one more test, pass, fail
    //assert your name starts with 'A'
    @DisplayName("First initial test")
    @Test
    public void checkInitialLetter(){
        String str = "AOmer";

       // assertEquals(str.startsWith("A"),true);
        assertTrue(str.startsWith("A"));
    }






}
