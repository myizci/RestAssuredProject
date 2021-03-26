package day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_utils.SpartanNoAuthBaseTest;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsFromShortVideos extends SpartanNoAuthBaseTest {

    @Test
    public void viewSpartanTest() {
        Response response = get("/spartans");
         assertThat(response.statusCode(),is(200));
         assertThat(response.body().asString(),containsString("Allen"));

    }

    @Test
    public void viewSpartanTest2() {
        Response response = given().accept(ContentType.JSON).when().get("/spartans");
        assertThat(response.statusCode(),is(200));



    }
}
