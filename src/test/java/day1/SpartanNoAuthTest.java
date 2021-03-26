package day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Spartan App Get Request")
public class SpartanNoAuthTest extends SpartanNoAuthBaseTest {



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
