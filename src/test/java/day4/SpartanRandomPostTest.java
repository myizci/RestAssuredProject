package day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import spartan_util.SpartanUtil;
import test_utils.SpartanNoAuthBaseTest;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import test_utils.SpartanNoAuthBaseTest;
public class SpartanRandomPostTest extends SpartanNoAuthBaseTest {

    @DisplayName("/POST /spartans with random Data")
    @Test
    public void addOneRandomSpartanTest() {
        // this is the map object we sent is the expected result
        Map<String, Object> randomRequestBodyMap = SpartanUtil.getRandomSpartanMap();

        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomRequestBodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomRequestBodyMap.get("name")))
        ;
    }

    @DisplayName("/POST /spartans with random POJO")
    @Test
    public void addOneRandomSpartanPOJOTest() {

        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();

        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                when()
                .post("/spartans").
                then()
                .log().all()
                .body("data.name", is(randomPOJO.getName()))
                .body("data.gender", is(randomPOJO.getGender()))
                .body("data.phone", is(randomPOJO.getPhone()))
        ;


    }

    @DisplayName("POST /spartans and send /GET/spartans/{id} to verify")
    @Test

    public void testAddOneDataThenGetOneDataToVerify() {

        // send post request , save the id generated
        //check status code is 201
        //send GET request using the id we saved from above
        // check status is 200 and body match exactly what we send
        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();
        Response response =
                given()
                        .log().body()
                        .contentType(ContentType.JSON)
                        .body(randomPOJO).
                        when()
                        .post("/spartans").prettyPeek();


        int newId = response.path("data.id");
        //  int newId2 = response.jsonPath().getInt("data.id"); //alternative way

        assertThat(response.statusCode(), is(201));
        //  assertThat(response.);


        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .pathParam("id", newId).
                when()
                .get("/spartans/{id}").
                then()
                .log().body()
                .statusCode(200)
                .body("id", is(newId))
                .body("name", is(randomPOJO.getName()))
                .body("gender", is(randomPOJO.getGender()))
                .body("phone", is(randomPOJO.getPhone()))
        ;
    }

    @DisplayName("POST /spartans and send /GET/spartans/{id} to verify 2")
    @Test

    public void testAddOneDataThenGetOneDataToVerifyInTheChain() {
        // send post request verify 201 and then extract the id in the same method chain
        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();
        int newId = given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                        when()
                .post("/spartans").

                        then()
                .log().body()
                .statusCode(201).
                        extract()
                //.path("data.id")
                .jsonPath().getInt("data.id");


        System.out.println("newId = " + newId);


    }

    @DisplayName("POST /spartans and send /GET/spartans/{id} to verify 3")
    @Test

    public void testAddOneDataThenExtractHeader() {
        //check status code and Extract Location Header
        Spartan randomPOJO = SpartanUtil.getRandomSpartanPOJO();

        String locationHeader =
        given()
                .log().body()
                .contentType(ContentType.JSON)
                .body(randomPOJO).
                        when()
                .post("/spartans").

                then()
                .statusCode(201)

                .extract()
                .header("Location");

        System.out.println("locationHeader = " + locationHeader);

        // Sending the GET request using the above url we extracted

        get(locationHeader).prettyPrint();
    }

}