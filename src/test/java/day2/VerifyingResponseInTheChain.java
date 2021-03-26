package day2;

import test_utils.SpartanNoAuthBaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class VerifyingResponseInTheChain extends SpartanNoAuthBaseTest {

    @DisplayName("Verifying the GET /spartans/{id} response directly in the chain")
    @Test
    public void testOneSpartanInOneShot(){

    given()
            .log().all() // this will log the request
            .pathParam("id",77).
    when()
            .get("/spartans/{id}").
    then()
            .log().all() // this will log the response
            .statusCode(200)
            .header("Content-Type",is("application/json"))
            .contentType("application/json")
            .body("id",equalTo(77))
            .body("name",equalTo("Stevana"))
            .body("phone",equalTo(1459126818))
    ;




    }
}
