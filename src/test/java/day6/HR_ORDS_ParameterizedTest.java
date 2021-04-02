package day6;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import test_utils.HR_ORDS_API_BaseTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HR_ORDS_ParameterizedTest extends HR_ORDS_API_BaseTest {

    @Test
    public void testCountries(){

        get("/countries").prettyPeek();
        get("/countries/AR").prettyPeek();
    }

    @ParameterizedTest
    @ValueSource(strings = {"AR","AU","TR"})
    public void testSingleCountryWithValues(String countryIdArg){
        // GET /countries/{country_id}

        given()
                .log().uri()
                .pathParam("country_id",countryIdArg).
        when()
                .get("/countries/{country_id}").
        then()
                .log().body()
                .body("items",is(not(empty())))
                .body("count",is(1))
                .statusCode(200);
    }


    @ParameterizedTest
    @CsvSource({"AR, Argentina",
            "US, United States of America",
            "UK, United Kingdom"})
    public void testingCountryWithCSVSource(String countryIdArg, String countryNameArg){
        // Send request to GET  /countries/{country_id}
        //Expect country name match the corresponding country_id

        given()
                .log().uri()
                .pathParam("country_id",countryIdArg).
        when()
                .get("/countries/{country_id}").
        then()
                .body("items[0].country_name",is(countryNameArg));


    }


    //Write a static method that returns list of country ids

    public static List<String> getCountryNameList(){
        List<String> countryList=

                get("/countries").jsonPath().getList("items.country_id",String.class);


        return countryList;
    }

    @ParameterizedTest
    @MethodSource("getCountryNameList")
    public void testCountryWithMethodSource(String countryIdArg){

        // GET /countries/{country_id}
        given()
                .log().uri()
                .pathParam("country_id", countryIdArg).
                when()
                .get("/countries/{country_id}").
                then()
                .log().body()
                .statusCode(200)
                .body("count", is(1) )
        ;
    }


}
