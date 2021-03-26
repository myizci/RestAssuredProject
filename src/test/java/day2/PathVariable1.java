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

@DisplayName("Spartan Test with path variable and query parameter")
public class PathVariable1 extends SpartanNoAuthBaseTest {

    @Test
    public void getOneSpartan() {
        //  get("/spartans/16").prettyPeek();

        // I want to provide 16 as path variable | parameter
        // I want to provide accept header

        Response r1 =
                given()
                        .header("Accept", "application/json")
                        .pathParam("Voltron", "16").
                        when()
                        .get("/spartans/{Voltron}")
                        .prettyPeek()  // we cannot assign prettyPrint to response
                ;

        // This is an alternative way of providing
        //path variable and value directly in get() method

        Response r2 =
                given()
                        //this is same as .header("Accept", "application/json")
                        .accept("application/json").
                        when()
                        .get("/spartans/{spartan_id}", 16)
                        .prettyPeek();

    }


    @Test
    public void getOneSpartanWithLog() {

        Response response =

                given()
                        .log().all()
                        .accept("application/json")
                        .pathParam("id", 81).
                        when()
                        .get("/spartans/{id}").
                        prettyPeek();

        assertThat(response.statusCode(),equalTo(200));
        assertThat(response.contentType(),is("application/json"));
        assertThat(response.path("name"),is("Georgianne"));


    }

}
