package day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Spartan App Get Request")
public class SpartanNoAuthTest {

    //http://54.159.232.203:8000/api/spartans

    @BeforeAll

    public static void init() {
        //this will set the part of URL at RestAssured
        RestAssured.baseURI = "http://54.159.232.203:8000";
       // RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset(); // resets all values back to original values
    }

    @Test
    public void hello(){

        // actual request url sent , is this

        get("/hello").prettyPeek();
    }


    @Test
    public void getAllSpartans() {

        get("/spartans").prettyPeek();

    }


}
