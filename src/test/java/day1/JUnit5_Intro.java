package day1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class JUnit5_Intro {

    @Test
    public void test(){
        System.out.println("Learning JUnit5");

        assertEquals(1,1,"Something is wrong");
    }
}
