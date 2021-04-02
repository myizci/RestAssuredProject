package day5;
import com.sun.glass.events.WheelEvent;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints={1,2,4,6,7,10})
    public void testPrintedMultipleIteration(int num){
       // int num =10;
        System.out.println("num = " + num);

        assertTrue(num>5);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Mustafa","Akbar","Dragisa","Alex"})
    public void testNames(String name){
        assertTrue(name.length()>=5);
    }

    //http://api.zippopotam.us/us/{zipcode}
    //22030,22031, 22032, 22033 , 22034, 22035, 22036
    //check the status code 200

    @ParameterizedTest
    @ValueSource(shorts={22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testZipCodes(short zip){



        given()
                .baseUri("http://api.zippopotam.us")
                .log().all()
                .pathParam("zipcode",zip).
        when()
                .get("/us/{zipcode}").
        then()
                .statusCode(200)
                .log().all();


    }





}
