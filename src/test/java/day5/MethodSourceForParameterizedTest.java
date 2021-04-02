package day5;
import com.sun.glass.events.WheelEvent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;

public class MethodSourceForParameterizedTest {

    @ParameterizedTest
    @MethodSource("getManyNames")
    public void testPrintNames(String name){

        System.out.println("name = " + name);
        assertTrue(name.length()>=4);

    }


    public static List<String> getManyNames(){
        List<String> nameList =
                Arrays.asList("Emre","Mustafa","Diana",
                        "Tucky","don Corleone","Erjon","Saya");

        return nameList;

    }
}

















