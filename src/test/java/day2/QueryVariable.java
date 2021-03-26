package day2;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class QueryVariable extends SpartanNoAuthBaseTest{

    @DisplayName("Test GET /spartans/search Endpoint")
    @Test
    public void testSearch(){

        Response response =
                given()
                        .log().all()
                        //.queryParam("nameContains","Nels")
                        .queryParam("gender","Female").
                when()
                        .get("/spartans/search")
                        .prettyPeek()
                ;

                // print the totalElement field value from the response

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));


    }

}
