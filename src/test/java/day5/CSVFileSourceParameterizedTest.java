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
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileSourceParameterizedTest {

   @ParameterizedTest
   @CsvFileSource(resources = "/state_city_zipCount.csv",numLinesToSkip = 1)
   public void testStateCityToZipEndpointWithCSVFile(String stateArg, String cityArg, int zipArg){
//      System.out.println("stateArg = " + stateArg);
//      System.out.println("cityArg = " + cityArg);
//      System.out.println("zipArg = " + zipArg);



                     given()
                           .baseUri("https://api.zippopotam.us")
                            .pathParam("state",stateArg)
                            .pathParam("city",cityArg)
                            .log().uri().
                      when()
                           .get("/us/{state}/{city}").
                      then()
                      // .log().body()
                            .statusCode(200)
                              .body("places",hasSize(zipArg))

              ;



   }



}
